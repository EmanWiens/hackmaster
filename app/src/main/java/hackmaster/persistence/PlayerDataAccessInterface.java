package hackmaster.persistence;

import java.util.List;

import hackmaster.objects.PlayerStatsSaves;

public interface PlayerDataAccessInterface extends DBComponentInterface{
    //List<PlayerStatsSaves> getPlayersList();
    List<String> getPlayersNamesList();
    //PlayerStatsSaves getPlayer(int playerID);
    int addNewPlayer(PlayerStatsSaves newPlayer);
    String removePlayer(int playerID);

    void addWin(int playerID);
    void addLoss(int playerID);
    void addLevel(int playerID);
    void setPlayerName(String playerName, int playerID);
    int getPlayerID(String playerName);
    int getLevel(int playerID);
    int getWin(int playerID);
    int getLoss(int playerID);
    int getTotalGames(int playerID);
    String getName(int playerID);
}
