package hackmaster.objects;

public class CardClass {
    private String name;
    private String description;
    private ResourceClass player;
    private ResourceClass enemy;
    private String type;
    private int id = 0;

    public CardClass(int id) {
        this.id = id;
    }

    public CardClass(int id, String name, String type, String d, ResourceClass p, ResourceClass e) {
        this.id = id;
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
    public int getID() { return id; }
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
    public boolean equals() {
        boolean sameCard = false;

        // TODO write the function that compares if two cards are equal

        return sameCard;
    }
}
