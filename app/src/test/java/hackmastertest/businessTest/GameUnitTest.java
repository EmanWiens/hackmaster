package hackmastertest.businessTest;

import org.junit.Before;
import org.junit.Test;

import hackmaster.business.DeckManager;
import hackmaster.business.Game;
import hackmaster.business.SetUpGame;
import hackmaster.objects.CardClass;
import hackmaster.objects.ResourceClass;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;


public class GameUnitTest {
    Game testGame;

    @Before
    public void setUp(){
        testGame = SetUpGame.setUpSinglePlayerGame();
    }

    private CardClass[] resetDeck() {
        CardClass[] testDeck = new CardClass[10];
        testDeck[0] = new CardClass(0, "Nothing", "Defense", "Do Nothing",
                new ResourceClass(0, 0, 0, 0, 0,0, 0), null);
        testDeck[1] = new CardClass(0, "Normal card", "Defense", "Costs a normal amount",
                new ResourceClass(0, -1, 0, -1, 0,-1, 0), null);
        testDeck[2] = new CardClass(0, "Expensive Health", "Attack", "Costs a lot of Health",
                new ResourceClass(-2000, 0, 0, 0, 0,0, 0), null);
        testDeck[3] = new CardClass(0, "Expensive HCoin", "Attack", "Costs a lot of HCoin",
                new ResourceClass(0, -2000, 0, 0, 0,0, 0), null);
        testDeck[4] = new CardClass(0, "Expensive BotNet", "Attack", "Costs a lot of Botnet",
                new ResourceClass(0, 0, 0, -2000, 0,0, 0), null);
        testDeck[5] = new CardClass(0, "Expensive GPU", "Attack", "Costs a lot of CPU",
                new ResourceClass(0, 0, 0, 0, 0,-2000, 0), null);
        testDeck[6] = new CardClass(0, "Generate Health", "Attack", "Makes a lot of Health",
                new ResourceClass(2000, 0, 0, 0, 0,0, 0), null);
        testDeck[7] = new CardClass(0, "Generate HCoin", "Attack", "Makes a lot of HCoin",
                new ResourceClass(0, 2000, 0, 0, 0,0, 0), null);
        testDeck[8] = new CardClass(0, "Generate BotNet", "Attack", "Makes a lot of BotNet",
                new ResourceClass(0, 0, 0, 2000, 0,0, 0), null);
        testDeck[9] = new CardClass(0, "Generate CPU", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, 0, 0,2000, 0), null);

        return testDeck;
    }

    @Test
    public void testSetDeck() {
        CardClass[] testDeck = new CardClass[3];
        testDeck[0] = new CardClass(0, "Nothing", "Defense", "Do Nothing",
                new ResourceClass(0, 0, 0, 0, 0,0, 0), null);
        testDeck[1] = new CardClass(0, "Normal card", "Defense", "Costs a normal amount",
                new ResourceClass(0, -1, 0, -1, 0,-1, 0), null);
        testDeck[2] = new CardClass(0, "Expensive Health", "Attack", "Costs a lot of Health",
                new ResourceClass(-2000, 0, 0, 0, 0,0, 0), null);
        testGame.setDeck(testDeck);

        assertEquals(3, testGame.getDeck().length);

        testGame.setDeck(new CardClass[0]);
        assertEquals(0, testGame.getDeck().length);

        testGame.setDeck(resetDeck());
        assertEquals(10, testGame.getDeck().length);

    }

    private void setPlayerHands() {
        testGame.getPlayer1().setCard(0, testGame.getDeckCardAt(0));
        testGame.getPlayer1().setCard(1, testGame.getDeckCardAt(1));
        testGame.getPlayer1().setCard(2, testGame.getDeckCardAt(2));
        testGame.getPlayer1().setCard(3, testGame.getDeckCardAt(3));
        testGame.getPlayer1().setCard(4, testGame.getDeckCardAt(4));
        testGame.getPlayer2().setCard(0, testGame.getDeckCardAt(5));
        testGame.getPlayer2().setCard(1, testGame.getDeckCardAt(6));
        testGame.getPlayer2().setCard(2, testGame.getDeckCardAt(7));
        testGame.getPlayer2().setCard(3, testGame.getDeckCardAt(8));
        testGame.getPlayer2().setCard(4, testGame.getDeckCardAt(9));
    }

    @Test
    public void testSetUpSingleGame() {
        assertEquals("HackerMan", testGame.getPlayer1().getName());
        assertEquals(0, testGame.getPlayer1().getId());
        assertEquals(100, testGame.getPlayer1().getResources().getHealth());
        assertEquals(10, testGame.getPlayer1().getResources().gethCoin());
        assertEquals(2, testGame.getPlayer1().getResources().gethCoinRate());
        assertEquals(10, testGame.getPlayer1().getResources().getBotnet());
        assertEquals(2, testGame.getPlayer1().getResources().getBotnetRate());
        assertEquals(2, testGame.getPlayer1().getResources().getCpuRate());
        assertEquals(10, testGame.getPlayer1().getResources().getCpu());


        assertEquals(1, testGame.getPlayer2().getId());
        assertEquals("Enemy Bot", testGame.getPlayer2().getName());
        assertEquals(100, testGame.getPlayer2().getResources().getHealth());
        assertEquals(10, testGame.getPlayer2().getResources().gethCoin());
        assertEquals(2, testGame.getPlayer2().getResources().gethCoinRate());
        assertEquals(10, testGame.getPlayer2().getResources().getBotnet());
        assertEquals(2, testGame.getPlayer2().getResources().getBotnetRate());
        assertEquals(2, testGame.getPlayer2().getResources().getCpuRate());
        assertEquals(10, testGame.getPlayer2().getResources().getCpu());
    }

    @Test
    public void testPlayCardEvent()
    {
       testGame.playCardEvent(4);
       // is this failing because of the delay?
       assertEquals("Should be Player 1 Turn", 0, testGame.getPlayerNum());
    }

    @Test
    public void testInvalidPlayCardEvent() {
        try {
            testGame.playCardEvent(-1);
            fail("ArrayIndexOutOfBoundsException Expected or RuntimeException Expected");
        } catch ( ArrayIndexOutOfBoundsException exp) {
        } catch ( RuntimeException exp) {
        }
        try {
            testGame.playCardEvent(6);
            fail("ArrayIndexOutOfBoundsException Expected or RuntimeException Expected");
        } catch ( ArrayIndexOutOfBoundsException exp) {
        } catch ( RuntimeException exp) {
        }
    }

    @Test
    public void testCheckCard() {
        resetDeck();
        setPlayerHands();

        assertEquals( true, testGame.checkCard(0, testGame.getPlayer1()));
        assertEquals(true, testGame.checkCard(1, testGame.getPlayer1()));
        assertEquals(false, testGame.checkCard(2, testGame.getPlayer1()));
        assertEquals(false, testGame.checkCard(3, testGame.getPlayer1()));
        assertEquals(false, testGame.checkCard(4, testGame.getPlayer1()));
        assertEquals(false, testGame.checkCard(0, testGame.getPlayer2()));
        assertEquals(true, testGame.checkCard(1, testGame.getPlayer2()));
        assertEquals(true, testGame.checkCard(2, testGame.getPlayer2()));
        assertEquals(true, testGame.checkCard(3, testGame.getPlayer2()));
        assertEquals(true, testGame.checkCard(4, testGame.getPlayer2()));
    }

    @Test
    public void testDiscardCardPlayer1(){
        resetDeck();
        setPlayerHands();
        DeckManager.resetIndex();

        assertEquals("The player card at index 0 should be Nothing ", "Nothing", testGame.getPlayer1().getCard(0).getName());
        assertEquals("The player card at index 1 should be Normal Card", "Normal card", testGame.getPlayer1().getCard(1).getName());
        assertEquals("The player card at index 2 should be Expensive Health", "Expensive Health", testGame.getPlayer1().getCard(2).getName());

        testGame.discardCard(4, testGame.getPlayer1());
        //assertEquals("The card should be at index 0 if the deck",true, testGame.getPlayer1().getCard(4).equals(testGame.getDeckCardAt(0)));
        assertEquals("The player card at index 0 should be Nothing", "Nothing", testGame.getPlayer1().getCard(4).getName());

        testGame.discardCard(3, testGame.getPlayer1());
        //assertEquals("The card should be at index 1 if the deck",true, testGame.getPlayer1().getCard(3).equals(testGame.getDeckCardAt(1)));
        assertEquals("The player card at index4 should have the name Normal card", "Normal card", testGame.getPlayer1().getCard(3).getName());

        testGame.discardCard(2, testGame.getPlayer1());
        //assertEquals("The card should be at index 2 of the deck", true, testGame.getPlayer1().getCard(2).equals(testGame.getDeckCardAt(2)));
        assertEquals("The player card at index 2 should be Expensive Health", "Expensive Health", testGame.getPlayer1().getCard(2).getName());

        testGame.discardCard(1, testGame.getPlayer1());
        //assertEquals("The card should be at index 3 of the deck", true, testGame.getPlayer1().getCard(1).equals(testGame.getDeckCardAt(3)));
        assertEquals("The player card at index 1 should be Expensive HCoin", "Expensive HCoin", testGame.getPlayer1().getCard(1).getName());
    }

    @Test
    public void testCheckStats() {

        testGame.initStats();
        assertNotNull(testGame.getPlayerName());
        assertEquals("The player name should be Player_1", "Player_1", testGame.getPlayerName());
        assertEquals("The Players wins should be 0", 0, testGame.getWin());
        testGame.addWin();
        assertEquals("The Players wins should be 1", 1, testGame.getWin());
        testGame.addLoss();
        assertEquals("The Players wins should be 1", 1, testGame.getWin());
    }
}
