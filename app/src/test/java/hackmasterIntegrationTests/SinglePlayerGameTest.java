package hackmasterIntegrationTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hackmaster.application.Services;
import hackmaster.business.DeckManager;
import hackmaster.business.Game;
import hackmaster.business.SetupGame;
import hackmaster.objects.PlayerClass;
import hackmaster.objects.ResourceClass;
import hackmasterUnitTest.persistenceTest.DataAccessStub;

import static junit.framework.Assert.assertEquals;

public class SinglePlayerGameTest {
    private Game testGame;
    private PlayerClass player1, player2;
    private ResourceClass startResources;

    @Before
    public void setup(){
        Services.closeDataAccess();
        DataAccessStub dbStub = new DataAccessStub("stub");
        Services.createDataAccess(dbStub,dbStub,dbStub);
        DeckManager.initDeck();
    }

    @Test
    public void setupGameTest() {
        testGame = SetupGame.setUpSinglePlayerGame();

        player1 = testGame.getPlayer1();
        player2 = testGame.getPlayer2();
        startResources = SetupGame.startOfGameResources();

        assertEquals(100, player1.getHealth());
        assertEquals(10, player1.getResources().gethCoin());
        assertEquals(2, player1.getResources().gethCoinRate());

        assertEquals(100, player2.getHealth());
        assertEquals(10, player2.getResources().gethCoin());
        assertEquals(2, player2.getResources().gethCoinRate());

        // play
    }

    @After
    public void tearDown() {
        Services.closeDataAccess();
    }
}