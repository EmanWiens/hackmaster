package hackmastertest.businessTest;

import org.junit.Before;
import org.junit.Test;

import hackmaster.business.DeckManager;
import hackmaster.business.GameManager;
import hackmaster.objects.CardClass;
import hackmaster.objects.ResourceClass;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;


public class GameManagerUnitTest {
    @Before
    public void setUp(){
        GameManager.runAsTest();
        GameManager.setUpSingleGame();
    }

    private void resetDeck() {
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
        GameManager.runAsTest();

        assertEquals("The name of player should be HackerMan", "HackerMan", GameManager.getPlayer1().getName());
        assertEquals("The ID of player should be 0", 0, GameManager.getPlayer1().getId());
        assertEquals("The health of player should be 100", 100, GameManager.getPlayer1().getResources().getHealth());
        assertEquals("The hCoin of player should be 2",10, GameManager.getPlayer1().getResources().gethCoin());
        assertEquals("The hCoinRate of player should be 2",2, GameManager.getPlayer1().getResources().gethCoinRate());
        assertEquals("The botnet of player should be 2", 10, GameManager.getPlayer1().getResources().getBotnet());
        assertEquals("The botnetRate of player should be 2", 2, GameManager.getPlayer1().getResources().getBotnetRate());
        assertEquals("The CPURate of player should be 2", 2, GameManager.getPlayer1().getResources().getCpuRate());
        assertEquals("The terraFlops of player should be 2", 10, GameManager.getPlayer1().getResources().getCpu());


        assertEquals("The ID of player should be 1", 1, GameManager.getPlayer2().getId());
        assertEquals("The name of player should be Enemy Bot", "Enemy Bot", GameManager.getPlayer2().getName());
        assertEquals("The health of player should be 100", 100, GameManager.getPlayer2().getResources().getHealth());
        assertEquals("The hCoin of player should be 2",10, GameManager.getPlayer2().getResources().gethCoin());
        assertEquals("The hCoinRate of player should be 2",2, GameManager.getPlayer2().getResources().gethCoinRate());
        assertEquals("The botnet of player should be 2", 10, GameManager.getPlayer2().getResources().getBotnet());
        assertEquals("The botnetRate of player should be 2", 2, GameManager.getPlayer2().getResources().getBotnetRate());
        assertEquals("The CPURate of player should be 2", 2, GameManager.getPlayer2().getResources().getCpuRate());
        assertEquals("The terraFlops of player should be 2", 10, GameManager.getPlayer2().getResources().getCpu());
    }

    /*@Test
    public void testApplyCard()
    {
        // TODO this test should be in gameManager (game manager has this function)
        int [] Player1Res= new int[]{-10, -10, 0, 0, 0, 0, 0};
        int [] Player2Res= new int[]{-50, 0, 0, 0, 0, 0, 0};

        ResourceManager.applyCard(true,player1,player2, testCardEffectPlayerOnly, true);
        testIndividualResources(200,2,2, 2,2,2, 2, true);

        ResourceManager.applyCard(false,player1,player2, testCardEffectPlayerOnly, true);
        testIndividualResources(200,2,2, 2,2,2, 2, false);

        ResourceManager.applyCard(true,player1,player2, testCardEffectEnemyCardOnly, true);
        testIndividualResources(0,0,0, 0,0,0, 0, false);

        ResourceManager.applyCard(false,player1,player2, testCardEffectEnemyCardOnly, true);
        testIndividualResources(0,0,0, 0,0,0, 0, true);

        ResourceManager.applyCard(true,player1,player2, testCardEffectPlayerAndEnemy, true);
        testEveryoneResources(Player1Res,Player2Res);

        Player1Res= new int[]{-60, -10, 0, 0, 0, 0, 0};
        Player2Res= new int[]{-60, -10, 0, 0, 0, 0, 0};

        ResourceManager.applyCard(false,player1,player2, testCardEffectPlayerAndEnemy, true);
        testEveryoneResources(Player1Res,Player2Res);

        ResourceManager.applyCard(true,player1,player2, testCardEffectPlayerAndEnemy, true);
        testEveryoneResources(Player1Res,Player2Res);
    }

    @Test
    public void testApplyCardBounds() {
        // TODO write a test that tests the extreme bounds (test player health 100, can play -100 and -99 but not -101)
        setDeck();


    }

    private void setDeck() {
        // player negative is cost and positive is gain
        // enemy negative is loss and positive is gain

        ArrayList<CardClass> testDeck = new ArrayList<CardClass>();
        testDeck.add(new CardClass(0, "Nothing", "Defense", "Do Nothing",
                new ResourceClass(-101, 0, 0, 0, 0,0, 0), null));
        testDeck.add(new CardClass(0, "hundred", "Defense", "Costs a normal amount",
                new ResourceClass(-100, 0, 0,0, 0,0, 0), null));
        testDeck.add(new CardClass(0, "Normal card", "Defense", "Costs a normal amount",
                new ResourceClass(-99, 0, 0,0, 0,0, 0), null));
        testDeck.add(new CardClass(0, "Expensive Health", "Attack", "Costs a lot of Health",
                new ResourceClass(0, -1, 0, 0, 0,0, 0), null));
        testDeck.add(new CardClass(0, "Expensive HCoin", "Attack", "Costs a lot of HCoin",
                new ResourceClass(0, -2, 0, 0, 0,0, 0), null));
        testDeck.add(new CardClass(0, "Expensive BotNet", "Attack", "Costs a lot of Botnet",
                new ResourceClass(0, -3, 0, 0, 0,0, 0), null));
        testDeck.add(new CardClass(0, "Expensive GPU", "Attack", "Costs a lot of CPU",
                new ResourceClass(0, 0, -1, 0, 0,0, 0), null)); //6
        testDeck.add(new CardClass(0, "Generate Health", "Attack", "Makes a lot of Health",
                new ResourceClass(0, 0, -2, 0, 0,0, 0), null));
        testDeck.add(new CardClass(0, "Generate HCoin", "Attack", "Makes a lot of HCoin",
                new ResourceClass(0, 0, -3, 0, 0,0, 0), null));
        testDeck.add(new CardClass(0, "Generate BotNet", "Attack", "Makes a lot of BotNet",
                new ResourceClass(0, 0, 0, -1, 0,0, 0), null));
        testDeck.add(new CardClass(0, "Generate CPU", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, -2, 0,0, 0), null));
        testDeck.add(new CardClass(0, "Generate CPU", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, -3, 0,0, 0), null));

        deckM.setDeck(testDeck.toArray(new CardClass[0]));
    }

    private void testIndividualResources(int health, int hCoin, int hCoinRate, int botnet, int botnetRate , int CPURate , int terraFlops, boolean player1ToCheck ) {
        if (player1ToCheck) {
            assertEquals("The health of player1 should be " + health, health, GameManager.getPlayer1().getResources().getHealth());
            assertEquals("The hCoin of player1 should be " + hCoin, hCoin, GameManager.getPlayer1().getResources().gethCoin());
            assertEquals("The hCoinRate of player1 should be " + hCoinRate, hCoinRate, GameManager.getPlayer1().getResources().gethCoinRate());
            assertEquals("The botnet of player1 should be " + botnet, botnet, GameManager.getPlayer1().getResources().getBotnet());
            assertEquals("The botnetRate of player1 should be " + botnetRate, botnetRate, GameManager.getPlayer1().getResources().getBotnetRate());
            assertEquals("The CPURate of player1 should be " + CPURate, CPURate, GameManager.getPlayer1().getResources().getCpuRate());
            assertEquals("The terraFlops of player1 should be " + terraFlops, terraFlops, GameManager.getPlayer1().getResources().getCpu());
        }
        else {
            assertEquals("The health of player2 should be " + health, health, GameManager.getPlayer2().getResources().getHealth());
            assertEquals("The hCoin of player2 should be " + hCoin, hCoin, GameManager.getPlayer2().getResources().gethCoin());
            assertEquals("The hCoinRate of player2 should be " + hCoinRate, hCoinRate, GameManager.getPlayer2().getResources().gethCoinRate());
            assertEquals("The botnet of player2 should be " + botnet, botnet, GameManager.getPlayer2().getResources().getBotnet());
            assertEquals("The botnetRate of player2 should be " + botnetRate, botnetRate, GameManager.getPlayer2().getResources().getBotnetRate());
            assertEquals("The CPURate of player2 should be " + CPURate, CPURate, GameManager.getPlayer2().getResources().getCpuRate());
            assertEquals("The terraFlops of player2 should be " + terraFlops, terraFlops, GameManager.getPlayer2().getResources().getCpu());
        }
    }
    private void testEveryoneResources(int [] Player1Res ,int [] Player2Res ) {
        assertEquals("The health of player1 should be " + Player1Res[0], Player1Res[0], GameManager.getPlayer1().getResources().getHealth());
        assertEquals("The hCoin of player1 should be " + Player1Res[1], Player1Res[1], GameManager.getPlayer1().getResources().gethCoin());
        assertEquals("The hCoinRate of player1 should be " + Player1Res[2], Player1Res[2], GameManager.getPlayer1().getResources().gethCoinRate());
        assertEquals("The botnet of player1 should be " + Player1Res[3], Player1Res[3], GameManager.getPlayer1().getResources().getBotnet());
        assertEquals("The botnetRate of player1 should be " + Player1Res[4], Player1Res[4], GameManager.getPlayer1().getResources().getBotnetRate());
        assertEquals("The CPURate of player1 should be " + Player1Res[5], Player1Res[5], GameManager.getPlayer1().getResources().getCpuRate());
        assertEquals("The terraFlops of player1 should be " + Player1Res[6], Player1Res[6], GameManager.getPlayer1().getResources().getCpu());

        assertEquals("The health of player2 should be " + Player2Res[0], Player2Res[0], GameManager.getPlayer2().getResources().getHealth());
        assertEquals("The hCoin of player2 should be " + Player2Res[1], Player2Res[1], GameManager.getPlayer2().getResources().gethCoin());
        assertEquals("The hCoinRate of player2 should be " + Player2Res[2], Player2Res[2], GameManager.getPlayer2().getResources().gethCoinRate());
        assertEquals("The botnet of player2 should be " + Player2Res[3], Player2Res[3], GameManager.getPlayer2().getResources().getBotnet());
        assertEquals("The botnetRate of player2 should be " + Player2Res[4], Player2Res[4], GameManager.getPlayer2().getResources().getBotnetRate());
        assertEquals("The CPURate of player2 should be " + Player2Res[5], Player2Res[5], GameManager.getPlayer2().getResources().getCpuRate());
        assertEquals("The terraFlops of player2 should be " + Player2Res[6], Player2Res[6], GameManager.getPlayer2().getResources().getCpu());

    }*/

    @Test
    public void testPlayerTestNotNull()
    {
        GameManager.runAsTest();
        assertNotNull(GameManager.getPlayerNum());
    }
    @Test
    public void testPlayCardEvent()
    {
        GameManager.runAsTest();
       GameManager.playCardEvent(4);
       // is this failing because of the delay?
       assertEquals("Should be Player 1 Turn", 0, GameManager.getPlayerNum());
    }


    @Test
    public void testInvalidPlayCardEvent(){
        // TODO fix these functions (Fail should be in catch)
        GameManager.runAsTest();

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
        GameManager.runAsTest();
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
        GameManager.runAsTest();

        resetDeck();
        setPlayerHands();
        DeckManager.resetIndex();

        assertEquals("The player card at index 0 should be Nothing ", "Nothing", GameManager.getPlayer1().getCard(0).getName());
        assertEquals("The player card at index 1 should be Normal Card", "Normal card", GameManager.getPlayer1().getCard(1).getName());
        assertEquals("The player card at index 2 should be Expensive Health", "Expensive Health", GameManager.getPlayer1().getCard(2).getName());

        GameManager.discardCard(4, GameManager.getPlayer1());
        //assertEquals("The card should be at index 0 if the deck",true, GameManager.getPlayer1().getCard(4).equals(GameManager.getDeckCardAt(0)));
        assertEquals("The player card at index 0 should be Nothing", "Nothing", GameManager.getPlayer1().getCard(4).getName());

        GameManager.discardCard(3, GameManager.getPlayer1());
        //assertEquals("The card should be at index 1 if the deck",true, GameManager.getPlayer1().getCard(3).equals(GameManager.getDeckCardAt(1)));
        assertEquals("The player card at index4 should have the name Normal card", "Normal card", GameManager.getPlayer1().getCard(3).getName());

        GameManager.discardCard(2, GameManager.getPlayer1());
        //assertEquals("The card should be at index 2 of the deck", true, GameManager.getPlayer1().getCard(2).equals(GameManager.getDeckCardAt(2)));
        assertEquals("The player card at index 2 should be Expensive Health", "Expensive Health", GameManager.getPlayer1().getCard(2).getName());

        GameManager.discardCard(1, GameManager.getPlayer1());
        //assertEquals("The card should be at index 3 of the deck", true, GameManager.getPlayer1().getCard(1).equals(GameManager.getDeckCardAt(3)));
        assertEquals("The player card at index 1 should be Expensive HCoin", "Expensive HCoin", GameManager.getPlayer1().getCard(1).getName());
    }
}
