package hackmaster.business;

import hackmaster.objects.CardClass;
import hackmaster.objects.PlayerClass;

public class ResourceManager {
    public ResourceManager() {

    }

    public static void applyTurnRate(PlayerClass p, boolean test) {
        p.increaseHcoinByRate();
        p.increaseCSpeedByRate();
        p.increaseBotnetByRate();
    }

    public static void applyCard(boolean player1Turn, PlayerClass p1, PlayerClass p2, CardClass card, boolean test) {
        if (player1Turn)
            ApplyCardToPlayer(p1, p2, card, test);
        else
            ApplyCardToPlayer(p2, p1, card, test);
    }

    private static void ApplyCardToPlayer(PlayerClass p1, PlayerClass p2, CardClass card, boolean test) {
        if(card.getPlayerR() != null) {
            p1.addResources(card.getPlayerR());
        }
        if(card.getEnemyR() != null) {
            p2.addResources(card.getEnemyR());
        }
    }
}
