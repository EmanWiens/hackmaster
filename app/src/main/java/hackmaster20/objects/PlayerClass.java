package hackmaster20.objects;

/**
 * Created by Owner on 1/29/2018.
 */

public class PlayerClass {
    private String name;
    private ResourceClass resource = null;
    private int health = 100;
    private int maxHealth = 100;
    private CardClass[] hand;

    public PlayerClass(String name, ResourceClass resources, CardClass[] cards) {
        this.name = name;
        hand = cards;
        resource = resources;
    }

    public CardClass getCard(int i) { return hand[i]; }
    public void setCard(int index, CardClass card) { hand[index] = card; }

    // TODO write a listener function that listens to which card you clicked
}
