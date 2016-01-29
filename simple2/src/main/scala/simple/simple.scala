

package simple

import org.apache.spark.{SparkConf, SparkContext}
import util.{db_maps, load_conf, sql_executor}


object simple {
  def main(args: Array[String]) {



    //prop object to store variables for the JDBC connection, only will be used if the exec_query class is used
    val prop = new java.util.Properties

    val conf = new SparkConf().setAppName("Federation Tool")
    val sc = new SparkContext(conf)
    val sqlContext = new org.apache.spark.sql.SQLContext(sc)

    //Load of the configuration Files

    val configuration = new load_conf(sc)


    // Tow mappers one per database
    val dbmap1= new db_maps
    val dbmap2= new db_maps

  // Load of the dataframes using Jdbc datasource and choosing the database with the configuration file
    val t1 = sqlContext.read.format("jdbc").options(dbmap1.sql_map(configuration)).load()
    val t2 = sqlContext.read.format("jdbc").options(dbmap2.sql_map(configuration,"T")).load()

  // Register both Dataframe as Tables, right now we can run any sql on them
    t1.registerTempTable("t1")
    t2.registerTempTable("t2")

  //Run of the query of the conf file

    val result_query = sqlContext.sql(configuration.query)

    /*Conversion to RDD if needed */
    val struct1 = t1.rdd
    struct1.map( x => x.getAs("c1"))

    val exec_sql = new sql_executor(configuration)

   /* If some query is needed can be declared here

    val exec_sql = new sql_executor(configuration)
    exec_sql.set_texto(" Any SQL")
    exec_sql.execute()

    */

    try {
      result_query.write.mode(org.apache.spark.sql.SaveMode.valueOf(configuration.table3_mode)).jdbc(configuration.url, configuration.table3, prop)
    }
    catch {
      case e: Throwable  => e.printStackTrace + e.getMessage
        println("error")
    }


  }
}

