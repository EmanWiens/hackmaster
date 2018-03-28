package hackmasterTests.integrationTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import hackmaster.application.DBController;
import hackmaster.application.Services;
import hackmaster.business.DeckManager;
import hackmaster.business.Game;
import hackmaster.business.ResourceManager;
import hackmaster.business.SetupGame;
import hackmaster.objects.CardClass;
import hackmaster.objects.PlayerClass;
import hackmaster.objects.ResourceClass;
import hackmaster.persistence.CardDataAccessInterface;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class SinglePlayerGameTest {
    private Game testGame;
    private PlayerClass player1, player2;
    private ResourceClass player1Resource, player2Resource;
    private CardDataAccessInterface cardDataAccess;
    private Random RNG;

    @Before
    public void setup(){
        DBController.startUp();
        Services.closeDataAccess();
        Services.createDataAccess(DBController.dbName);
        cardDataAccess = Services.getCardDataAccess();
        RNG = new Random(0);
    }

    @Test
    public void testSingleplayerGame() {
        DeckManager.setRandom(RNG);
        DeckManager.resetIndex();
        testGame = SetupGame.setUpSinglePlayerGame();

        player1 = testGame.getPlayer1();
        player2 = testGame.getPlayer2();
        player1Resource = player1.getResources();
        player2Resource = player2.getResources();

        assertEquals(100, player1.getHealth());
        assertEquals(10, player1Resource.gethCoin());
        assertEquals(2, player1Resource.gethCoinRate());
        assertEquals(10, player1Resource.getBotnet());
        assertEquals(2, player1Resource.getBotnetRate());

        assertEquals(100, player2.getHealth());
        assertEquals(10, player2Resource.gethCoin());
        assertEquals(2, player2Resource.gethCoinRate());
        assertEquals(10, player2Resource.getBotnet());
        assertEquals(2, player2Resource.getBotnetRate());

        assertEquals(1, player1.getCard(0).getID());
        assertEquals(20, player1.getCard(1).getID());
        assertEquals(10, player1.getCard(2).getID());
        assertEquals(8, player1.getCard(3).getID());
        assertEquals(7, player1.getCard(4).getID());

        assertEquals(22,player2.getCard(0).getID());
        assertEquals(27,player2.getCard(1).getID());
        assertEquals(2,player2.getCard(2).getID());
        assertEquals(4,player2.getCard(3).getID());
        assertEquals(26,player2.getCard(4).getID());

        testGame.pauseGame();
        assertTrue(testGame.gamePaused());
        testGame.unpauseGame();
        assertFalse(testGame.gamePaused());

        // Turn 1
        testGame.playCardEvent(0);
        assertEquals(1,testGame.getPlayedCardOne().getID());
        assertEquals(22,testGame.getPlayedCardTwo().getID());
        assertEquals(3, player1Resource.gethCoinRate());
        assertEquals(8, player1Resource.gethCoin());
        assertEquals(12, player1Resource.getBotnet());
        assertEquals(12, player1Resource.getCpu());
        assertEquals(90, player2Resource.getHealth());
        assertEquals(100, player1Resource.getHealth());
        assertEquals(12, player2Resource.gethCoin());
        assertEquals(12, player2Resource.getBotnet());
        assertEquals(12, player2Resource.getCpu());
        assertEquals(24, player1.getCard(0).getID());
        assertEquals(3, player2.getCard(0).getID());

        testGame.discardOn();
        testGame.playCardEvent(0);
        assertEquals(19, player1.getCard(0).getID());
        assertTrue(!testGame.getDiscard());

        testGame.pauseGame();
        assertTrue(testGame.gamePaused());
        testGame.unpauseGame();
        assertFalse(testGame.gamePaused());

        // Turn 2: player 1 discard
        testGame.discardOn();
        testGame.playCardEvent(0);
        assertEquals(19,testGame.getPlayedCardOne().getID());
        assertEquals(0,testGame.getPlayedCardTwo().getID());
        assertEquals(14, player1Resource.gethCoin());
        assertEquals(16, player1Resource.getBotnet());
        assertEquals(16, player1Resource.getCpu());
        assertEquals(90, player1Resource.getHealth());
        assertEquals(11, player2Resource.gethCoin());
        assertEquals(11, player2Resource.getBotnet());
        assertEquals(17, player2Resource.getCpu());
        assertEquals(23, player1.getCard(0).getID());
        assertEquals(3, player2.getCard(0).getID());

        assertFalse(testGame.gameDone());

        // Turn 3
        testGame.playCardEvent(0);
        assertEquals(23,testGame.getPlayedCardOne().getID());
        assertEquals(3,testGame.getPlayedCardTwo().getID());
        assertEquals(80, player1Resource.getHealth());
        assertEquals(12, player1Resource.gethCoin());
        assertEquals(18, player1Resource.getBotnet());
        assertEquals(18, player1Resource.getCpu());
        assertEquals(80, player2Resource.getHealth());
        assertEquals(14, player2Resource.gethCoin());
        assertEquals(8, player2Resource.getBotnet());
        assertEquals(20, player2Resource.getCpu());
        assertEquals(11, player1.getCard(0).getID());
        assertEquals(25, player2.getCard(0).getID());

        assertFalse(testGame.gameDone());

        testGame.discardOn();
        testGame.playCardEvent(0);
        assertEquals(18, player1.getCard(0).getID());
        assertTrue(!testGame.getDiscard());

        testGame.discardOn();
        testGame.playCardEvent(0);
        assertEquals(6, player1.getCard(0).getID());
        assertTrue(!testGame.getDiscard());

        testGame.playCardEvent(0);
        assertEquals(6,testGame.getPlayedCardOne().getID());
        assertEquals(17,testGame.getPlayedCardTwo().getID());
        assertEquals(75, player1Resource.getHealth());
        assertEquals(19, player1Resource.gethCoin());
        assertEquals(24, player1Resource.getBotnet());
        assertEquals(24, player1Resource.getCpu());
        assertEquals(80, player2Resource.getHealth());
        assertEquals(24, player2Resource.gethCoin());
        assertEquals(11, player2Resource.getBotnet());
        assertEquals(17, player2Resource.getCpu());
        assertEquals(23, player1.getCard(0).getID());
        assertEquals(3, player2.getCard(0).getID());
    }

    @Test
    public void testPlayer2Lose() {
        DeckManager.setRandom(RNG);
        DeckManager.resetIndex();
        testGame = SetupGame.setUpSinglePlayerGame();

        player1 = testGame.getPlayer1();
        player2 = testGame.getPlayer2();
        player1Resource = player1.getResources();
        player2Resource = player2.getResources();

        player2.getResources().setHealth(10);

        // Turn 1
        testGame.playCardEvent(0);
        assertEquals(1,testGame.getPlayedCardOne().getID());
        assertEquals(22,testGame.getPlayedCardTwo().getID());
        assertEquals(3, player1Resource.gethCoinRate());
        assertEquals(8, player1Resource.gethCoin());
        assertEquals(12, player1Resource.getBotnet());
        assertEquals(12, player1Resource.getCpu());
        assertEquals(12, player2Resource.gethCoin());
        assertEquals(12, player2Resource.getBotnet());
        assertEquals(12, player2Resource.getCpu());
        assertEquals(24, player1.getCard(0).getID());
        assertEquals(3, player2.getCard(0).getID());

        assertEquals(0, player2Resource.getHealth());
        assertEquals(100, player1Resource.getHealth());

        assertTrue(testGame.gameDone());
    }

    @Test
    public void testPlayer1Lose() {
        DeckManager.setRandom(RNG);
        DeckManager.resetIndex();
        testGame = SetupGame.setUpSinglePlayerGame();

        player1 = testGame.getPlayer1();
        player2 = testGame.getPlayer2();
        player1Resource = player1.getResources();
        player2Resource = player2.getResources();

        player1.getResources().setHealth(10);

        // Turn 1
        testGame.playCardEvent(0);
        assertEquals(1,testGame.getPlayedCardOne().getID());
        assertEquals(22,testGame.getPlayedCardTwo().getID());
        assertEquals(3, player1Resource.gethCoinRate());
        assertEquals(8, player1Resource.gethCoin());
        assertEquals(12, player1Resource.getBotnet());
        assertEquals(12, player1Resource.getCpu());
        assertEquals(12, player2Resource.gethCoin());
        assertEquals(12, player2Resource.getBotnet());
        assertEquals(12, player2Resource.getCpu());
        assertEquals(24, player1.getCard(0).getID());
        assertEquals(3, player2.getCard(0).getID());

        assertEquals(10, player1.getHealth());
        assertEquals(90, player2.getHealth());

        assertFalse(testGame.gameDone());

        // Turn 2
        testGame.playCardEvent(0);
        assertEquals(24,testGame.getPlayedCardOne().getID());
        assertEquals(2,testGame.getPlayedCardTwo().getID());
        assertEquals(5, player1Resource.gethCoin());
        assertEquals(16, player1Resource.getBotnet());
        assertEquals(14, player1Resource.getCpu());
        assertEquals(75, player2Resource.getHealth());
        assertEquals(14, player2Resource.gethCoin());
        assertEquals(9, player2Resource.getBotnet());
        assertEquals(14, player2Resource.getCpu());
        assertEquals(19, player1.getCard(0).getID());
        assertEquals(3, player2.getCard(0).getID());

        assertEquals(10, player1.getHealth());
        assertEquals(75, player2.getHealth());

        assertFalse(testGame.gameDone());

        testGame.playCardEvent(0);
        assertEquals(19,testGame.getPlayedCardOne().getID());
        assertEquals(0,testGame.getPlayedCardTwo().getID());
        assertEquals(8, player1Resource.gethCoin());
        assertEquals(8, player1Resource.getBotnet());
        assertEquals(16, player1Resource.getCpu());
        assertEquals(11, player2Resource.gethCoin());
        assertEquals(11, player2Resource.getBotnet());
        assertEquals(17, player2Resource.getCpu());
        assertEquals(23, player1.getCard(0).getID());
        assertEquals(3, player2.getCard(0).getID());

        assertEquals(0, player1.getHealth());
        assertEquals(55, player2.getHealth());

        assertTrue(testGame.gameDone());
    }

    @Test
    public void testPlayCardEvent() {
        DeckManager.setRandom(RNG);
        DeckManager.resetIndex();
        testGame = SetupGame.setUpSinglePlayerGame();

        player1 = testGame.getPlayer1();
        player2 = testGame.getPlayer2();
        player1Resource = player1.getResources();
        player2Resource = player2.getResources();

        // player 1 turn
        testGame.playCardEvent(0);
        assertEquals(1,testGame.getPlayedCardOne().getID());
        assertEquals(22,testGame.getPlayedCardTwo().getID());
        assertEquals(3, player1Resource.gethCoinRate());
        assertEquals(8, player1Resource.gethCoin());
        assertEquals(24, player1.getCard(0).getID());
        assertEquals(12, player1Resource.getBotnet());
        assertEquals(2, player1Resource.getBotnetRate());
        assertEquals(90, player2.getHealth());
        assertEquals(12, player2Resource.gethCoin());
        assertEquals(2, player2Resource.gethCoinRate());
        assertEquals(12, player2Resource.getBotnet());
        assertEquals(2, player2Resource.getBotnetRate());
        assertEquals(24, player1.getCard(0).getID());
        assertEquals(3, player2.getCard(0).getID());


        // player 1 turn
        testGame.playCardEvent(0);
        assertEquals(24,testGame.getPlayedCardOne().getID());
        assertEquals(2,testGame.getPlayedCardTwo().getID());
        assertEquals(14, player2Resource.gethCoin());
        assertEquals(9, player2Resource.getBotnet());
        assertEquals(3, player2.getCard(0).getID());
        assertEquals(2, player2Resource.gethCoinRate());
        assertEquals(2, player2Resource.getBotnetRate());
        assertEquals(100, player1.getHealth());
        assertEquals(5, player1Resource.gethCoin());
        assertEquals(3, player1Resource.gethCoinRate());
        assertEquals(16, player1Resource.getBotnet());
        assertEquals(2, player1Resource.getBotnetRate());
        assertEquals(19, player1.getCard(0).getID());
        assertEquals(3, player2.getCard(0).getID());


        // player 1 turn
        testGame.playCardEvent(0);
        assertEquals(19,testGame.getPlayedCardOne().getID());
        assertEquals(0,testGame.getPlayedCardTwo().getID());
        assertEquals(3, player1Resource.gethCoinRate());
        assertEquals(8, player1Resource.gethCoin());
        assertEquals(23, player1.getCard(0).getID());
        assertEquals(8, player1Resource.getBotnet());
        assertEquals(2, player1Resource.getBotnetRate());
        assertEquals(55, player2.getHealth());
        assertEquals(11, player2Resource.gethCoin());
        assertEquals(3, player2Resource.gethCoinRate());
        assertEquals(11, player2Resource.getBotnet());
        assertEquals(2, player2Resource.getBotnetRate());
        assertEquals(23, player1.getCard(0).getID());
        assertEquals(3, player2.getCard(0).getID());


        // player 1 turn
        testGame.playCardEvent(0);
        assertEquals(23,testGame.getPlayedCardOne().getID());
        assertEquals(3,testGame.getPlayedCardTwo().getID());
        assertEquals(14, player2Resource.gethCoin());
        assertEquals(8, player2Resource.getBotnet());
        assertEquals(25, player2.getCard(0).getID());
        assertEquals(3, player2Resource.gethCoinRate());
        assertEquals(2, player2Resource.getBotnetRate());
        assertEquals(80, player1.getHealth());
        assertEquals(6, player1Resource.gethCoin());
        assertEquals(3, player1Resource.gethCoinRate());
        assertEquals(10, player1Resource.getBotnet());
        assertEquals(2, player1Resource.getBotnetRate());
        assertEquals(11, player1.getCard(0).getID());
        assertEquals(25, player2.getCard(0).getID());


        // player 1 turn
        testGame.playCardEvent(0);
        assertEquals(11,testGame.getPlayedCardOne().getID());
        assertEquals(25, testGame.getPlayedCardTwo().getID());
        assertEquals(1, player1Resource.gethCoinRate());
        assertEquals(7, player1Resource.gethCoin());
        assertEquals(18, player1.getCard(0).getID());
        assertEquals(12, player1Resource.getBotnet());
        assertEquals(2, player1Resource.getBotnetRate());
        assertEquals(45, player2.getHealth());
        assertEquals(17, player2Resource.gethCoin());
        assertEquals(3, player2Resource.gethCoinRate());
        assertEquals(10, player2Resource.getBotnet());
        assertEquals(2, player2Resource.getBotnetRate());
        assertEquals(18, player1.getCard(0).getID());
        assertEquals(14, player2.getCard(0).getID());


        // player 1 turn
        testGame.playCardEvent(0);
        assertEquals(11,testGame.getPlayedCardOne().getID());
        assertEquals(25,testGame.getPlayedCardTwo().getID());
        assertEquals(17, player2Resource.gethCoin());
        assertEquals(10, player2Resource.getBotnet());
        assertEquals(14, player2.getCard(0).getID());
        assertEquals(3, player2Resource.gethCoinRate());
        assertEquals(2, player2Resource.getBotnetRate());
        assertEquals(100, player1.getHealth());
        assertEquals(7, player1Resource.gethCoin());
        assertEquals(1, player1Resource.gethCoinRate());
        assertEquals(12, player1Resource.getBotnet());
        assertEquals(2, player1Resource.getBotnetRate());
        assertEquals(18, player1.getCard(0).getID());
        assertEquals(14, player2.getCard(0).getID());
    }

    @After
    public void tearDown() {
        Services.closeDataAccess();
    }
}