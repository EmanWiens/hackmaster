package hackmasterTests.integrationTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

import hackmaster.application.DBController;
import hackmaster.application.Services;
import hackmaster.business.DeckManager;
import hackmaster.objects.CardClass;
import hackmaster.objects.ResourceClass;
import hackmaster.persistence.CardDataAccessInterface;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertNull;

public class BusinessPersistenceSeamTest {
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
    public void testDeckManager() {
        DeckManager.setRandom(RNG);
        DeckManager.initDeck();

        CardClass[] deck = DeckManager.getDeck();
        assertEquals(1, deck[0].getID());
        assertEquals(20, deck[1].getID());
        assertEquals(10, deck[2].getID());
        assertEquals(8, deck[3].getID());
        assertEquals(7, deck[4].getID());

        ArrayList<CardClass> randCard = cardDataAccess.getCardRandom(deck[0].getID());
        assertNotNull(randCard);
        assertEquals(1,randCard.get(0).getID());
    }

    @Test
    public void testCardPersistence() {
        String result;
        ResourceClass emptyResource = new ResourceClass(0,0,0,0,0,0,0);

        CardClass newCard = new CardClass(400, "Jansen Card", "meme", "card test", emptyResource, emptyResource);
        result = cardDataAccess.insertCard(newCard);
        assertNull(result);
        assertEquals(400, cardDataAccess.getCardRandom(400).get(0).getID());

        emptyResource.setBotnetRate(2);
        emptyResource.increaseBotnetByRate();

        newCard = new CardClass(400, "Jansen Card", "meme", "card test", emptyResource, emptyResource);
        result = cardDataAccess.updateCard(newCard);
        assertNull(result);
        assertEquals(400, cardDataAccess.getCardRandom(400).get(0).getID());
        assertEquals(2, cardDataAccess.getCardRandom(400).get(0).getPlayerR().getBotnet());

        newCard = new CardClass(401, "Jansen Card", "meme", "card test", emptyResource, emptyResource);
        result = cardDataAccess.updateCard(newCard);
        assertEquals("No rows are affected by the operation", result);

        result = cardDataAccess.removeCard(400);
        assertNull(result);
        assertTrue(cardDataAccess.getCardRandom(400).isEmpty());

        result = cardDataAccess.removeCard(401);
        assertEquals("No rows are affected by the operation", result);
    }

    @After
    public void tearDown() {
        Services.closeDataAccess();
    }
}
