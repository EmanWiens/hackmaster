package hackmaster.objects;

public class CardClass {
    private String name;
    private String description;
    private ResourceClass player;
    private ResourceClass enemy;
    private String type;
    private int cardID;

    public CardClass(int id) {
        this.cardID = id;
    }

    public CardClass(int id, String name, String type, String d, ResourceClass p, ResourceClass e) {
        this.cardID = id;
        this.name = name;
        this.type = type;
        description = d;
        player = p;
        enemy = e;
    }
    public String toString() {
        String strung;

        strung = name + "\nType:" + type;
        strung += "\n" + resString();

        return strung;
    }
    public ResourceClass getPlayerR() { return player; }
    public ResourceClass getEnemyR() { return enemy; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getID() { return cardID; }
    public String getDescription() { return description; }
    public String resString() {
        String strung = "\nResources\n";

        if (player != null)
            strung += "Player:" + player.toString();
        strung+="\n----\n";
        if (enemy != null)
            strung += "Enemy:" + enemy.toString();

        return strung;
    }
    public boolean equals(Object object) {
        boolean result;
        CardClass card;

        result = false;
        if (object instanceof CardClass) {
            card = (CardClass) object;
            if (card.cardID == cardID) {
                result = true;
            }
        }

        return result;
    }
}
