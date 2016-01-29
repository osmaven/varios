
package oscar.prueba
/* SimpleApp.scala */

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import net.liftweb.json._

object SimpleApp {

  val format = new java.text.SimpleDateFormat("yyyy-MM-dd")

  case class Register (d: String, uuid: String, cust_id: String, lat: Float,
                       lng: Float)
  case class Click (d: String, uuid: String, landing_page: Int)


  def main(args: Array[String]) {

    /*val pt = new SearchFunctions("i")
    pt.isMatch("k").toString().foreach(print)*/

    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)

   /* val reg = sc.textFile("/Users/oventura/Downloads/reg.tsv").map(_.split("\t")).map(
      r => (r(1), Register(r(0), r(1), r(2), r(3).toFloat, r(4).toFloat))
    )
    val clk = sc.textFile("/Users/oventura/Downloads/clk.tsv").map(_.split("\t")).map(
      c => (c(1), Click(c(0), c(1), c(2).trim.toInt))
    )
    */

    /*val logFile = "hdfs://0.0.0.0:9000/tmp/Edstat-Data.csv"*/
    val regfile =   sc.textFile("/Users/oventura/Downloads/reg.tsv")
    val clkfile =  sc.textFile("/Users/oventura/Downloads/clk.tsv")

/*
    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)
    */

    val pro ="'uno',1"

                                          pro.toString()  sc.parallelize(pro.toString())
    val reg = regfile.map(_.split("\t")).map(
      r => (r(1), Register((r(0)), r(1), r(2), r(3).toFloat, r(4).toFloat))
    )

    val reg = regfile.map(_.split("\t")).map(
      r => (Register((r(0)), r(1), r(2), r(3).toFloat, r(4).toFloat))
    )

    val clk = clkfile.map(_.split("\t")).map(
      c => (c(1), Click((c(0)), c(1), c(2).trim.toInt))
    )

    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._
    reg.toDF().registerTempTable("my_rdd")


    val df = sqlContext.read.json("examples/src/main/resources/people.json")



    reg.join(clk).collect().foreach(println)

    val x = sc.parallelize(List( ("a" , "b" , 1) , ("a" , "b" , 1) , ("c" , "b" , 1) , ("a" , "d" , 1)))
    val byKey = x.map({case (a,b,count) => (a.concat(b))->count})

val reducedByKey = byKey.reduceByKey((x:Int,y:Int) => x + y)

    val redu1 = reducedByKey.sortByKey()

    val prueba = redu1.map({case((a,b),c) => c ->(a,b)})

val otro = prueba.reduceByKey((Int, (x1:String,y1:String) => x1 + y1))

  /*  ((a:String,b:String) => a.concat(b)) */
  val names1 = sc.parallelize(List("abe", "abby", "apple"))
    names1.flatMap(k => List(k.size) ).reduce((t1,t2) => t1 + t2)
    names2 => names1.flatMap(line => line.split(""))

      val pair = ('a', 'b')
          pair._1

      val sparkContext = new SparkContext("local", "functional")
      val employeeData = List(("Jack",1000.0),("Bob",2000.0),("Carl",7000.0))
      val employeeRDD = sc.makeRDD(employeeData)
      val dummyEmployee = ("dummy",0.0);

      val maxSalaryEmployee = employeeRDD.fold(dummyEmployee)((acc,employee) => {
        if(acc._2 < employee._2) employee else acc})
      println("employee with maximum salary is"+maxSalaryEmployee)

      numbers.fold(0) { (z, i) =>
        if (z > i) z else i
      }


      val wordList = List("scala", "akka", "play framework", "sbt", "typesafe","is")
      val tweet = "This is an example tweet talking about scala and sbt"

      tweet.contains(wordList).


    // Should be some file on your system*/

    /*
    val conf = new SparkConf().setAppName("Simple Application")
   val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache()
    /*logData.persist(StorageLevel.MEMORY_ONLY_SER)*/
   /* val pythonLines = logData.filter(line => line.contains("a"))*/
    /*val counts = logData.flatMap(line => line.split(" "))*/
    val words = logData.flatMap(line => line.split(","))
    try {
      val counts2 = words.map(word => (word, 1)).reduceByKey { case (x, y) => x + y }
      println("caracters with a")
      val counts3 = counts2.sortByKey(true)
      /*val rdd_boolean = pt.getMatchesFunctionReference(counts3)*/
      /*val counts4 = counts2.sortBy*/

      println("####################################")
      println("####################################")
      println("####################################")
      counts3.top(1).foreach(println)
      println("####################################")
      println("####################################")
      println("####################################")
      /*rdd_boolean.foreach(println)*/
      val kk = sc.


      /*counts2.values.max()*/
      /*counts.saveAsTextFile("/tmp/kk")*/
    }
    catch {
      case ex: Throwable => {
        println("failed to execute map",ex.getMessage())
      }
      /*val numAs = logData.filter(line => line.contains("e")).count()
      val numBs = logData.filter(line => line.contains("b")).count()
      println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))*/
      /*
      val sc = new SparkContext(conf)
      val lines = sc.textFile("data.txt")*/
      /* val words = logData.flatMap(logData <= logData.split(" "))
       val counts = words.map(words => 1)*/
      /*val total = words.reduce()
        .reduceByKey
        .reduceByKey{case(x,y)=> x+y}
      val pairs = logData.map(s => (s, 1))*/
      /* val counts = pairs.reduceByKey((a, b) => a + b)
       val kk = counts.toString()*/

    }
    /*counts.values.foreach(println)*/

    */
    case class Person(name: String, age: Int)

      val pro ="uno,1"
      val pro1 =sc.parallelize(List(pro))
      val y = pro1.map(_.split(",")).map(p => Person(p(0), p(1).trim.toInt))


      val animals = List("cat", "dog")
      val s = sc.parallelize(animals)
      val s33 =sc.parallelize(animals).map(word => (word, 1)).reduceByKey((x:Int,y:Int) => x + y)
      s33.map((x) => x)
      val numbers = Array(1, 2, 3, 4)
      val t2= sc.parallelize(List(numbers))



  }

}
