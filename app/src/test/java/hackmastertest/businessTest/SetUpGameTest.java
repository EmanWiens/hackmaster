package hackmastertest.businessTest;

import android.test.SingleLaunchActivityTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hackmaster.application.Services;
import hackmaster.business.DeckManager;
import hackmaster.business.Game;
import hackmaster.business.SetUpGame;
import hackmaster.business.SinglePlayerGame;
import hackmaster.objects.CardClass;
import hackmaster.objects.PlayerClass;
import hackmastertest.persistenceTest.DataAccessStub;

import static junit.framework.Assert.assertEquals;

public class SetUpGameTest {
    @Before
    public void setUP(){
        Services.closeDataAccess();
        DataAccessStub dbStub = new DataAccessStub("stub");
        Services.createDataAccess(dbStub,dbStub,dbStub);
    }

    @Test
    public void setUpSinglePlayerGameTest() {
        Game game = SetUpGame.setUpSinglePlayerGame();
        PlayerClass player1 = game.getPlayer1();
        PlayerClass player2 = game.getPlayer2();
        CardClass[] deck = game.getDeck();
        CardClass[] player1Hand = player1.getCards();
        CardClass[] player2Hand = player2.getCards();

        assert(game instanceof SinglePlayerGame);
        assertEquals(Game.hand, player1Hand.length);
        assertEquals(Game.hand, player2Hand.length);
        //TODO test that the stub database works
    }
}
