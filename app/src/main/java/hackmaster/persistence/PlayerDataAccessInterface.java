package hackmaster.persistence;

import java.util.List;
import hackmaster.objects.PlayerStatsSaves;

public interface PlayerDataAccessInterface extends DBComponentInterface{
    List<PlayerStatsSaves> getPlayersList();
    List<String> getPlayersNamesList();
    PlayerStatsSaves getPlayer(int playerID);
    int addNewPlayer(PlayerStatsSaves newPlayer);
    String removePlayer(int playerID);
    void updatePlayerStats(PlayerStatsSaves newPlayer);
    // TODO geyPlayer win, loss, level
}
