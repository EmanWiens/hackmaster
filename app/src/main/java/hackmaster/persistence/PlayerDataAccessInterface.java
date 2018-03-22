package hackmaster.persistence;

import java.util.ArrayList;
import java.util.List;
import hackmaster.objects.PlayerStats;

public interface PlayerDataAccessInterface extends DBComponentInterface{
    String getPlayerSequential(List<PlayerStats> playerResult);
    String getPlayersNamesList(List<String> playerResult);
    ArrayList<PlayerStats> getPlayerRandom(int playerID);
    String insertPlayer(PlayerStats player);
    String updatePlayer(PlayerStats player);
    String removePlayer(int playerID);
}
