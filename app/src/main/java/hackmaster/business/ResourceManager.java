package hackmaster.business;

import hackmaster.objects.CardClass;
import hackmaster.objects.PlayerClass;

public abstract class ResourceManager {
    public static void applyTurnRate(PlayerClass player) {
        player.increaseHcoinByRate();
        player.increaseCSpeedByRate();
        player.increaseBotnetByRate();
    }

    public static void applyCard(boolean player1Turn, PlayerClass player1, PlayerClass player2, CardClass card) {
        if (player1Turn) {
            ApplyCardToPlayer(player1, player2, card);
        } else {
            ApplyCardToPlayer(player1, player2, card);
        }
    }

    private static void ApplyCardToPlayer(PlayerClass player1, PlayerClass player2, CardClass card) {
        if(card.getPlayerR() != null) {
            player1.addResources(card.getPlayerR());
        }
        if(card.getEnemyR() != null) {
            player2.addResources(card.getEnemyR());
        }
    }
}
