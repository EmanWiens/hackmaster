package HackMasterTest.businessTest;

import org.junit.Before;
import org.junit.Test;

import HackMaster.business.GameManager;
import HackMaster.objects.CardClass;
import HackMaster.objects.CardResource;
import HackMaster.objects.PlayerClass;
import HackMaster.objects.ResourceClass;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;


public class GameManagerUnitTest {
    @Before
    public void setUp(){
        GameManager.setUpSingleGame(true);

        resetDeck();
    }

    private void resetDeck() {
        CardClass[] testDeck = new CardClass[10];
        testDeck[0] = new CardClass(0, "Nothing", "Defense", "Do Nothing",
                new CardResource(new ResourceClass(0, 0, 0, 0, 0,0, 0), null));
        testDeck[1] = new CardClass(0, "Normal card", "Defense", "Costs a normal amount",
                new CardResource(new ResourceClass(0, -1, 0, -1, 0,-1, 0), null));
        testDeck[2] = new CardClass(0, "Expensive Health", "Attack", "Costs a lot of Health",
                new CardResource(new ResourceClass(-2000, 0, 0, 0, 0,0, 0), null));
        testDeck[3] = new CardClass(0, "Expensive HCoin", "Attack", "Costs a lot of HCoin",
                new CardResource(new ResourceClass(0, -2000, 0, 0, 0,0, 0), null));
        testDeck[4] = new CardClass(0, "Expensive BotNet", "Attack", "Costs a lot of Botnet",
                new CardResource(new ResourceClass(0, 0, 0, -2000, 0,0, 0), null));
        testDeck[5] = new CardClass(0, "Expensive GPU", "Attack", "Costs a lot of CPU",
                new CardResource(new ResourceClass(0, 0, 0, 0, 0,-2000, 0), null));
        testDeck[6] = new CardClass(0, "Generate Health", "Attack", "Makes a lot of Health",
                new CardResource(new ResourceClass(2000, 0, 0, 0, 0,0, 0), null));
        testDeck[7] = new CardClass(0, "Generate HCoin", "Attack", "Makes a lot of HCoin",
                new CardResource(new ResourceClass(0, 2000, 0, 0, 0,0, 0), null));
        testDeck[8] = new CardClass(0, "Generate BotNet", "Attack", "Makes a lot of BotNet",
                new CardResource(new ResourceClass(0, 0, 0, 2000, 0,0, 0), null));
        testDeck[9] = new CardClass(0, "Generate CPU", "Attack", "Makes a lot of CPU",
                new CardResource(new ResourceClass(0, 0, 0, 0, 0,2000, 0), null));

        GameManager.setDeck(testDeck);
    }

    @Test
    public void testSetUpSingleGame() {
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

    @Test
    public void testCheckCard() {
        resetDeck();

        GameManager.getPlayer1().setCard(0, GameManager.getDeckCardAt(0));
        GameManager.getPlayer1().setCard(1, GameManager.getDeckCardAt(1));
        GameManager.getPlayer1().setCard(2, GameManager.getDeckCardAt(2));
        GameManager.getPlayer1().setCard(3, GameManager.getDeckCardAt(3));
        GameManager.getPlayer1().setCard(4, GameManager.getDeckCardAt(4));
        GameManager.getPlayer2().setCard(0, GameManager.getDeckCardAt(5));
        GameManager.getPlayer2().setCard(1, GameManager.getDeckCardAt(6));
        GameManager.getPlayer2().setCard(2, GameManager.getDeckCardAt(7));
        GameManager.getPlayer2().setCard(3, GameManager.getDeckCardAt(8));
        GameManager.getPlayer2().setCard(4, GameManager.getDeckCardAt(9));
        assertEquals( true, GameManager.checkCard(0, GameManager.getPlayer1()));
        assertEquals(true, GameManager.checkCard(1, GameManager.getPlayer1()));
        assertEquals(false, GameManager.checkCard(2, GameManager.getPlayer1()));
        assertEquals(false, GameManager.checkCard(3, GameManager.getPlayer1()));
        assertEquals(false, GameManager.checkCard(4, GameManager.getPlayer1()));
        assertEquals(false, GameManager.checkCard(0, GameManager.getPlayer2()));
        assertEquals(true, GameManager.checkCard(1, GameManager.getPlayer2()));
        assertEquals(true, GameManager.checkCard(2, GameManager.getPlayer2()));
        assertEquals(true, GameManager.checkCard(3, GameManager.getPlayer2()));
        assertEquals(true, GameManager.checkCard(4, GameManager.getPlayer2()));
    }

    @Test
    public void testDiscard(){
        resetDeck();

        // TODO write the game manager discard tests
    }
}
