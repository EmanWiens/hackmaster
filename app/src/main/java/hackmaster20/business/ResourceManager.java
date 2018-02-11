package hackmaster20.business;

import hackmaster20.objects.CardClass;
import hackmaster20.objects.PlayerClass;
import hackmaster20.objects.ResourceClass;
import hackmaster20.presentation.DrawToScreen;

/**
 * Created by Owner on 2/6/2018.
 */

public class ResourceManager {
    private static DrawToScreen mainAct;
    public ResourceManager(DrawToScreen mainActivity) {
        mainAct = mainActivity;
    }

    public static void drawPlayerResource(PlayerClass player) {
        mainAct.drawPlayerResource(player);
    }

    public static void applyTurnRate(PlayerClass p,boolean test) {
        p.addMinerRate();
        p.addCSpeedRate();
        p.addBotnetRate();

        if (!test) {
            drawPlayerResource(p);
        }
    }

    public static void applyCard(boolean player1Turn, PlayerClass p1, PlayerClass p2, CardClass card,boolean test) {
        if (player1Turn)
            ApplyCardToPlayer(p1, p2, card, test);
        else
            ApplyCardToPlayer(p2, p1, card, test);
    }

    private static void ApplyCardToPlayer(PlayerClass p1, PlayerClass p2, CardClass card, boolean test) {
        if(card.getCardResource().getPlayerR() != null) {
            p1.addResources(card.getCardResource().getPlayerR());

//            p1.addHealth(card.getCardResource().getPlayerR().getHealth());
//            p2.addHealth(card.getCardResource().getEnemyR().getHealth());
        }
        if(card.getCardResource().getEnemyR() != null) {
            p2.addResources(card.getCardResource().getEnemyR());

//            p2.addHealth(card.getCardResource().getPlayerR().getHealth());
//            p1.addHealth(card.getCardResource().getEnemyR().getHealth());
        }
        if (!test) {
            drawPlayerResource(p1);
            drawPlayerResource(p2);
        }
    }
}
