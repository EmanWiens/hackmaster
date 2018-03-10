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

    /**
     * Opens the PlayerStatDataAccess
     * @param statement the statement to use in PlayerStatDataAccess queries
     */
    public void open(Statement statement) {
        this.statement = statement;
    }

    /**
     * Close the PlayerStatDataAccess
     */
    public void close() {
        try {
            statement.close();
        }
        catch (Exception e) {
            DataAccessObject.processSQLError(e);
        }
    }

    /**
     * Gets a list of all players in the database
     * @return a list of all players in the database
     */
    @Override
    public List<PlayerStatsSaves> getPlayersList(){
        ArrayList<PlayerStatsSaves> playerList = new ArrayList<>();
        PlayerStatsSaves player = null;

        String playerName;
        int id, win, loss, games, level;

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
                playerList.add(player);
            }
            resultSet.close();
        }
        catch (Exception e) {
            DataAccessObject.processSQLError(e);
        }
        return playerList;
    }

    /**
     * Gets a list of names of all players in the database
     * @return a list of names of all players in the database
     */
    @Override
    public List<String> getPlayersNamesList(){
        String playerName;
        ArrayList<String> playerList = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT * FROM PLAYERS");
            while (resultSet.next()) {
                playerName = resultSet.getString("NAME");
                playerList.add(playerName);
            }
            resultSet.close();
        }
        catch (Exception e) {
            DataAccessObject.processSQLError(e);
        }
        return playerList;
    }

    /**
     * Retrieves a player from the database with the id given as parameter
     * @param playerID the id of the player to retrieve from the database
     * @return The player with id playerID, or null if no player exists with that id
     */
    @Override
    public PlayerStatsSaves getPlayer(int playerID){
        String playerName;
        int id, win, loss, games, level;
        PlayerStatsSaves player = null;

        try {
            resultSet = statement.executeQuery("SELECT * FROM PLAYERS WHERE PLAYERID =" + playerID);
            id = resultSet.getInt("PLAYERID");
            playerName = resultSet.getString("NAME");
            win = resultSet.getInt("WINS");
            loss = resultSet.getInt("LOSSES");
            games = resultSet.getInt("GAMESPLAYED");
            level = resultSet.getInt("LEVEL");
            player = new PlayerStatsSaves(id,playerName,win,loss,games,level);
            resultSet.close();
        }
        catch (Exception e) {
            DataAccessObject.processSQLError(e);
        }
        return player;
    }

    // DATABASE creates a Unique ID for the newly created player
    @Override
    public int addNewPlayer(PlayerStatsSaves newPlayer) {
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
            DataAccessObject.processSQLError(e);
        }
        return newPlayerID;
    }

    @Override
    public String removePlayer(int playerID) {
        String result = null;
        try {
            updateCount = statement.executeUpdate("DELETE FROM PLAYERS WHERE CARDID =" + playerID);
            result = DataAccessObject.checkWarning(statement, updateCount);
        }
        catch (Exception e) {
            DataAccessObject.processSQLError(e);
        }
        return result;
    }
}
