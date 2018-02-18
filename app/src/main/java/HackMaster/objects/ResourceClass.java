package HackMaster.objects;

public class ResourceClass {
    private int health = 0; // 0 if not used
    private int hCoin = 0;
    private int hCoinRate = 0;
    private int botnet = 0;
    private int botnetRate = 0;
    private int cpuRate = 0;
    private int cpu = 0;

    public ResourceClass(int health, int hCoin, int hCoinRate, int botnet, int botnetRate, int cpu, int cpuRate) {
        this.health = health;
        this.hCoin = hCoin;
        this.hCoinRate = hCoinRate;
        this.botnet = botnet;
        this.botnetRate = botnetRate;
        this.cpu = cpu;
        this.cpuRate = cpuRate;
    }

    public String toString() {
        String strung = "";
        if (health != 0)
            strung +="\nHealth: "+health;
        if (hCoinRate != 0)
            strung +="\nHCoin Rate: "+ hCoinRate;
        if (hCoin != 0)
            strung +="\nHackCoin: " + hCoin;
        if (botnetRate != 0)
            strung +="\nBotnet gen.: " + botnetRate;
        if (botnet != 0)
            strung +="\nBotnet: "+ botnet;
        if (cpuRate != 0)
            strung +="\nCPU Rate: "+ cpuRate;
        if (cpu != 0)
            strung +="\nCPU: "+ cpu;
        return strung;
    }

    public String minerToString() {
        return "\nHackCoin Rate: " + hCoinRate +
                "\n----\nHackCoin: " + hCoin;
    }

    public String cSpeedToString() {
        return "\nCPU Rate: " + cpuRate +
                "\n----\nCPU: " + cpu;
    }

    public String botnetToString() {
        return "\nBotnet gen.: " + botnetRate +
                "\n----\nBotnet: " + botnet;
    }

    public void addResources(ResourceClass add) {
        hCoin += add.hCoin;
        hCoinRate += add.hCoinRate;
        cpuRate += add.cpuRate;
        cpu += add.cpu;
        botnet += add.botnet;
        botnetRate += add.botnetRate;
        health += add.health;
    }

    public void addHealth(int add) { health += add; }
    public void addHCoin(int add) { hCoin += add; }
    public void addHCoinRate(int add) { hCoinRate += add; }
    public void addBotnet(int add) { botnet += add; }
    public void addBotnetRate(int add) { botnetRate += add; }
    public void addCpuRate(int add) { cpuRate += add; }
    public void addCpu(int add) { cpu += add; }

    public void increaseHcoinByRate() {
        hCoin += hCoinRate;
    }
    public void increaseCpuByRate() {
        cpu += cpuRate;
    }
    public void increaseBotnetByRate() {
        botnet += botnetRate;
    }

    public int getHealth() { return health; }
    public int gethCoin() { return hCoin; }
    public int gethCoinRate() { return hCoinRate; }
    public int getBotnet() { return botnet; }
    public int getBotnetRate() { return botnetRate; }
    public int getCpuRate() { return cpuRate; }
    public int getCpu() { return cpu; }


}
