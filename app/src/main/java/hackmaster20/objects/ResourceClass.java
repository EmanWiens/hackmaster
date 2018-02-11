package hackmaster20.objects;

/**
 * Created by Owner on 1/29/2018.
 */

public class ResourceClass {
    private int health = 0; // 0 if not used
    private int hCoin = 0;
    private int hCoinRate = 0;
    private int botnet = 0;
    private int botnetRate = 0;
    private int cpuRate = 0;
    private int terraFlops = 0;

    public ResourceClass(int h, int crypt, int c, int bot, int gpu, int coreR, int infecR) {
        health = h;
        hCoin = crypt;
        hCoinRate = c;
        botnet = bot;
        botnetRate = gpu;
        cpuRate = coreR;
        terraFlops = infecR;
    }

    public String toString() {
        String strung = "";

        if (health != 0)
            strung +="\nHealth: "+health;

        if (hCoinRate != 0)
            strung +="\nMiners: "+ hCoinRate;
        if (hCoin != 0)
            strung +="\nHackCoin: " + hCoin;

        if (botnetRate != 0)
            strung +="\nBotnet gen.: "+botnetRate;
        if (botnet != 0)
            strung +="\nBotnet: "+ botnet;

        if (cpuRate != 0)
            strung +="\nCore Rate: "+ cpuRate;
        if (terraFlops != 0)
            strung +="\nTerraflops: "+ terraFlops;

        return strung;
    }

    public void addResources(ResourceClass add) {
        hCoin += add.hCoin;
        hCoinRate += add.hCoinRate;
        cpuRate += add.cpuRate;
        terraFlops += add.terraFlops;
        botnet += add.botnet;
        botnetRate += add.botnetRate;
        health += add.health;
    }

    public int getHealth() { return health; }
    public int gethCoin() { return hCoin; }
    public int gethCoinRate() { return hCoinRate; }
    public int getBotnet() { return botnet; }
    public int getBotnetRate() { return botnetRate; }
    public int getCpuRate() { return cpuRate; }
    public int getTerraFlops() { return terraFlops; }

    public void addHealth(int add) { health += add; }
    public void addHCoin(int add) { hCoin += add; }
    public void addHCoinRate(int add) { hCoinRate += add; }
    public void addBotnet(int add) { botnet += add; }
    public void addBotnetRate(int add) { botnetRate += add; }
    public void addCpuRate(int add) { cpuRate += add; }
    public void addTerraFlops(int add) { terraFlops += add; }

    public void increaseHcoinByRate() {
        hCoin += hCoinRate;
    }
    public void increaseCSpeedByRate() {
        terraFlops += cpuRate;
    }
    public void increaseBotnetByRate() {
        botnet += botnetRate;
    }
}
