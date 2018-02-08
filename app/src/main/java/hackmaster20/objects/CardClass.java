package hackmaster20.objects;

/**
 * Created by Owner on 1/29/2018.
 */

public class CardClass {
    private String name;
    private String description;
    private CardResource resManager;
    private String type;
    private int id = 0;

    public CardClass(int id, String name, String type, String d, CardResource cardResource) {
        this.id = id;
        this.name = name;
        this.type = type;
        description = d;
        resManager = cardResource;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public int getID() { return id; }
    public String getDescription() { return description; }
    public String toString() {
        String strung;

        strung = name + "\ntype:" + type;
        strung += "\n" + resManager.toString();

        return strung;
    }
}
