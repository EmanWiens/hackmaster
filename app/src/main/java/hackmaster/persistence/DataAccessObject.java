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

    private ArrayList<CardClass> cards;

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
            dbType = "HSQL";
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

    public String getCardSequential(List<CardClass> cardResult)
    {
        CardClass card;
        int myID = -1;
        String myName = EOF;
        String myType = EOF;
        String myDesc = EOF;
        int myPHealth = 0;
        int myPCoin = 0;
        int myPCoinRate = 0;
        int myPBotnet = 0;
        int myPBotnetRate = 0;
        int myPCPU = 0;
        int myPCPURate = 0;
        int myEHealth = 0;
        int myECoin = 0;
        int myECoinRate = 0;
        int myEBotnet = 0;
        int myEBotnetRate = 0;
        int myECPU = 0;
        int myECPURate = 0;

        result = null;
        try
        {
            cmdString = "Select * from Cards";
            rs2 = st1.executeQuery(cmdString);
            //ResultSetMetaData md2 = rs2.getMetaData();
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        try
        {
            while (rs2.next())
            {
                myID = rs2.getInt("CardID");
                myName = rs2.getString("Name");
                myType = rs2.getString("Type");
                myDesc = rs2.getString("Description");
                myPHealth = rs2.getInt("PHealth");
                myPCoin = rs2.getInt("PCoin");
                myPCoinRate = rs2.getInt("PCoinRate");
                myPBotnet = rs2.getInt("PBotnet");
                myPBotnetRate = rs2.getInt("PBotnetRate");
                myPCPU = rs2.getInt("PCPU");
                myPCPURate = rs2.getInt("PCPURate");
                myEHealth = rs2.getInt("EHealth");
                myECoin = rs2.getInt("ECoin");
                myECoinRate = rs2.getInt("ECoinRate");
                myEBotnet = rs2.getInt("EBotnet");
                myEBotnetRate = rs2.getInt("EBotnetRate");
                myECPU = rs2.getInt("ECPU");
                myECPURate = rs2.getInt("ECPURate");
                card = new CardClass(myID, myName, myType, myDesc,
                        new ResourceClass(myPHealth, myPCoin, myPCoinRate, myPBotnet, myPBotnetRate, myPCPU, myPCPURate),
                        new ResourceClass(myEHealth, myECoin, myECoinRate, myEBotnet, myEBotnetRate, myECPU, myECPURate));
                cardResult.add(card);
            }
            rs2.close();
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }

        return result;
    }

    public ArrayList<CardClass> getCardRandom(CardClass newCard)
    {
        CardClass card;
        int myID = -1;
        String myName = EOF;
        String myType = EOF;
        String myDesc = EOF;
        int myPHealth = 0;
        int myPCoin = 0;
        int myPCoinRate = 0;
        int myPBotnet = 0;
        int myPBotnetRate = 0;
        int myPCPU = 0;
        int myPCPURate = 0;
        int myEHealth = 0;
        int myECoin = 0;
        int myECoinRate = 0;
        int myEBotnet = 0;
        int myEBotnetRate = 0;
        int myECPU = 0;
        int myECPURate = 0;
        cards = new ArrayList<CardClass>();
        try
        {
            cmdString = "Select * from Cards where CardID=" + newCard.getID();
            rs3 = st1.executeQuery(cmdString);
            // ResultSetMetaData md2 = rs3.getMetaData();
            while (rs3.next())
            {
                myID = rs3.getInt("CardID");
                myName = rs3.getString("Name");
                myType = rs3.getString("Type");
                myDesc = rs3.getString("Description");
                myPHealth = rs3.getInt("PHealth");
                myPCoin = rs3.getInt("PCoin");
                myPCoinRate = rs3.getInt("PCoinRate");
                myPBotnet = rs3.getInt("PBotnet");
                myPBotnetRate = rs3.getInt("PBotnetRate");
                myPCPU = rs3.getInt("PCPU");
                myPCPURate = rs3.getInt("PCPURate");
                myEHealth = rs3.getInt("EHealth");
                myECoin = rs3.getInt("ECoin");
                myECoinRate = rs3.getInt("ECoinRate");
                myEBotnet = rs3.getInt("EBotnet");
                myEBotnetRate = rs3.getInt("EBotnetRate");
                myECPU = rs3.getInt("ECPU");
                myECPURate = rs3.getInt("ECPURate");
                card = new CardClass(myID, myName, myType, myDesc,
                        new ResourceClass(myPHealth, myPCoin, myPCoinRate, myPBotnet, myPBotnetRate, myPCPU, myPCPURate),
                        new ResourceClass(myEHealth, myECoin, myECoinRate, myEBotnet, myEBotnetRate, myECPU, myECPURate));
                cards.add(card);
            }
            rs3.close();
        } catch (Exception e)
        {
            processSQLError(e);
        }
        return cards;
    }

    public String insertCard(CardClass card)
    {
        String values;

        result = null;
        try
        {
            values = card.getID()
                    +", '" +card.getName()
                    +"', '" +card.getType()
                    +"', '" +card.getDescription()
                    +"', " +card.getPlayerR().getHealth()
                    +", " +card.getPlayerR().gethCoin()
                    +", " +card.getPlayerR().gethCoinRate()
                    +", " +card.getPlayerR().getBotnet()
                    +", " +card.getPlayerR().getBotnetRate()
                    +", " +card.getPlayerR().getCpu()
                    +", " +card.getPlayerR().getCpuRate();
            cmdString = "Insert into Cards " +" Values(" +values +")";
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

    public String processSQLError(Exception e)
    {
        String result = "*** SQL Error: " + e.getMessage();

        // Remember, this will NOT be seen by the user!
        e.printStackTrace();

        return result;
    }
}
