package hackmaster.persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLWarning;

public class DataAccessObject implements DBInterface {
    private static final String DB_PATH_PREFIX = "jdbc:hsqldb:file:";
    private static final String DB_JDBC_DRIVER = "org.hsqldb.jdbcDriver";
    private static final String SHUTDOWN_CMD = "shutdown compact";

    private Connection connection;

    private String dbName;
    private String dbType = "HSQLDB";

    public DataAccessObject(String dbName) { this.dbName = dbName; }

    public void open(String dbPath) {
        String url;
        try {
            Class.forName(DB_JDBC_DRIVER).newInstance();
            url = DB_PATH_PREFIX + dbPath; // stored on disk mode
            connection = DriverManager.getConnection(url, "SA", "");
        }
        catch (Exception e) {
            processSQLError(e);
        }
        System.out.println("Opened " +dbType +" database " +dbPath);
    }

    public void close() {
        Statement statement;
        try {	// commit all changes to the database
            statement = connection.createStatement();
            statement.executeQuery(SHUTDOWN_CMD);
            statement.close();
            connection.close();
        }
        catch (Exception e) {
            processSQLError(e);
        }
        System.out.println("Closed " +dbType +" database " +dbName);
    }

    /**
     * Get a new statement from the database connection
     * @return a new statement
     */
    public Statement getNewStatement() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch(Exception e) {
            processSQLError(e);
        }
        return statement;
    }

    static String checkWarning(Statement st, int updateCount) {
        String result = null;
        try {
            SQLWarning warning = st.getWarnings();
            if (warning != null) {
                result = warning.getMessage();
            }
        }
        catch (Exception e) {
            processSQLError(e);
        }
        if (updateCount == 0) {
            result = "No rows are affected by the operation";
        }
        return result;
    }

    static void processSQLError(Exception e)
    {
        e.printStackTrace();
    }
}
