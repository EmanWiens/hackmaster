package hackmasterAcceptanceTests;

import java.util.Random;

public class StubRandom extends Random {
    public StubRandom() {}
    public int nextInt(int range) { return 0; }
}
