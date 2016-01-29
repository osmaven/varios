package simple.util

/**
 * A helper to retrieve Spark configuration options
 * All possible options for this application should be centralized here
 */
object ConfigHelper {
  val USER1 = "spark.db1.user"
  val DB1_HOST = "spark.db1.host"
  val DB1_PORT = "spark.db1.port"
  val DB1_DATAB = "spark.db1.db"
  val DB1_TABLE = "spark.db1.table"
  val DB1_DRIVER = "spark.db1.driver"
  val DB1_TYPE = "spark.db1.type"

  val USER2 = "spark.db2.user"
  val PASSWORD2 = "spark.db2.user"
  val DB2_HOST = "spark.db2.host"
  val DB2_PORT = "spark.db2.port"
  val DB2_DATAB = "spark.db2.db"
  val DB2_TABLE= "spark.db2.table"
  val DB2_DRIVER = "spark.db2.driver"
  val DB2_TYPE = "spark.db2.type"

  val DB3_TABLE= "spark.db3.table"
  val DB3_TABLE_MODE= "spark.db3.table_mode"

  val QUERY="spark.query"
};
