package hackmaster.business;

import hackmaster.application.Services;
import hackmaster.objects.PlayerStatsSaves;
import hackmaster.persistence.PlayerDataAccessInterface;

public abstract class PlayerManager {
    private static PlayerStatsSaves playerOne;
    private static PlayerStatsSaves PlayerTwo;
    private static PlayerStatsSaves[] leaderBoards;
    private static PlayerDataAccessInterface playerDataAccess;

    public static void initPlayer(String playerName) {
        int playerID;
        playerDataAccess = Services.getPlayerDataAccess();
        playerID = playerDataAccess.insertPlayer(playerName);
        playerOne = playerDataAccess.getPlayerRandom(playerID);
        System.out.println(playerID);
    }

    public static int getPlayerOneID() { return playerOne.getPlayerID(); }
    public static String getPlayerOneName() { return playerOne.getName(); }
}
