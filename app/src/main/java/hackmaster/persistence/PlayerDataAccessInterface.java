package hackmaster.persistence;

import java.util.ArrayList;
import java.util.List;
import hackmaster.objects.PlayerStatsSaves;

public interface PlayerDataAccessInterface extends DBComponentInterface{
    String getPlayerSequential(List<PlayerStatsSaves> playerResult);
    String getPlayersNamesList(List<String> playerResult);
    PlayerStatsSaves getPlayerRandom(int playerID);
    int insertPlayer(String playerName);
    String updatePlayer(PlayerStatsSaves player);
    String removePlayer(int playerID);
}
