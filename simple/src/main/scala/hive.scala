
/* SimpleApp.scala */
import org.apache.spark.sql.hive.
import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}




def main(args: Array[String]) {
  val conf = new SparkConf().setAppName("Simple Application")
  val sc = new SparkContext(conf)
  val hc = new HiveContext(sc)
  val timePartitionSelectionSQL = "select count(*) from kk2"
  val mainDf = hc.sql(timePartitionSelectionSQL).cache()
}
