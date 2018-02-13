package HackMasterTest.businessTest;

import org.junit.Before;
import org.junit.Test;

import HackMaster.business.GameManager;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;


public class GameManagerUnitTest {
    @Before
    public void setUp(){
        GameManager.setUpSingleGame(true);
    }
    @Test
    public void testSetUpSingleGame() {
        // TODO write the test functions for while the game is running
        // TODO tests for all the in game functions
        assertEquals("The name of player should be HackerMan", "HackerMan", GameManager.getPlayer1().getName());
        assertEquals("The ID of player should be 0", 0, GameManager.getPlayer1().getId());
        assertEquals("The health of player should be 100", 100, GameManager.getPlayer1().getResources().getHealth());
        assertEquals("The hCoin of player should be 2",2, GameManager.getPlayer1().getResources().gethCoin());
        assertEquals("The hCoinRate of player should be 2",2, GameManager.getPlayer1().getResources().gethCoinRate());
        assertEquals("The botnet of player should be 2", 2, GameManager.getPlayer1().getResources().getBotnet());
        assertEquals("The botnetRate of player should be 2", 2, GameManager.getPlayer1().getResources().getBotnetRate());
        assertEquals("The CPURate of player should be 2", 2, GameManager.getPlayer1().getResources().getCpuRate());
        assertEquals("The terraFlops of player should be 2", 2, GameManager.getPlayer1().getResources().getCpu());


        assertEquals("The ID of player should be 1", 1, GameManager.getPlayer2().getId());
        assertEquals("The name of player should be Enemy Bot", "Enemy Bot", GameManager.getPlayer2().getName());
        assertEquals("The health of player should be 100", 100, GameManager.getPlayer2().getResources().getHealth());
        assertEquals("The hCoin of player should be 2",2, GameManager.getPlayer2().getResources().gethCoin());
        assertEquals("The hCoinRate of player should be 2",2, GameManager.getPlayer2().getResources().gethCoinRate());
        assertEquals("The botnet of player should be 2", 2, GameManager.getPlayer2().getResources().getBotnet());
        assertEquals("The botnetRate of player should be 2", 2, GameManager.getPlayer2().getResources().getBotnetRate());
        assertEquals("The CPURate of player should be 2", 2, GameManager.getPlayer2().getResources().getCpuRate());
        assertEquals("The terraFlops of player should be 2", 2, GameManager.getPlayer2().getResources().getCpu());
    }

    @Test
    public void testPlayerTestNotNull()
    {
        assertNotNull(GameManager.getPlayerNum());
    }
    @Test
    public void testPlayCardEvent()
    {
       GameManager.playCardEvent(4);
       assertEquals("Should be Player 1 Turn", 0, GameManager.getPlayerNum());
    }


    @Test
    public void testInvalidPlayCardEvent(){
        try {
            GameManager.playCardEvent(-1);
            fail("ArrayIndexOutOfBoundsException Expected");
        } catch ( ArrayIndexOutOfBoundsException exp) {
        }
        try {
            GameManager.playCardEvent(6);
            fail("ArrayIndexOutOfBoundsException Expected");
        } catch ( ArrayIndexOutOfBoundsException exp) {
        }
    }

}
