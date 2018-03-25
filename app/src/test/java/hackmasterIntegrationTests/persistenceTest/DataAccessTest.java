package hackmasterIntegrationTests.persistenceTest;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import hackmasterOG.objects.CardClass;
import hackmasterOG.objects.PlayerStatsSaves;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class DataAccessTest {
    DataAccessStub dbStub;
    @Before
    public void Setup() {
        this.dbStub = new DataAccessStub("Stub");
        this.dbStub.open("Stub Path");
    }

    @Test
    public void testPlayerStatsAccess() {

        ArrayList<PlayerStatsSaves> players = new ArrayList<PlayerStatsSaves>();
        ArrayList<String> playerNames = new ArrayList<String>();
        PlayerStatsSaves player;
        int playerID;
        String result;

        result = dbStub.getPlayerSequential(players);
        assertNull(result);
        assertEquals(4, players.size());
        assertEquals(100, players.get(0).getPlayerID());
        assertEquals(200, players.get(1).getPlayerID());
        assertEquals(300, players.get(2).getPlayerID());
        assertEquals(400, players.get(3).getPlayerID());

        result = dbStub.getPlayersNamesList(playerNames);
        assertEquals(4, playerNames.size());
        assertNull(result);
        assertEquals("Gary Chalmers", playerNames.get(0));
        assertEquals("Selma Bouvier", playerNames.get(1));
        assertEquals("Arnie Pye", playerNames.get(2));
        assertEquals("Bailey Bailey", playerNames.get(3));

        players = dbStub.getPlayerRandom(400);
        assertEquals(400, players.get(0).getPlayerID());
        players = dbStub.getPlayerRandom(1234);
        assertEquals(0, players.size());

        player = new PlayerStatsSaves(35);
        result = dbStub.insertPlayer(player);
        assertNull(result);
        players = dbStub.getPlayerRandom(35);
        assertEquals(1, players.size());
        assertEquals(35, players.get(0).getPlayerID());

        player = new PlayerStatsSaves(35, "Tester", 0, 0, 0, 0);
        result = dbStub.updatePlayer(player);
        assertNull(result);
        players = dbStub.getPlayerRandom(35);
        assertEquals(1, players.size());
        assertEquals("Tester", players.get(0).getName());

        result = dbStub.removePlayer(35);
        assertNull(result);
        players = dbStub.getPlayerRandom(35);
        assertEquals(0, players.size());
    }

    @Test
    public void testCardsAccess() {

        ArrayList<CardClass> cards = new ArrayList<CardClass>();
        CardClass card;
        String result;

        dbStub.getCardSequential(cards);
        assertEquals(29, cards.size());
        assertEquals(0, cards.get(0).getID());
        assertEquals(28, cards.get(28).getID());

        cards = dbStub.getCardRandom(5);
        assertEquals(5, cards.get(0).getID());
        cards = dbStub.getCardRandom(1234);
        assertEquals(0, cards.size());

        card = new CardClass(35);
        result = dbStub.insertCard(card);
        assertNull(result);
        cards = dbStub.getCardRandom(35);
        assertEquals(1, cards.size());
        assertEquals(35, cards.get(0).getID());

        card = new CardClass(35, "Test Card", "Test", "Its a test", null, null);
        result = dbStub.updateCard(card);
        assertNull(result);
        cards = dbStub.getCardRandom(35);
        assertEquals(1, cards.size());
        assertEquals("Test Card", cards.get(0).getName());

        result = dbStub.removeCard(35);
        assertNull(result);
        cards = dbStub.getCardRandom(35);
        assertEquals(0, cards.size());

        cards.clear();
        dbStub.getRandomDeck(cards, new Random(0));
        assertEquals(29, cards.size());
        assertEquals(1, cards.get(0).getID());
        assertEquals(12, cards.get(28).getID());
    }
}
