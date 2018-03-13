package hackmaster.objects;

import java.util.ArrayList;
import hackmaster.business.Game;

public class PlayerClass {
    private String name;
    private ResourceClass resources = null;
    private CardClass[] hand;
    private int playerId;

    public PlayerClass(int id, String name, ResourceClass resources, CardClass[] cards) {
        this.name = name;
        hand = cards;
        this.resources = resources;
        playerId = id;
    }

    public CardClass[] playableCards() {
        ArrayList<CardClass> playable = new ArrayList<CardClass>();

        for (int i = 0; i < cardsSize(); i++) {
            if (Game.checkCard(i, this)) {
                playable.add(getCard(i));
            }
        }

        return playable.toArray(new CardClass[0]);
    }

    public String minerToString() {
        return resources.minerToString();
    }
    public String cSpeedToString() {
        return resources.cSpeedToString();
    }
    public String botnetToString() {
        return resources.botnetToString();
    }

    public void increaseHcoinByRate() {
        resources.increaseHcoinByRate();
    }
    public void increaseCSpeedByRate() {
        resources.increaseCpuByRate();
    }
    public void increaseBotnetByRate() {
        resources.increaseBotnetByRate();
    }

    public void addHealth(int health) {resources.addHealth(health);}

    public void addResources(ResourceClass addRes) {
        resources.addResources(addRes);
        resourceLimit();
    }

    private void resourceLimit () {
        if(resources.getBotnetRate() < 1) resources.setBotnetRate(1);
        if(resources.gethCoinRate() < 1) resources.sethCoinRate(1);
        if(resources.getCpuRate() < 1) resources.setCpuRate(1);
        if(resources.getBotnet() < 0) resources.setBotnet(0);
        if(resources.gethCoin() < 0) resources.sethCoin(0);
        if(resources.getCpu() < 0) resources.setCpu(0);
    }

    public void setCard(int index, CardClass card) {
        if(index < hand.length) {
            hand[index] = card;
        } else {
            System.out.println("Index " + index + " is out of bounds");
        }
    }
    public ResourceClass getResources() { return resources; }
    public static int getCardIndex(int id, CardClass[] hand) {
        int j=-1;
        for (int i = 0; i < hand.length; i++)
            if (id == hand[i].getID())
                j=i;
        return j;
    }

    public String playerHealthToString() {
        return "Health: " + getHealth() + "%";
    }
    public int getId() { return playerId; }
    public CardClass[] getCards() { return hand; }
    public int cardsSize() { return hand.length; }
    public CardClass getCard(int i) { return hand[i]; }
    public String getName() { return name; }
    public int getHealth() { return resources.getHealth(); }
}
