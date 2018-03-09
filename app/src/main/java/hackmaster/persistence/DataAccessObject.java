package hackmaster.persistence;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import hackmaster.objects.CardClass;
import hackmaster.objects.ResourceClass;

public class DataAccessObject implements DBInterface {
    private Statement st1;
    private Connection c1;
    private ResultSet rs2, rs3;

    private String dbName;
    private String dbType;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessObject(String dbName)
    {
        this.dbName = dbName;
    }

    public void open(String dbPath)
    {
        String url;
        try
        {
            // Setup for HSQL
            dbType = "HSQLDB";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath; // stored on disk mode
            c1 = DriverManager.getConnection(url, "SA", "");
            st1 = c1.createStatement();

        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        System.out.println("Opened " +dbType +" database " +dbPath);
    }

    public void close()
    {
        try
        {	// commit all changes to the database
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        System.out.println("Closed " +dbType +" database " +dbName);
    }


    public String updateCard(CardClass card)
    {
        String values;
        String where;

        result = null;
        try
        {
            // Should check for empty values and not update them
            values = "Name='" +card.getName()
                    +"', Type='" +card.getType()
                    +"', Description='" +card.getDescription()
                    +"', PHealth=" +card.getPlayerR().getHealth()
                    +", PCoin=" +card.getPlayerR().gethCoin()
                    +", PCoinRate=" +card.getPlayerR().gethCoinRate()
                    +", PBotnet=" +card.getPlayerR().getBotnet()
                    +", PBotnetRate=" +card.getPlayerR().getBotnetRate()
                    +", PCPU=" +card.getPlayerR().getCpu()
                    +", PCPURate=" +card.getPlayerR().getCpuRate();
            where = "where CardID=" +card.getID();
            cmdString = "Update Cards " +" Set " +values +" " +where;
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    public String removeCard(CardClass card)
    {
        int values;

        result = null;
        try
        {
            values = card.getID();
            cmdString = "Delete from Cards where CardID=" +values;
            //System.out.println(cmdString);
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }


    public String checkWarning(Statement st, int updateCount)
    {
        String result;

        result = null;
        try
        {
            SQLWarning warning = st.getWarnings();
            if (warning != null)
            {
                result = warning.getMessage();
            }
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        if (updateCount != 1)
        {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    /**
     * Get a new statement from the database connection
     * @return a new statement
     */
    public Statement getNewStatement() {
        Statement statement = null;
        try {
            statement = c1.createStatement();
        } catch(Exception e) {
            processSQLError(e);
        }
        return statement;
    }

    public String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }
}
