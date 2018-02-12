package hackmaster20.objects;

import hackmaster20.objects.ResourceClass;

/**
 * Created by Owner on 1/29/2018.
 */

public class CardResource {
    private ResourceClass player;
    private ResourceClass enemy;

    public CardResource(ResourceClass p, ResourceClass e) {
        player = p;
        enemy = e;
    }

    public ResourceClass getPlayerR() { return player; }
    public ResourceClass getEnemyR() { return enemy; }

    public String toString() {
        String strung = "\nResources\n";

        if (player != null)
            strung += "player:" + player.toString();
        strung+="\n";
        if (enemy != null)
            strung += "\nenemy:" + enemy.toString();

        return strung;
    }
}
