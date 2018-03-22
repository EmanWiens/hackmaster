package hackmaster.business;

import java.util.ArrayList;

import hackmaster.application.Services;
import hackmaster.objects.PlayerStats;
import hackmaster.persistence.PlayerDataAccessInterface;

public class StatsManager {

    private ArrayList<PlayerStats> playerList;
    private PlayerDataAccessInterface playerDataAccess;
    private PlayerStats activePlayer;

    public StatsManager() {
        playerDataAccess = Services.getPlayerDataAccess();
        playerList = new ArrayList<>();
        updateList();
        if(playerList.size() != 0) activePlayer = playerList.get(0);
    }

    public PlayerStats getActivePlayer () { return activePlayer; }
    public void setActivePlayer (PlayerStats newPlayer) { activePlayer = newPlayer; }
    public ArrayList<PlayerStats> getPlayerList () { return playerList; }

    public String addWin() {
        String result = null;
        activePlayer.addWin();
        result = playerDataAccess.updatePlayer(activePlayer);
        updateList();
        return result;
    }

    public String addLoss() {
        String result;
        activePlayer.addLoss();
        result = playerDataAccess.updatePlayer(activePlayer);
        updateList();
        return result;
    }

    public String addPlayer(String name) {
        String result;
        PlayerStats player = new PlayerStats(0, name, 0, 0, 0, 0);
        result = playerDataAccess.insertPlayer(player);
        updateList();
        return result;
    }
    public String removePlayer(PlayerStats player) {
        String result;
        if (playerList.size() == 1) {
            result = "Can't remove last player";
        } else {
            result = playerDataAccess.removePlayer(player.getPlayerID());
            updateList();
            if (player.equals(activePlayer)) activePlayer = playerList.get(0);
        }
        return result;
    }
    private void updateList() {
        String eMsg = playerDataAccess.getPlayerSequential(playerList);
        if (eMsg != null) System.out.println(eMsg);
    }
}
