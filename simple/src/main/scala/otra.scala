package oscar.prueba
/* SimpleApp.scala */

import org.apache.spark.{SparkConf, SparkContext}
import sun.jvm.hotspot.memory.Dictionary
import scala.util.matching.Regex

object otra {


  val regex = "\"".r

  "123 Main Street".replaceAll("![a-Z,0-9]", "x")

  val format = new java.text.SimpleDateFormat("yyyy-MM-dd")

  /*case class Register (d: String, uuid: String, cust_id: String, lat: Float,
                       lng: Float)*/
  case class Click (d: String, uuid: String, landing_page: Int)


  def main(args: Array[String]) {

    /*def makePlurals(line: String): String = line + 's'*/

    def makePlurals(line: String): Array[String] = {

      return line.toLowerCase().split("\\s+").filter(p => p.length()>0)
    }

    def extract_one(line: Array[String,String]): String = line(0)

    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)

    val animals = List("cat", "dog")
    val s = sc.parallelize(animals)
    val o= s.map(x => makePlurals(x))

    val regfile =   sc.textFile("/Users/oventura/Documents/spark-1.4.1-bin-hadoop2.3/README.md").filter(line => line.contains("spark"))
    val words = regfile.flatMap(line => line.split(" ")).filter(line => line.contains("spark"))
    val counts2 = words.map(word => (word, 1)).reduceByKey { case (x, y) => x + y }
    val counts3 = words.map(word => (1, 1)).reduceByKey { case (x, y) => x + y }
    println("####################################")
    println("####################################")
    println("####################################")
    counts2.foreach(println)
    counts3.foreach(println)
    println("####################################")
    println("####################################")
    println("####################################")

    case class Register (id: Int, name: String, age: Int, number :Int)


    val clk = sc.textFile("/Users/oventura/Downloads/fakefriends.csv")

    val y = clk.map(_.split(",")).map(p => Register(p(0).trim.toInt, p(1),p(2).trim.toInt,p(3).trim.toInt))
    val h = y.id

    def tokenize(line: String): Array[String] = {

      return line.toLowerCase().replaceAll("[*!]", "").split("\\W+").filter(p => p.length()>0)

    }

    val stopwords = sc.textFile("/Users/oventura/Downloads/cs100/lab3/stopwords.csv", 4)

    val distFile = sc.textFile("/Users/oventura/Downloads/cs100/lab3/stopwords.csv", 4)

    val rd =distFile.map(x=>x.replaceAll("\"", "")).map(x => x.split(",")).filter(x => !(x(0)=="id" )).map(x=>(x(0),x))

    val ext= rd.map(x => (x(0),tokenize(x(1))))

    val tro = ext.map(x => x(1).subtract(stopwords))

    def prueba(line: String,array: Array[String]): {

      return line,array(1)

    }






  }

}
