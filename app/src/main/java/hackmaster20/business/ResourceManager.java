package hackmaster20.business;

import hackmaster20.objects.PlayerClass;
import hackmaster20.objects.ResourceClass;

/**
 * Created by Owner on 1/29/2018.
 */

public class ResourceManager {
    private static ResourceClass playerR;
    private static ResourceClass enemyR;

    public ResourceManager(ResourceClass p, ResourceClass e) {
        playerR = p;
        enemyR = e;
    }

    public static  ResourceClass getPlayerR() { return playerR; }
    public static  ResourceClass getEnemyR() { return enemyR; }


    public  String toString() {
        String strung = "ResourceManager String\n";

        if (playerR != null)
            strung += "player:" + playerR.toString();
        if (enemyR != null)
            strung += "\nenemy:" + enemyR.toString();

        return strung;
    }
}
