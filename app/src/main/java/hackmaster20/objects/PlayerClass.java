package hackmaster20.objects;

import hackmaster20.business.DeckManager;
import hackmaster20.business.GameManager;
import hackmaster20.business.ResourceManager;

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
        return "\nMiners: " + resource.getBotnetRate() +
                "\nHCoin: " + resource.gethCoin();
    }

    public String cSpeedToString() {
        return "\nCore Rate: " + resource.getCpuRate() +
                "\nCPU: " + resource.gethCoinRate();
    }

    public String botnetToString() {
        return "\nBotnet gen.: " + resource.getTerraFlops() +
                "\nBotnet: " + resource.getBotnet();
    }

    public void addMinerRate() {
        resource.addMinerRate();
    }
    public void addCSpeedRate() {
        resource.addCSpeedRate();
    }
    public void addBotnetRate() {
        resource.addBotnetRate();
    }
    public void addResources(ResourceClass addRes) {
        resource.addResources(addRes);
    }
}
