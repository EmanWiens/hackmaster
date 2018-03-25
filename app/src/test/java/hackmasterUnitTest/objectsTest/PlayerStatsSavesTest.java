package hackmasterUnitTest.objectsTest;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import hackmasterOG.objects.PlayerStatsSaves;

public class PlayerStatsSavesTest {
    private PlayerStatsSaves playerStats;

    @Before
    public void setup() {
        playerStats = new PlayerStatsSaves(0,"Tester",0,0,0,0);
    }


    @Test
    public void testLevelUp() {
        assertEquals(0, playerStats.getLevel());

        playerStats.addLevel();
        assertEquals(1, playerStats.getLevel());

        playerStats.addLevel();
        assertEquals(2, playerStats.getLevel());

        playerStats.addLevel();
        playerStats.addLevel();
        playerStats.addLevel();
        assertEquals(5, playerStats.getLevel());
    }
    @Test
    public void testWinAndLose() {
        playerStats.addWin();
        assertEquals(1.00, playerStats.getWinLossRatio(), 0.0001);
        assertEquals(1, playerStats.getTotalGames());
        playerStats.addLoss();
        playerStats.addLoss();
        assertEquals(0.50, playerStats.getWinLossRatio(), 0.0001);
        assertEquals(3, playerStats.getTotalGames());
    }

    @Test
    public void testInitPlayerStats() {
        assertNotNull(playerStats);
        assertEquals(0, playerStats.getWin());
        assertEquals(0, playerStats.getLevel());
    }

    @Test
    public void testGetWin() {
        assertEquals(0, playerStats.getWin());
        playerStats.addWin();
        assertEquals(1, playerStats.getWin());

        playerStats.addWin();
        assertEquals(2, playerStats.getWin());

        playerStats.addWin();
        playerStats.addWin();
        assertEquals(4, playerStats.getWin());
    }

    @Test
    public void testGetLoss() {
        assertEquals(0, playerStats.getLoss());
        playerStats.addLoss();
        assertEquals(1, playerStats.getLoss());

        playerStats.addLoss();
        assertEquals(2, playerStats.getLoss());

        playerStats.addLoss();
        playerStats.addLoss();
        assertEquals(4, playerStats.getLoss());
    }

    @Test
    public void testEquals() {
        PlayerStatsSaves otherPlayer = new PlayerStatsSaves(0,"Not Tester",123,321,444,55);
        assertEquals(otherPlayer, playerStats);
    }

}