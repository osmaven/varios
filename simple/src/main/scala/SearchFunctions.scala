package oscar.prueba.utils

import org.apache.spark.rdd.RDD

class SearchFunctions(val query: String)
{
  def isMatch(s: String): Boolean = {
    s.contains(query) }

  def getMatchesFunctionReference(rdd: RDD[(String, Int)]): RDD[Boolean] = {
    rdd.keys.map(isMatch)}
}
