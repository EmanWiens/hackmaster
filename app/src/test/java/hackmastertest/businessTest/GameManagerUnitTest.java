package hackmastertest.businessTest;

import org.junit.Before;
import org.junit.Test;

import hackmaster.business.DeckManager;
import hackmaster.business.GameManager;
import hackmaster.objects.CardClass;
import hackmaster.objects.CardResource;
import hackmaster.objects.ResourceClass;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;


public class GameManagerUnitTest {
    @Before
    public void setUp(){
        GameManager.setUpSingleGame(true);
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

    private void setPlayerHands() {
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
       // is this failing because of the delay?
       assertEquals("Should be Player 1 Turn", 0, GameManager.getPlayerNum());
    }


    @Test
    public void testInvalidPlayCardEvent(){
        // TODO fix these functions (Fail should be in catch)
        try {
            GameManager.playCardEvent(-1);
            fail("ArrayIndexOutOfBoundsException Expected or RuntimeException Expected");
        } catch ( ArrayIndexOutOfBoundsException exp) {
        } catch ( RuntimeException exp) {
        }
        try {
            GameManager.playCardEvent(6);
            fail("ArrayIndexOutOfBoundsException Expected or RuntimeException Expected");
        } catch ( ArrayIndexOutOfBoundsException exp) {
        } catch ( RuntimeException exp) {
        }
    }

    @Test
    public void testCheckCard() {
        resetDeck();
        setPlayerHands();

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
    public void testDiscardCardPlayer1(){
        resetDeck();
        setPlayerHands();
        DeckManager.resetIndex();

        assertEquals("The player card at index 0 should be Nothing ", "Nothing", GameManager.getPlayer1().getCard(0).getName());
        assertEquals("The player card at index 1 should be Normal Card", "Normal card", GameManager.getPlayer1().getCard(1).getName());
        assertEquals("The player card at index 2 should be Expensive Health", "Expensive Health", GameManager.getPlayer1().getCard(2).getName());

        GameManager.discardCard(5, GameManager.getPlayer1());
        assertEquals("The card should be at index 0 if the deck",true, GameManager.getPlayer1().getCard(5).equals(GameManager.getDeckCardAt(0)));
        assertEquals("The player card at index 0 should be Nohting", "Nothing", GameManager.getPlayer1().getCard(5).getName());

        GameManager.discardCard(4, GameManager.getPlayer1());
        assertEquals("The card should be at index 1 if the deck",true, GameManager.getPlayer1().getCard(4).equals(GameManager.getDeckCardAt(1)));
        assertEquals("The player card at index4 should have the name Normal card", "Normal card", GameManager.getPlayer1().getCard(4).getName());

        GameManager.discardCard(2, GameManager.getPlayer1());
        assertEquals("The card should be at index 2 of the deck", true, GameManager.getPlayer1().getCard(2).equals(GameManager.getDeckCardAt(2)));
        assertEquals("The player card at index 2 should be Expensive Health", "Expensive Health", GameManager.getPlayer1().getCard(2).getName());

        GameManager.discardCard(1, GameManager.getPlayer1());
        assertEquals("The card should be at index 3 of the deck", true, GameManager.getPlayer1().getCard(1).equals(GameManager.getDeckCardAt(3)));
        assertEquals("The player card at index 1 should be Expensive HCoin", "Expensive HCoin", GameManager.getPlayer1().getCard(1).getName());
    }
}
