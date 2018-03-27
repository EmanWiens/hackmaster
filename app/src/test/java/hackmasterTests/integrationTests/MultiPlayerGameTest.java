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

public class MultiPlayerGameTest {
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
    public void setupGameTest() {
        DeckManager.setRandom(RNG);
        testGame = SetupGame.setUpMultiplayerGame();

        player1 = testGame.getPlayer1();
        player2 = testGame.getPlayer2();
        player1Resource = player1.getResources();
        player2Resource = player2.getResources();

        assertEquals(100, player1.getHealth());
        assertEquals(10, player1.getResources().gethCoin());
        assertEquals(2, player1.getResources().gethCoinRate());

        assertEquals(100, player2.getHealth());
        assertEquals(10, player2.getResources().gethCoin());
        assertEquals(2, player2.getResources().gethCoinRate());

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

        // player 1 turn
        testGame.playCardEvent(0);
        assertEquals(3, player1Resource.gethCoinRate());
        assertEquals(5, player1Resource.gethCoin());
        assertEquals(90, player2Resource.getHealth());
        assertEquals(24, player1.getCard(0).getID());

        // player 2 turn
        testGame.playCardEvent(0);
        assertEquals(17, player2Resource.gethCoin());
        assertEquals(2, player2Resource.getBotnet());
        assertEquals(90, player1Resource.getHealth());
        assertEquals(3, player2.getCard(0).getID());

        // player 1 turn discard
        testGame.discardOn();
        testGame.playCardEvent(0);
        assertEquals(19, player1.getCard(0).getID());

        // player 2 turn discard
        testGame.discardOn();
        testGame.playCardEvent(0);
        assertEquals(19, player1.getCard(0).getID());
        assertEquals(0, player2.getCard(0).getID());
    }


    @After
    public void tearDown() {
        Services.closeDataAccess();
    }
}