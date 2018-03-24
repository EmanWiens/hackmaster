package hackmastertest.businessTest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import hackmaster.application.Services;
import hackmaster.business.DeckManager;
import hackmaster.business.Game;
import hackmaster.business.SetUpGame;
import hackmaster.business.SinglePlayerGame;
import hackmaster.objects.CardClass;
import hackmaster.objects.EnemyAI;
import hackmaster.objects.PlayerClass;
import hackmaster.objects.ResourceClass;
import hackmastertest.persistenceTest.DataAccessStub;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.fail;

public class SinglePlayerGameUnitTest {
    Game game;
    PlayerClass player1;
    PlayerClass player2;
    CardClass[] deck;
    ResourceClass player1Res;
    ResourceClass player2Res;
    CardClass[] player1Hand;
    CardClass[] player2Hand;

    @Before
    public void setup(){
        Services.closeDataAccess();
        DataAccessStub dbStub = new DataAccessStub("stub");
        Services.createDataAccess(dbStub,dbStub,dbStub);

        game = SetUpGame.setUpSinglePlayerGame();
        ArrayList<CardClass> listDeck = new ArrayList<>();
        dbStub.getCardSequential(listDeck);
        game.setDeck(listDeck.toArray(new CardClass[0]));
        player1 = game.getPlayer1();
        player2 = game.getPlayer2();
        DeckManager.resetIndex();
        player1.setHand(DeckManager.dealFirstHandOfGame());
        player2.setHand(DeckManager.dealFirstHandOfGame());
    }

    @Test
    public void testSetupInstances() {
        assert(game instanceof SinglePlayerGame);
        assert(player1 instanceof PlayerClass);
        assert (player2 instanceof EnemyAI);
    }

    @Test
    public void testPlayer1Resources() {
        player1Res = game.getPlayer1Res();

        assertEquals(SetUpGame.startOfGameResources().getBotnet(), player1Res.getBotnet());
        assertEquals(SetUpGame.startOfGameResources().getBotnetRate(), player1Res.getBotnetRate());

        assertEquals(SetUpGame.startOfGameResources().getCpu(), player1Res.getCpu());
        assertEquals(SetUpGame.startOfGameResources().getCpuRate(), player1Res.getCpuRate());

        assertEquals(SetUpGame.startOfGameResources().gethCoin(), player1Res.gethCoin());
        assertEquals(SetUpGame.startOfGameResources().gethCoinRate(), player1Res.gethCoinRate());

        assertEquals(SetUpGame.startOfGameResources().getHealth(), player1Res.getHealth());
    }

    @Test
    public void testPlayer2Resources() {
        player2Res = game.getPlayer2Res();

        assertEquals(SetUpGame.startOfGameResources().getBotnet(), player2Res.getBotnet());
        assertEquals(SetUpGame.startOfGameResources().getBotnetRate(), player2Res.getBotnetRate());

        assertEquals(SetUpGame.startOfGameResources().getCpu(), player2Res.getCpu());
        assertEquals(SetUpGame.startOfGameResources().getCpuRate(), player2Res.getCpuRate());

        assertEquals(SetUpGame.startOfGameResources().gethCoin(), player2Res.gethCoin());
        assertEquals(SetUpGame.startOfGameResources().gethCoinRate(), player2Res.gethCoinRate());

        assertEquals(SetUpGame.startOfGameResources().getHealth(), player2Res.getHealth());
    }

    @Test
    public void testPlayer1Hand() {
        player1Hand = game.getPlayer1().getCards();
        deck = game.getDeck();

        assertEquals("CPU Boost", player1Hand[0].getName());
        assertEquals(deck[0], player1Hand[0]);

        assertEquals("More Cores", player1Hand[1].getName());
        assertEquals(deck[1], player1Hand[1]);

        assertEquals("bot.net", player1Hand[2].getName());
        assertEquals(deck[2], player1Hand[2]);

        assertEquals("Сut some wires", player1Hand[3].getName());
        assertEquals(deck[3], player1Hand[3]);

        assertEquals("Upgrade Botnet", player1Hand[4].getName());
        assertEquals(deck[4], player1Hand[4]);
    }

    @Test
    public void testPlayer2Hand() {
        player2Hand = game.getPlayer2().getCards();
        deck = game.getDeck();

        assertEquals("Upgrade CPU", player2Hand[0].getName());
        assertEquals(deck[5].getID(), player2Hand[0].getID());

        assertEquals("Upgrade Hash Rate", player2Hand[1].getName());
        assertEquals(deck[6].getID(), player2Hand[1].getID());

        assertEquals("DDOS", player2Hand[2].getName());
        assertEquals(deck[7].getID(), player2Hand[2].getID());

        assertEquals("File Transfer", player2Hand[3].getName());
        assertEquals(deck[8].getID(), player2Hand[3].getID());

        assertEquals("Pop-up", player2Hand[4].getName());
        assertEquals(deck[9].getID(), player2Hand[4].getID());
    }

    @Test
    public void testPlayCardEventReplaceCard()
    {
        CardClass beforePlayed = game.getPlayer1().getCard(4);
        game.playCardEvent(4);
        assertEquals(game.getPlayedCardOne(), beforePlayed);
        assertFalse(beforePlayed.equals(game.getPlayer1().getCard(4)));

        beforePlayed = game.getPlayer1().getCard(1);
        game.playCardEvent(1);
        assertEquals(game.getPlayedCardOne(), beforePlayed);
        assertFalse(beforePlayed.equals(game.getPlayer1().getCard(1)));

        beforePlayed = game.getPlayer1().getCard(2);
        game.playCardEvent(2);
        assertEquals(game.getPlayedCardOne(), beforePlayed);
        assertFalse(beforePlayed.equals(game.getPlayer1().getCard(2)));

        beforePlayed = game.getPlayer1().getCard(0);
        game.discardOn();
        game.playCardEvent(0);
        assertEquals(game.getPlayedCardOne(), beforePlayed);
        assertFalse(beforePlayed.equals(game.getPlayer1().getCard(0)));
    }

    @Test
    public void testPlayerTurnAfterPlayedCard() {
        assert (game.getPlayer1Turn());
        game.playCardEvent(4);

        assert (game.getPlayer1Turn());
        game.playCardEvent(3);

        assert (game.getPlayer1Turn());
        game.playCardEvent(3);

        assert (game.getPlayer1Turn());
        game.playCardEvent(3);
    }
}
