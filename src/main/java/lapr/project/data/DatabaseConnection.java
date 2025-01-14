package lapr.project.data;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * @author Eduardo Silva <1201371@isep.ipp.pt>
 */
public class DatabaseConnection {

    /**
     * The connection
     */
    private Connection connection;

    /**
     * The last error registered
     */
    private SQLException error;

    /**
     * @param url of the database
     * @param username of the user
     * @param password of the user
     */
    public DatabaseConnection(String url, String username, String password) {
        try {
            OracleDataSource oracleDataSource = new OracleDataSource();

            oracleDataSource.setURL(url);

            connection = oracleDataSource.getConnection(username, password);

        } catch (SQLException e) {
            error = e;

        }
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        if (connection == null) {
            System.out.println("Error when fetching the connection");
        }
        return connection;
    }

    /**
     * @param error that has been caught
     */
    public void registerError(SQLException error) {
        this.error = error;
    }

    /**
     * @return if the connection is null
     */
    public boolean connectionWorking(){
        return error == null;
    }
}
