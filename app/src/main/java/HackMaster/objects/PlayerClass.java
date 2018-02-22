package HackMaster.objects;

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

    public int findPlayerCardIndex(String name) {
        int index = 0;
        boolean found = false;
        while(!found && index < hand.length) {
            if(name.equals(hand[index].getName())){
                found = true;
            } else {
                index++;
            }
        }
        if(!found){
            index = -1;
        }
        return index;
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

    public void increaseHcoinByRate() {resources.increaseHcoinByRate();}
    public void increaseCSpeedByRate() {resources.increaseCpuByRate();}
    public void increaseBotnetByRate() {
        resources.increaseBotnetByRate();
    }
    public void addResources(ResourceClass addRes) {
        resources.addResources(addRes);
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
    public void setResources(ResourceClass res) { resources = res; }
    public int getId() { return playerId; }
    public CardClass[] getCards() { return hand; }
    public int cardsSize() { return hand.length; }
    public CardClass getCard(int i) { return hand[i]; }
    public String getName() { return name; }
    public int getHealth() { return resources.getHealth(); }
}
