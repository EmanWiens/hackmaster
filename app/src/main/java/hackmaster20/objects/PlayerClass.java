package hackmaster20.objects;

import hackmaster20.persistence.playerStatsDatabase;

/**
 * Created by Owner on 1/29/2018.
 */

public class PlayerClass {
    private String name;
    private ResourceClass resource = null;
    private int health = 100;
    private int maxHealth = 100;
    private CardClass[] hand;

    public PlayerClass(String n, ResourceClass r, CardClass[] c) {
        name = n;
        hand = c;
        resource = r;
    }
}
