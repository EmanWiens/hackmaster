package hackmaster20.business;

import hackmaster20.objects.CardClass;
import hackmaster20.objects.CardResource;
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
        ResourceClass resource = player.getResource();
        mainAct.drawPlayerResource(player);
    }

    public static void applyTurnRate(PlayerClass p) {
        p.addMinerRate();
        p.addCSpeedRate();
        p.addBotnetRate();

        drawPlayerResource(p);
    }

    public static void applyCard(boolean player1Turn, PlayerClass p1, PlayerClass p2, CardClass card) {
        if (player1Turn) {
            p1.addResources(card.getCardResource().getPlaterR());
            drawPlayerResource(p1);
        }
        else {
            p2.addResources(card.getCardResource().getEnemyR());
            drawPlayerResource(p2);
        }
    }
}
