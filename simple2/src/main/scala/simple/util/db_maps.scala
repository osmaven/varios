package simple.util

/**
*  Created by oventura on 1/15/16.
 *
*/

class db_maps ()
{
 def sql_map (configuration: load_conf,ext: String) : Map[String, String] = {
   val b = Map("url" -> configuration.url2,
     "dbtable" -> configuration.table2,
     "driver" -> configuration.driver2,
     "user" -> configuration.user2,
     "password" -> configuration.password2
     )
   return b
 }

  def sql_map( configuration: load_conf): Map[String, String] = {

    val b = Map("url" -> configuration.url,
      "dbtable" -> configuration.table1,
      "driver" -> configuration.driver1,
      "user" -> configuration.user,
      "port" -> configuration.port
      )
    return b

  }
}
