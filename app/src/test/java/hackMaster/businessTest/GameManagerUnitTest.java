package hackMaster.businessTest;

import org.junit.Test;

import hackmaster20.business.GameManager;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Owner on 2/3/2018.
 */

public class GameManagerUnitTest {
    @Test
    public void testSetupSingleGame() {
        GameManager manager;

        // TODO call set up single player and make sure that all the variables are set
        // TODO write the test functions for while the game is running
        // TODO tests for all the in game functions
    }
    @Test
    public void SingleGame_isCorrect() {
        GameManager.setUpSingleGame();
        //TODO
       // System.out.println(GameManager.getPlayerNum().getName());
      //  assertEquals("The name of  player should be", "HackerMan", GameManager.getPlayerNum().getName());
    }
    @Test
    public void getPlayerTest()
    {
        assertEquals(0,GameManager.getPlayerNum());
        assertNotNull(GameManager.getPlayerNum());
    }
    @Test
    public void playerTurnTest()
    {
       // GameManager.playerTurn("test", GameManager.getPlayer1());
       // assertEquals(,);
        assertNotNull(GameManager.getPlayerNum());
    }


}
