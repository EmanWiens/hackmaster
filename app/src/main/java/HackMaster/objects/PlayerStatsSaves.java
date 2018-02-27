package HackMaster.objects;

/**
 * Created by Owner on 1/29/2018.
 */
public class PlayerStatsSaves {
    private static int totalWins = 0;
    private static int totalLoses = 0;
    private static int totalGames = 0;
    private static int level = 0;

    public PlayerStatsSaves() {
        totalWins = 0;
        totalLoses = 0;
        totalGames =0;
        level = 0;
    }

    public static void addWin() { totalWins++; totalGames++; }
    public static void addLoss() { totalLoses++; totalGames++; }
    public static void addLevel() { level++; }

    public static double getWinLossRatio() {
        double result = totalWins;
        if (totalLoses != 0) {
            result /= totalLoses;
        }
        return result;
    }
    public static int getLevel() { return level; }
    public static int getWin() { return totalWins; }
}