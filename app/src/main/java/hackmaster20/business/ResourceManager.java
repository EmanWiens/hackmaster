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
        ResourceClass resource = player.getResources();
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
        if (player1Turn) {
            p1.addResources(card.getCardResource().getPlaterR());
            if (!test) {
                drawPlayerResource(p1);
            }
        }
        else {
            p2.addResources(card.getCardResource().getEnemyR());
            if (!test) {
                drawPlayerResource(p2);
            }
        }
    }
}
