package hackmastertest.persistenceTest;

import org.junit.Before;
import org.junit.Test;

import hackmaster.objects.PlayerStatsSaves;

import static junit.framework.Assert.fail;

public class PlayerDataAccessTest {
    private PlayerStatsSaves playerStats;

    @Before
    public void Setup() {
        playerStats = new PlayerStatsSaves();
    }

    // TODO STUB TEST
    @Test
    public void testDB() {
        fail();
    }
}
