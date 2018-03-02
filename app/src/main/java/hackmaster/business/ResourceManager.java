package hackmaster.business;

import hackmaster.objects.CardClass;
import hackmaster.objects.PlayerClass;

public class ResourceManager {
    public ResourceManager() {

    }

    public static void applyTurnRate(PlayerClass p) {
        p.increaseHcoinByRate();
        p.increaseCSpeedByRate();
        p.increaseBotnetByRate();
    }

    public static void applyCard(boolean player1Turn, PlayerClass p1, PlayerClass p2, CardClass card) {
        if (player1Turn)
            ApplyCardToPlayer(p1, p2, card);
        else
            ApplyCardToPlayer(p2, p1, card);
    }

    private static void ApplyCardToPlayer(PlayerClass p1, PlayerClass p2, CardClass card) {
        if(card.getPlayerR() != null) {
            p1.addResources(card.getPlayerR());
        }
        if(card.getEnemyR() != null) {
            p2.addResources(card.getEnemyR());
        }
    }
}
