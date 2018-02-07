package hackmaster20.business;

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
}
