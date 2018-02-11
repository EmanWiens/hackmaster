package hackMaster.persistenceTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Owner on 2/3/2018.
 */

public class PlayerStatsSavesTest {
    private hackmaster20.objects.PlayerStatsSaves playerStats;

    @Before
    public void Setup() {
        playerStats = new  hackmaster20.objects.PlayerStatsSaves();
    }



    // TODO test the other playerStats functions
    @Test
    public void testLevelUp() {
        playerStats.addLevel();
        assertEquals("The Level should be 1",1, playerStats.getLevel());
    }
    @Test
    public void testWinandLose() {

        assertEquals("The value of win/lose ratio should be 0" ,0.00,playerStats.getLevel(), 0.0001);
        playerStats.addWin();
        assertEquals("The value of win/lose ratio should be 1", 1.00, playerStats.getWinLossRatio(), 0.0001);
        playerStats.addLoss();
        playerStats.addLoss();
        assertEquals("The value of win/lose ratio should be .5", 0.50, playerStats.getWinLossRatio(), 0.0001);
    }

    @Test
    public void testInitPlayerStats() {
        // TODO test initializing the player stats class
        assertNotNull(playerStats);
        assertEquals("The total wins 0.0", 0.0, playerStats.getWin(), 0.01);
        assertEquals("The Level should be 0", 0, playerStats.getLevel());
    }

    @After
    public void tearDown() {
        playerStats = null;
    }

}