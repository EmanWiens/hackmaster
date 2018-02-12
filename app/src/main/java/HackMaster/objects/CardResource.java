package HackMaster.objects;

public class CardResource {
    private ResourceClass player;
    private ResourceClass enemy;

    public CardResource(ResourceClass p, ResourceClass e) {
        player = p;
        enemy = e;
    }

    public String toString() {
        String strung = "\nResources\n";

        if (player != null)
            strung += "Player:" + player.toString();
        strung+="\n----\n";
        if (enemy != null)
            strung += "Enemy:" + enemy.toString();

        return strung;
    }

    public ResourceClass getPlayerR() { return player; }
    public ResourceClass getEnemyR() { return enemy; }

}
