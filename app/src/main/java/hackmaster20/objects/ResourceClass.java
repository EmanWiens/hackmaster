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

    public int getHealth() { return health; }
    public int gethCoin() { return hCoin; }
    public int gethCoinRate() { return hCoinRate; }
    public int getBotnet() { return botnet; }
    public int getBotnetRate() { return botnetRate; }
    public int getCpuRate() { return cpuRate; }
    public int getTerraFlops() { return terraFlops; }

    public void addHCoin(int add) { hCoin += add; }
    public void addHCoinRate(int add) { hCoinRate += add; }
    public void addBotnet(int add) { botnet += add; }
    public void addBotnetRate(int add) { botnetRate += add; }
    public void addCpuRate(int add) { cpuRate += add; }
    public void addTerraFlops(int add) { terraFlops += add; }

    public String toString() {
        String strung = "";

        if (health != 0)
            strung +="\nHealth:"+health;
        if (hCoin != 0)
            strung +="\nHCoin:"+ hCoin;
        if (hCoinRate != 0)
            strung +="\nCPU:"+ hCoinRate;
        if (botnet != 0)
            strung +="\nBotnet:"+botnet;
        if (botnetRate != 0)
            strung +="\nMiner:"+ botnetRate;
        if (cpuRate != 0)
            strung +="\nTeraflops:"+ cpuRate;
        if (terraFlops != 0)
            strung +="\nBotRate:"+ terraFlops;

        return strung;
    }

    public void addMinerRate() {
        hCoin += hCoinRate;
    }
    public void addCSpeedRate() {
        cpuRate += terraFlops;
    }
    public void addBotnetRate() {
        botnet += botnetRate;
    }

    public void addResources(ResourceClass add) {
        hCoin += add.hCoin;
        hCoinRate += add.hCoinRate;
        cpuRate += add.cpuRate;
        terraFlops += terraFlops;
        botnet += add.botnet;
        botnetRate += botnetRate;
        health += add.health;
    }
}
