package hackmaster.persistence;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hackmaster.objects.PlayerStatsSaves;

/**
 * Created by Jansen Lazaro on 2018-03-07.
 */

public class PlayerDataAccess implements PlayerDataAccessInterface {
    private Statement statement;
    private ResultSet resultSet;

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
            processSQLError(e);
        }
    }

    /**
     * Gets a list of all players in the database
     *
     * @return a list of all players in the database
     */
    // TODO Access DB
    @Override
    public List<PlayerStatsSaves> getPlayersList(){
        ArrayList<PlayerStatsSaves> playerList = new ArrayList<>();
        PlayerStatsSaves player;

        String playerName;
        int id, win, loss, games, level;

        try {
            // get the list of players from the db
            resultSet = statement.executeQuery("SELECT * FROM PLAYERS");
        } catch(Exception e) {
            processSQLError(e);
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
            processSQLError(e);
        }
        return playerList;
    }

    /**
     * Gets a list of names of all players in the database
     *
     * @return a list of names of all players in the database
     */
    // TODO Access DB
    @Override
    public List<String> getPlayersNamesList(){
        ArrayList<String> playerList = new ArrayList<>();
        return playerList;
    }

    /**
     * Retrieves a player from the database with the id given as parameter
     *
     * @param playerID the id of the player to retrieve from the database
     * @return The player with id playerID, or null if no player exists with that id
     */
    // TODO Access DB
    @Override
    public PlayerStatsSaves getPlayer(int playerID){
        PlayerStatsSaves player = null;
        return player;
    }

    /**
     * Gets the error message message of an SQL exception and prints the stack trace
     * @param e the exception thrown
     */
    private String processSQLError(Exception e) {
        String result = "*** SQL Error: " + e.getMessage();
        e.printStackTrace();
        return result;
    }
}