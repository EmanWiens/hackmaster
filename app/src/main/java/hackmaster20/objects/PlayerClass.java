package hackmaster20.objects;

import hackmaster20.business.DeckManager;

/**
 * Created by Owner on 1/29/2018.
 */

public class PlayerClass {
    private String name;
    private ResourceClass resources = null;
    private int health;
    private CardClass[] hand;
    private int playerId;

    public PlayerClass(int id, String name, ResourceClass resources, CardClass[] cards) {
        this.name = name;
        hand = cards;
        this.resources = resources;
        playerId = id;
        health = 100;
    }

    public int getId() { return playerId; }
    public CardClass[] getCards() { return hand; }
    public int cardsSize() { return hand.length; }
    public CardClass getCard(int i) { return hand[i]; }
    public String getName() { return name; }

    public void addHealth(int add) { health += add; }

    public void setCard(int index, CardClass card) { hand[index] = card; }
    public int findPlayerCardIndex(String name) {
        return DeckManager.getCardIndex(name, hand);
    }
    public ResourceClass getResources() { return resources; }

    public String minerToString() {
        return "\nMiners: " + resources.gethCoinRate() +
                "\n----\nHackCoin: " + resources.gethCoin();
    }

    public String cSpeedToString() {
        return "\nCore Rate: " + resources.getCpuRate() +
                "\n----\nTerraflops: " + resources.getTerraFlops();
    }

    public String botnetToString() {
        return "\nBotnet gen.: " + resources.getBotnetRate() +
                "\n----\nBotnet: " + resources.getBotnet();
    }

    public void addMinerRate() {
        resources.increaseHcoinByRate();
    }
    public void addCSpeedRate() {
        resources.increaseCSpeedByRate();
    }
    public void addBotnetRate() {
        resources.increaseBotnetByRate();
    }
    public void addResources(ResourceClass addRes) {
        resources.addResources(addRes);
        health += addRes.getHealth();
    }

    public int getHealth() { return health; }
}
