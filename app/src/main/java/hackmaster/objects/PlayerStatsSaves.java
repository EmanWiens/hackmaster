package hackmaster.objects;


public class PlayerStatsSaves {
    private static String playerName = "Player 1";
    private static int totalWins = 0;
    private static int totalLoses = 0;
    private static int totalGames = 0;
    private static int level = 0;
    private int playerID;

    public PlayerStatsSaves() {
        totalWins = 0;
        totalLoses = 0;
        totalGames = 0;
        level = 0;
        playerID = 0;
    }

    public PlayerStatsSaves(int playerID, String playerName, int totalWins, int totalLoses, int totalGames, int level) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.totalWins = totalWins;
        this.totalLoses = totalLoses;
        this.totalGames = totalGames;
        this.level = level;
    }

    public static void addWin() {
        totalWins++;
        totalGames++;
    }

    public static void addLoss() {
        totalLoses++;
        totalGames++;
    }

    public static void addLevel() {
        level++;
    }

    public static void setPlayerName(String name) {
        playerName = name;
    } //test this (marc)

    public static double getWinLossRatio() {
        double result = totalWins;
        if (totalLoses != 0) {
            result /= totalLoses;
        }
        return result;
    }

    public static int getLevel() {
        return level;
    }

    public static int getWin() {
        return totalWins;
    }

    public static String getName() {
        return playerName;
    } //test this (marc)
}