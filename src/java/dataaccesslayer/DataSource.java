package dataaccesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * @author Tai Nguyen
 * The DataSource class provides a single database connection for the application.
 * It manages the creation of a connection to the database using JDBC.
 * 
 */
public class DataSource {

    private Connection connection = null;
    // TODO:  Initialize the url string variable properly.  No need to load the
    //        JDBC URL, username, and password from a properties file.
    private final String url = "jdbc:mysql://localhost:3306/peertutor?useSSL=false&allowPublicKeyRetrieval=true";
    private String username = "CST8288";
    private String password = "Manhtai1";
/**
 * Constructs a new DataSource object.
 */
    public DataSource() {
    }

    /*
     * Only use one connection for this application, prevent memory leaks.
     */
    /**
     *
     * Creates and returns a database connection using the specified JDBC URL, username, and password.
     * If a connection already exists, a warning message is printed, and the existing connection is returned.
     * @return The Connection object representing the database connection
     */
    public Connection createConnection() {
        
      try{
          if (connection != null) {
                System.out.println("Cannot create new connection, one exists already");
            } else {
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
      }
      return connection;
    }

}
