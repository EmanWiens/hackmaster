package hackmaster20.persistence;

/**
 * Created by Owner on 1/29/2018.
 */

public class cardDatabase {
    // make some card specs for making cards
    public static enum CardType { Attack,Defense,Upgrade };
    public static enum CardResource { CryptoCoin,CPU,Botnet };
    public static enum CardResourceRate { GPUMiner, CoreRate, infectionRate }
}
