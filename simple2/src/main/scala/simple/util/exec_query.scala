
package simple.util
import java.sql.{DriverManager, ResultSet}


class sql_executor (configuration:load_conf) {
  var texto: String = "select 1"

  def set_texto(valor:String): Unit =
  {
    texto->valor
  }

  def execute() {
    val driver = configuration.driver1

    // Change to Your Database Config
    val conn_str = configuration.url


    Class.forName(driver)
    //prop object to store variables for the JDBC connection, only will be used if the exec_query class is used
    val prop = new java.util.Properties
    prop.setProperty("user",configuration.user)


    // Setup the connection
    val conn = DriverManager.getConnection(conn_str,prop)
    try {
      // Configure to be Read Only
      val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)

      // Execute Query
      val rs = statement.executeQuery(texto)
  println("borradas" + rs.getFetchSize)
      rs.close()
    }
    catch {
      case e : Throwable => e.printStackTrace
        println("error")
    }
    finally {
      conn.close
    }
  }
}