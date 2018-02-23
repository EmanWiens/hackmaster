package hackmaster.objects;

/**
 * Created by Owner on 1/29/2018.
 */

public class CardClass {
    private String name;
    private String description;
    private CardResource cardRes;
    private String type;
    private int id = 0;

    public CardClass(int id, String name, String type, String d, CardResource cardResource) {
        this.id = id;
        this.name = name;
        this.type = type;
        description = d;
        cardRes = cardResource;
    }
    public String toString() {
        String strung;

        strung = name + "\nType:" + type;
        strung += "\n" + cardRes.toString();

        return strung;
    }
    public CardResource getCardResource() { return cardRes; }
    public String getName() { return name; }
    public String getType() { return type; }
    public int getID() { return id; }
    public String getDescription() { return description; }

    public boolean equals() {
        boolean sameCard = false;

        // TODO write the function that compares if two cards are equal

        return sameCard;
    }
}
