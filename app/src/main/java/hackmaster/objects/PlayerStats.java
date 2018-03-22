package hackmaster.objects;

public class PlayerStats {

    private String playerName;
    private int totalWins;
    private int totalLoses;
    private int totalGames;
    private int level;

    private int playerID;

    public PlayerStats(int id) {
        this.playerID = id;
    }

    public PlayerStats(int playerID, String playerName, int totalWins, int totalLoses, int totalGames, int level) {
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

    public double getWinLossRatio() {
        double result = totalWins;
        if (totalLoses != 0) {result /= totalLoses;}
        return result;
    }

    public boolean equals(Object object) {
        boolean result;
        PlayerStats player;

        result = false;
        if (object instanceof PlayerStats) {
            player = (PlayerStats) object;
            if (player.playerID == playerID) {
                result = true;
            }
        }

        return result;
    }

    public int getPlayerID() { return playerID; }
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
    }
}