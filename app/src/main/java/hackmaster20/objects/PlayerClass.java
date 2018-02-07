package hackmaster20.objects;

import hackmaster20.business.DeckManager;

/**
 * Created by Owner on 1/29/2018.
 */

public class PlayerClass {
    private String name;
    private ResourceClass resource = null;
    private int health;
    private CardClass[] hand;

    public PlayerClass(String name, ResourceClass resources, CardClass[] cards) {
        this.name = name;
        hand = cards;
        resource = resources;
    }

    public CardClass[] getCards() { return hand; }
    public int cardsSize() { return hand.length; }
    public CardClass getCard(int i) { return hand[i]; }
    public void setCard(int index, CardClass card) { hand[index] = card; }

    // TODO write a listener function that listens to which card you clicked
    public int findPlayerCardIndex(String name) {
        return DeckManager.getCardIndex(name, hand);
    }

    public CardClass getCardByIndex(int index) { return hand[index]; }
}
