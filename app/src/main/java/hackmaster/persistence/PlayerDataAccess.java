package hackmaster.persistence;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hackmaster.objects.PlayerStatsSaves;

public class PlayerDataAccess implements PlayerDataAccessInterface {
    private Statement statement;
    private ResultSet resultSet;
    private int updateCount;

    public void open(Statement statement) {
        this.statement = statement;
    }

    public void close() {
        try {
            statement.close();
        }
        catch (Exception e) {
            DataAccessObject.processSQLError(e);
        }
    }

    @Override
    public String getPlayerSequential(List<PlayerStatsSaves> playerResult){
        PlayerStatsSaves player = null;

        String playerName;
        int id, win, loss, games, level;

        String result = null;

        try {
            // get the list of players from the db
            resultSet = statement.executeQuery("SELECT * FROM PLAYERS");
        } catch(Exception e) {
            DataAccessObject.processSQLError(e);
        }
        try {
            while (resultSet.next()) {
                id = resultSet.getInt("PLAYERID");
                playerName = resultSet.getString("NAME");
                win = resultSet.getInt("WINS");
                loss = resultSet.getInt("LOSSES");
                games = resultSet.getInt("GAMESPLAYED");
                level = resultSet.getInt("LEVEL");
                player = new PlayerStatsSaves(id,playerName,win,loss,games,level);
                playerResult.add(player);
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
    public String getPlayersNamesList(List<String> playerResult){
        String playerName;
        String result = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM PLAYERS");
            while (resultSet.next()) {
                playerName = resultSet.getString("NAME");
                playerResult.add(playerName);
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
    public ArrayList<PlayerStatsSaves> getPlayerRandom(int playerID){
        String playerName;
        int id, win, loss, games, level;
        ArrayList<PlayerStatsSaves> player = new ArrayList<PlayerStatsSaves>();

        try {
            resultSet = statement.executeQuery("SELECT * FROM PLAYERS WHERE PLAYERID =" + playerID);
            while (resultSet.next()) {
                id = resultSet.getInt("PLAYERID");
                playerName = resultSet.getString("NAME");
                win = resultSet.getInt("WINS");
                loss = resultSet.getInt("LOSSES");
                games = resultSet.getInt("GAMESPLAYED");
                level = resultSet.getInt("LEVEL");
                player.add(new PlayerStatsSaves(id, playerName, win, loss, games, level));
                resultSet.close();
            }
        }
        catch (Exception e) {
            DataAccessObject.processSQLError(e);
        }
        return player;
    }

    @Override
    public String insertPlayer(PlayerStatsSaves newPlayer) {
        String values;
        String result = null;
        int newPlayerID = -1;
        try {
            values = newPlayer.getName()
                    +", '" + newPlayer.getWin()
                    +"', '" + newPlayer.getLoss()
                    +"', '" + newPlayer.getTotalGames()
                    +"', " + newPlayer.getLevel();
            updateCount = statement.executeUpdate("INSERT INTO PLAYERS ( PLAYERID, NAME, WINS, LOSSES, GAMESPLAYED, LEVEL)" +" VALUES( NULL, " +values +")");
            result = DataAccessObject.checkWarning(statement, updateCount);
            if(updateCount == 1) {
                resultSet = statement.getGeneratedKeys();
                if(resultSet.next()) {
                    newPlayerID = resultSet.getInt(1);
                }
            } else newPlayerID = -1;
        }
        catch (Exception e) {
            result = e.getMessage();
            DataAccessObject.processSQLError(e);
        }
        return result;
    }

    @Override
    public String updatePlayer(PlayerStatsSaves newPlayer) {
        String values;
        String where;
        String cmdString;
        String result = null;
        try {
            values = newPlayer.getName()
                    +", '" + newPlayer.getWin()
                    +"', '" + newPlayer.getLoss()
                    +"', '" + newPlayer.getTotalGames()
                    +"', " + newPlayer.getLevel();
            where = "where PlayerID=" +newPlayer.getPlayerID();
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
    public String removePlayer(int playerID) {
        String result = null;
        try {
            updateCount = statement.executeUpdate("DELETE FROM PLAYERS WHERE CARDID =" + playerID);
            result = DataAccessObject.checkWarning(statement, updateCount);
        }
        catch (Exception e) {
            result = e.getMessage();
            DataAccessObject.processSQLError(e);
        }
        return result;
    }

}
