package hackmaster.persistence;

import java.util.List;

import hackmaster.objects.PlayerStatsSaves;

public interface PlayerDataAccessInterface {

    /**
     * Gets a list of all players in the database
     * @return a list of all players in the database
     */
    List<PlayerStatsSaves> getPlayersList();

    /**
     * Gets a list of names of all players in the database
     * @return a list of names of all player in the database
     */
    List<String> getPlayersNamesList();

    /**
     * Retrieves a player from the database with the name given as parameter
     * @param playerName the name of the player to retrieve from the database
     * @return The workout with name playerName, or null if no player exists with that name
     */
    PlayerStatsSaves getPlayer(int playerID);

    // TODO geyPlayer win, loss, level

}
