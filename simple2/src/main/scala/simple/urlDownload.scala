package simple
import scala.io.Source
import org.apache.spark.{SparkConf, SparkContext}
/**
 * Created by oventura on 1/19/16.
 */
class urlDownload {
  val conf = new SparkConf().setAppName("URL Download Tool")
  val sc = new SparkContext(conf)

  val html = Source.fromURL("http://google.com", "UTF-8")
  val s = html.mkString
  val kk = sc.parallelize(s.split(" "))
  val o2 = kk.map(x => (x,1)).reduceByKey( (x,y)=>x+y)
}
