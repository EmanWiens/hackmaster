package hackmaster20.persistence;

/**
 * Created by Owner on 1/29/2018.
 */

public class playerStatsDatabase {
    private static int totalWins = 0;
    private static int totalLoses = 0;
    private static int totalGames = 0;

    public playerStatsDatabase() { }

    public static void addWin() { totalWins++; totalGames++; }
    public static void addLoss() { totalLoses++; totalGames++; }

    public static double getWinLossRatio() { return totalWins/totalLoses; }
}
