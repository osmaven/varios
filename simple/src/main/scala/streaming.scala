/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// scalastyle:off println

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.streaming._

/**
 * K-means clustering.
 *
 * This is an example implementation for learning how to use Spark. For more conventional use,
 * please refer to org.apache.spark.mllib.clustering.KMeans
 */
object streaming {

  def parseVector(line: String): Vector[Double] = {
    DenseVector(line.split(' ').map(_.toDouble))
  }

  def closestPoint(p: Vector[Double], centers: Array[Vector[Double]]): Int = {
    var bestIndex = 0
    var closest = Double.PositiveInfinity

    for (i <- 0 until centers.length) {
      val tempDist = squaredDistance(p, centers(i))
      if (tempDist < closest) {
        closest = tempDist
        bestIndex = i
      }
    }

    bestIndex
  }

  def showWarning() {
    System.err.println(
      """WARN: This is a naive implementation of KMeans Clustering and is given as an example!
        |Please use the KMeans method found in org.apache.spark.mllib.clustering
        |for more conventional use.
      """.stripMargin)
  }

  def main(args: Array[String]) {


    val conf = new SparkConf().setAppName("Simple Application")
    val sc = new SparkContext(conf)

    // create a StreamingContext
    val ssc = new StreamingContext(sc, Seconds(2))
    // create a DStream that will connect to serverIP:serverPort
    val lines = ssc.socketTextStream("10.61.7.49", 9999)
    // split each line into words
    val words = lines.flatMap(_.split(" "))
    // count each word in each batch
    val pairs = words.map(word => (1, word.toInt))
    val wordCounts = pairs.reduceByKey(_ + _)
    // print a few of the counts to the console
    wordCounts.print()
    ssc.start() // start the computation
    ssc.awaitTermination()
  }
}
// scalastyle:on println
