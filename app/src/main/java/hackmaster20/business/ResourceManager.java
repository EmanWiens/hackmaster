package hackmaster20.business;

import hackmaster20.objects.ResourceClass;

/**
 * Created by Owner on 1/29/2018.
 */

public class ResourceManager {
    private ResourceClass player;
    private ResourceClass enemy;

    public ResourceManager(ResourceClass p, ResourceClass e) {
        player = p;
        enemy = e;
    }

    public ResourceClass getPlaterR() { return player; }
    public ResourceClass getEnemyR() { return enemy; }

    // TODO make the function that adds up all the data fields (calls function from resource class)

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
