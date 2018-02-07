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
    private int playerId;

    public PlayerClass(int id, String name, ResourceClass resources, CardClass[] cards) {
        this.name = name;
        hand = cards;
        resource = resources;
        playerId = id;
    }

    public int getId() { return playerId; }
    public CardClass[] getCards() { return hand; }
    public int cardsSize() { return hand.length; }
    public CardClass getCard(int i) { return hand[i]; }
    public void setCard(int index, CardClass card) { hand[index] = card; }
    public int findPlayerCardIndex(String name) {
        return DeckManager.getCardIndex(name, hand);
    }
    public CardClass getCardByIndex(int index) { return hand[index]; }
    public ResourceClass getResource() { return resource; }

    public String minerToString() {
        return "\nMiners: " + resource.getGpuMiner() +
                "\nHCoin: " + resource.getCryptoCoin();
    }

    public String cSpeedToString() {
        return "\nCore Rate: " + resource.getCoreRate() +
                "\nCPU: " + resource.getCpu();
    }

    public String botnetToString() {
        return "\nBotnet gen.: " + resource.getInfectionRate() +
                "\nBotnet: " + resource.getBotnet();
    }
}
