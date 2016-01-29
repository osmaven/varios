*** DEMO 1 - Twitter Search - GFXD - HAWQ ***

Pull tweets with 'hadoop' in them into spring XD then GXD and ultimately land in the data lake for further analysis with HAWQ.

--Insert directly into GFXD
create table tweet (id VARCHAR(255), from_user VARCHAR(255), created_at VARCHAR(255), text VARCHAR(255), language_code VARCHAR(10), retweet_count INTEGER, retweet VARCHAR(10), PRIMARY KEY (id)) PARTITION BY PRIMARY KEY eviction by criteria (retweet_count <= 0) evict incoming hdfsstore (tweet) ;

--Spring XD Definition
stream create --name tweet --definition "twittersearch --query='hadoop' --outputType=application/json | jdbc --columns='id, from_user, created_at, text, language_code, retweet_count, retweet'" --deploy

--HAWQ
CREATE external TABLE tweet_pxf(
   id VARCHAR(255), from_user VARCHAR(255), created_at VARCHAR(255), text VARCHAR(255), 
   language_code VARCHAR(10), retweet_count INTEGER, retweet VARCHAR(10))
   location ('pxf://pivhdsne:50070/tweet/APP.TWEET?PROFILE=GemFireXD&CHECKPOINT=false') 
  FORMAT 'CUSTOM' (FORMATTER='pxfwritable_import');

  
select count(*) from tweet --GEMFIREXD-PROPERTIES queryHDFS=true \n;
  
select from_user, sum(retweet_count) as total  from tweet_pxf group by from_user order by total desc limit 10 ;


*** DEMO 2 - Map Reduce vs HAWQ***
This demo shows solving the same problem different ways.  The customer will see a spectrum of possibilities, but if speed is most important it is clear HAWQ is the best.

Find top tax paid by postal code.

--must go to this dir
cd  /pivotal-samples/map-reduce-java/taxpaid_by_postalcode

--show off attributes about the file
hdfs dfs -cat /retail_demo/orders/orders.tsv.gz | zcat | more
hdfs dfs -cat /retail_demo/orders/orders.tsv.gz | zcat | wc -l
hdfs dfs -ls /retail_demo/orders/orders.tsv.gz 

--time M/R job
time hadoop jar target/taxpaid_by_postalcode-1.0.jar com.pivotal.hadoop.PostalCodesPaidAmountTaxDriver /retail_demo/orders/orders.tsv.gz /output-mr1


--hawq external tables - show external tables
s
 

--hawq tables - show regular tables
select billing_address_postal_code, sum(total_paid_amount::float8) as total, sum(total_tax_amount::float8) as tax from retail_demo.orders_hawq group by billing_address_postal_code order by total desc limit 10;



  
***DEMO 3 - Twitter Stream into HDFS ***

stream create --name twitterfeed --definition "twitterstream --filterLevel=medium --outputType=application/json | hdfs --rollover=1M --directory=/twitter-stream"

stream create tweetlang  --definition "tap:stream:twitterfeed > field-value-counter --fieldName=lang" --deploy

stream create tweetcount --definition "tap:stream:twitterfeed > aggregate-counter" --deploy

stream create tagcount --definition "tap:stream:twitterfeed > field-value-counter --fieldName=entities.hashtags.text --name=hashtags" --deploy



--show the directory before, should be empty or you should clear it out
hdfs dfs -ls /twitter-stream

--turn the stream on
stream deploy tweets 

--show the directory filling up with files
hdfs dfs -ls /twitter-stream

--Bring up VM show the analytics app
cd spring-xd-samples/analytics-dashboard/
--start webserver
./startWebServer.sh (there is a tab in the windows group for this)

--Use firefox to browse to:
http://localhost:9889/dashboard.html


*** DEMO 4 - RMQ - GFXD - 2 Tables - DO NOT USE YET ****
--Insert directly into GXD
create table tweet (id VARCHAR(255), from_user VARCHAR(255), created_at VARCHAR(255), text VARCHAR(255), language_code VARCHAR(10), retweet_count INTEGER, retweet VARCHAR(10), PRIMARY KEY (id)) PARTITION BY PRIMARY KEY BUCKETS 5 eviction by criteria (language_code <> 'en') evict incoming hdfsstore (tweet) ;
create table hash_tag (id VARCHAR(255), tweet_id VARCHAR(255), text VARCHAR(255), language_code VARCHAR(10), PRIMARY KEY (id)) PARTITION BY COLUMN (tweet_id) colocate with (tweet) BUCKETS 5 eviction by criteria (language_code <> 'en') evict incoming hdfsstore (hash_tag) ;

--Spring XD Definitions
stream create --name twitterfeed --definition "twitterstream --filterLevel=medium | rabbit --exchange=twitter.fanout"
stream create --name tweet --definition "rabbit --queues=tweet --outputType=text/plain | transform --script=tweets-stream-delim.groovy --inputType=application/json | jdbc --columns='id, from_user, created_at, text, language_code, retweet_count, retweet'" --deploy
stream create --name hash_tag --definition "rabbit --queues=hash.tag --outputType=text/plain | transform --script=tweets-hash.groovy --inputType=application/json | splitter --expression=#jsonPath(payload,'$.hashtags.hashtag[*]') --inputType=application/json | jdbc --tableName=hash_tag --columns='id, tweet_id, text, language_code' --inputType=application/json " --deploy

--find most hashtags
select t.id, count(ht.tweet_id) as hash_tags  from  tweet as t left outer join  hash_tag ht on t.id = ht.tweet_id group by t.id order by count(ht.tweet_id) desc ;


--HAWQ Definitions
CREATE external TABLE tweet_pxf(
   id VARCHAR(255), from_user VARCHAR(255), created_at VARCHAR(255), text VARCHAR(255), 
   language_code VARCHAR(10), retweet_count INTEGER, retweet VARCHAR(10))
   location ('pxf://pivhdsne:50070/tweet/APP.TWEET?PROFILE=GemFireXD&CHECKPOINT=false') 
  FORMAT 'CUSTOM' (FORMATTER='pxfwritable_import');

CREATE external TABLE hash_tag_pxf(
   id VARCHAR(255), tweet_id VARCHAR(255), text VARCHAR(255), 
   language_code VARCHAR(10))
   location ('pxf://pivhdsne:50070/hash_tag/APP.HASH_TAG?PROFILE=GemFireXD&CHECKPOINT=false') 
  FORMAT 'CUSTOM' (FORMATTER='pxfwritable_import');

select t.id, count(ht.tweet_id) as hash_tags  from  tweet_pxf as t left outer join  hash_tag_pxf ht on t.id = ht.tweet_id group by t.id order by count(ht.tweet_id) desc ;

select t.id, count(ht.tweet_id) as hash_tags  from  tweet_pxf as t left outer join  hash_tag_pxf ht on t.id = ht.tweet_id group by t.id order by count(ht.tweet_id) desc ;
