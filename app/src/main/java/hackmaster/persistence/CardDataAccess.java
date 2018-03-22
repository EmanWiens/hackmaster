package hackmaster.persistence;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import hackmaster.objects.CardClass;
import hackmaster.objects.ResourceClass;

public class CardDataAccess implements CardDataAccessInterface {
    private Statement statement;
    private ResultSet resultSet;
    private int updateCount;

    public void open(Statement statement) { this.statement = statement; }

    public void close() {
        try {
            statement.close();
        } catch (Exception e) {
            DataAccessObject.processSQLError(e);
        }
    }

    @Override
    public String getCardSequential(List<CardClass> cardResult) {
        CardClass card;
        int myID = -1;
        String myName, myType, myDesc;
        int myPHealth = 0, myPCoin = 0, myPCoinRate = 0, myPBotnet = 0, myPBotnetRate = 0, myPCPU = 0, myPCPURate = 0;
        int myEHealth = 0, myECoin = 0, myECoinRate = 0, myEBotnet = 0, myEBotnetRate = 0, myECPU = 0, myECPURate = 0;

        String result = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM CARDS");
        }
        catch (Exception e) {
            DataAccessObject.processSQLError(e);
        }
        try {
            while (resultSet.next()) {
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
        catch (Exception e) {
            result = e.getMessage();
            DataAccessObject.processSQLError(e);
        }
        return result;
    }

    @Override
    public String getRandomDeck(List<CardClass> cardResult, Random random) {
        String emsg = getCardSequential(cardResult);
        for (int i = cardResult.size() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            CardClass card = cardResult.get(index);
            cardResult.set(index, cardResult.get(i));
            cardResult.set(i, card);
        }
        return emsg;
    }

    @Override
    public ArrayList<CardClass> getCardRandom(int cardID) {
        ArrayList<CardClass> card = new ArrayList<CardClass>();
        int myID = -1;
        String myName, myType, myDesc;
        int myPHealth = 0, myPCoin = 0, myPCoinRate = 0, myPBotnet = 0, myPBotnetRate = 0, myPCPU = 0, myPCPURate = 0, myEHealth = 0;
        int myECoin = 0, myECoinRate = 0, myEBotnet = 0, myEBotnetRate = 0, myECPU = 0, myECPURate = 0;

        try {
            resultSet = statement.executeQuery("Select * from Cards where CardID=" + cardID);
            while (resultSet.next()) {
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
                card.add(new CardClass(myID, myName, myType, myDesc,
                        new ResourceClass(myPHealth, myPCoin, myPCoinRate, myPBotnet, myPBotnetRate, myPCPU, myPCPURate),
                        new ResourceClass(myEHealth, myECoin, myECoinRate, myEBotnet, myEBotnetRate, myECPU, myECPURate)));
            }
            resultSet.close();
        } catch (Exception e) {
            DataAccessObject.processSQLError(e);
        }
        return card;
    }

    @Override
    public String insertCard(CardClass card) {
        String values;

        String result = null;
        try {
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
            updateCount = statement.executeUpdate("Insert into Cards " +" Values(" +values +")");
            result = DataAccessObject.checkWarning(statement, updateCount);
        }
        catch (Exception e) {
            result = e.getMessage();
            DataAccessObject.processSQLError(e);
        }
        return result;
    }

    @Override
    public String updateCard(CardClass card) {
        String values;
        String where;
        String cmdString;
        String result = null;
        try {
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
            updateCount = statement.executeUpdate(cmdString);
            result = DataAccessObject.checkWarning(statement, updateCount);
        }
        catch (Exception e) {
            DataAccessObject.processSQLError(e);
        }
        return result;
    }

    @Override
    public String removeCard(int cardID) {
        int values;
        String result = null;
        try {
            values = cardID;
            updateCount = statement.executeUpdate("Delete from Cards where CardID=" +values);
            result = DataAccessObject.checkWarning(statement, updateCount);
        }
        catch (Exception e) {
            result = e.getMessage();
            DataAccessObject.processSQLError(e);
        }
        return result;
    }
}

