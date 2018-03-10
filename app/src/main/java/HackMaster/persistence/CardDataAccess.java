
package hackmaster.persistence;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;

import hackmaster.objects.CardClass;
import hackmaster.objects.ResourceClass;

public class CardDataAccess implements CardDataAccessInterface {
    private Statement statement;
    private ResultSet resultSet;

    public void open(Statement statement) { this.statement = statement; }

    public void close() {
        try {
            statement.close();
        } catch (Exception e) {
            processSQLError(e);
        }
    }

    public String getCardSequential(List<CardClass> cardResult)
    {
        CardClass card;
        int myID = -1;
        String myName, myType, myDesc;
        int myPHealth = 0, myPCoin, myPCoinRate, myPBotnet, myPBotnetRate, myPCPU, myPCPURate;
        int myEHealth = 0, myECoin, myECoinRate, myEBotnet, myEBotnetRate, myECPU, myECPURate;

        String result = null;
        try
        {
            resultSet = statement.executeQuery("SELECT * FROM CARDS");
        }
        catch (Exception e)
        {
            processSQLError(e);
        }
        try
        {
            while (resultSet.next())
            {
                myID = resultSet.getInt("CardID");
                myName = resultSet.getString("Name");
                myType = resultSet.getString("Type");
                myDesc = resultSet.getString("Description");
                myPHealth = resultSet.getInt("PHealth");
                myPCoin = resultSet.getInt("PCoin");
                myPCoinRate = resultSet.getInt("PCoinRate");
                myPBotnet = resultSet.getInt("PBotnet");
                myPBotnetRate = resultSet.getInt("PBotnetRate");
                myPCPU = resultSet.getInt("PCPU");
                myPCPURate = resultSet.getInt("PCPURate");
                myEHealth = resultSet.getInt("EHealth");
                myECoin = resultSet.getInt("ECoin");
                myECoinRate = resultSet.getInt("ECoinRate");
                myEBotnet = resultSet.getInt("EBotnet");
                myEBotnetRate = resultSet.getInt("EBotnetRate");
                myECPU = resultSet.getInt("ECPU");
                myECPURate = resultSet.getInt("ECPURate");
                card = new CardClass(myID, myName, myType, myDesc,
                        new ResourceClass(myPHealth, myPCoin, myPCoinRate, myPBotnet, myPBotnetRate, myPCPU, myPCPURate),
                        new ResourceClass(myEHealth, myECoin, myECoinRate, myEBotnet, myEBotnetRate, myECPU, myECPURate));
                cardResult.add(card);
            }
            resultSet.close();
        }
        catch (Exception e)
        {
            result = e.getMessage();
            processSQLError(e);
        }
        return result;
    }

    public CardClass getCardRandom(CardClass newCard)
    {
        CardClass card = null;
        int myID = -1;
        String myName, myType, myDesc;
        int myPHealth = 0, myPCoin, myPCoinRate, myPBotnet, myPBotnetRate, myPCPU, myPCPURate, myEHealth;
        int myECoin = 0, myECoinRate, myEBotnet, myEBotnetRate, myECPU, myECPURate;

        try
        {
            resultSet = statement.executeQuery("Select * from Cards where CardID=" + newCard.getID());
            while (resultSet.next())
            {
                myID = resultSet.getInt("CardID");
                myName = resultSet.getString("Name");
                myType = resultSet.getString("Type");
                myDesc = resultSet.getString("Description");
                myPHealth = resultSet.getInt("PHealth");
                myPCoin = resultSet.getInt("PCoin");
                myPCoinRate = resultSet.getInt("PCoinRate");
                myPBotnet = resultSet.getInt("PBotnet");
                myPBotnetRate = resultSet.getInt("PBotnetRate");
                myPCPU = resultSet.getInt("PCPU");
                myPCPURate = resultSet.getInt("PCPURate");
                myEHealth = resultSet.getInt("EHealth");
                myECoin = resultSet.getInt("ECoin");
                myECoinRate = resultSet.getInt("ECoinRate");
                myEBotnet = resultSet.getInt("EBotnet");
                myEBotnetRate = resultSet.getInt("EBotnetRate");
                myECPU = resultSet.getInt("ECPU");
                myECPURate = resultSet.getInt("ECPURate");
                card = new CardClass(myID, myName, myType, myDesc,
                        new ResourceClass(myPHealth, myPCoin, myPCoinRate, myPBotnet, myPBotnetRate, myPCPU, myPCPURate),
                        new ResourceClass(myEHealth, myECoin, myECoinRate, myEBotnet, myEBotnetRate, myECPU, myECPURate));
            }
            resultSet.close();
        } catch (Exception e)
        {
            processSQLError(e);
        }
        return card;
    }

    public String insertCard(CardClass card) {
        String values;

        String result = null;
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
            resultSet = statement.executeUpdate("Insert into Cards " +" Values(" +values +")");
            result = checkWarning(st1, updateCount);
        }
        catch (Exception e)
        {
            result = processSQLError(e);
        }
        return result;
    }

    private String processSQLError(Exception e) {
        String result = "*** SQL Error: " + e.getMessage();
        e.printStackTrace();
        return result;
    }
}

