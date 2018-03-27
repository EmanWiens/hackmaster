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
import hackmaster.persistence.CardDataAccessInterface;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

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

        ArrayList<CardClass> randCard = cardDataAccess.getCardRandom(1);
        assertNotNull(randCard);
        assertEquals(1,randCard.get(0).getID());

        fail("tests to exercise all CRUD operations");
    }

    @After
    public void tearDown() {
        Services.closeDataAccess();
    }
}
