package hackmaster20.persistence;

/**
 * Created by Owner on 1/29/2018.
 */

public class PlayerStatsSaves {
    private static int totalWins = 0;
    private static int totalLoses = 0;
    private static int totalGames = 0;
    private static int level = 0;

    public PlayerStatsSaves() { }

    public static void addWin() { totalWins++; totalGames++; }
    public static void addLoss() { totalLoses++; totalGames++; }
    public static int getLevel() { return level; }
    public static void addLevel() { level++; }

    public static double getWinLossRatio() { return totalWins/totalLoses; }
}
