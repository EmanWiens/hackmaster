package hackmaster.objects;

public class PlayerStatsSaves {
    private String playerName = "Player 1";
    private int totalWins = 0;
    private int totalLoses = 0;
    private int totalGames = 0;
    private int level = 0;
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

    public void addWin() {
        totalWins++;
        totalGames++;
    }

    public void addLoss() {
        totalLoses++;
        totalGames++;
    }

    public void addLevel() {
        level++;
    }

    public void setPlayerName(String name) {
        playerName = name;
    } //test this (marc)

    public double getWinLossRatio() {
        double result = totalWins;
        if (totalLoses != 0) {
            result /= totalLoses;
        }
        return result;
    }

    public int getLevel() {
        return level;
    }
    public int getWin() {
        return totalWins;
    }
    public int getLoss() { return totalLoses; }
    public int getTotalGames() { return totalGames; }
    public String getName() {
        return playerName;
    } //test this (marc)
}