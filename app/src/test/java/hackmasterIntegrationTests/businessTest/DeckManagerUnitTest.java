package hackmasterIntegrationTests.businessTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import hackmaster.application.Services;
import hackmaster.business.DeckManager;
import hackmaster.objects.CardClass;
import hackmasterIntegrationTests.persistenceTest.DataAccessStub;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

public class DeckManagerUnitTest {
    @Before
    public void setup(){
        Services.closeDataAccess();
        DataAccessStub dbStub = new DataAccessStub("stub");
        Services.createDataAccess(dbStub,dbStub,dbStub);
        DeckManager.initDeck();
        ArrayList<CardClass> listDeck = new ArrayList<>();
        dbStub.getCardSequential(listDeck);
        DeckManager.setDeck(listDeck.toArray(new CardClass[0]));
    }

    @Test
    public void testDealInitialHand() {
        CardClass[] deck = DeckManager.dealFirstHandOfGame();
        assertEquals( 0, deck[0].getID());
        assertEquals( 1, deck[1].getID());
        assertEquals( 2, deck[2].getID());
        assertEquals( 3, deck[3].getID());
        assertEquals( 4, deck[4].getID());
    }

    @Test
    public void testDealNextCard() {
        CardClass card1 = DeckManager.dealNextCard();
        assertEquals(0, card1.getID());
        assertTrue(DeckManager.getNextIndex() == 1);

        card1 = DeckManager.dealNextCard();
        assertEquals(1,card1.getID());
        assertTrue(DeckManager.getNextIndex() == 2);

        card1 = DeckManager.dealNextCard();
        assertEquals(2,card1.getID());
        assertTrue(DeckManager.getNextIndex() == 3);

        card1 = DeckManager.dealNextCard();
        assertTrue(DeckManager.getNextIndex() == 4);
        card1 = DeckManager.dealNextCard();
        assertTrue(DeckManager.getNextIndex() == 5);
        assertEquals(4,card1.getID());
    }

    @Test
    public void testResetIndex() {
        assertTrue(DeckManager.getNextIndex() == 0);
        DeckManager.dealNextCard();
        DeckManager.dealNextCard();
        assertTrue(DeckManager.getNextIndex() == 2);
        DeckManager.dealNextCard();
        assertTrue(DeckManager.getNextIndex() == 3);

        DeckManager.resetIndex();
        assertTrue(DeckManager.getNextIndex() == 0);
    }

    @Test
    public void testUpdateNextIndex() {
        DeckManager.setIndex(DeckManager.getSizeDeck() - 1);

        CardClass card = DeckManager.dealNextCard();
        assertEquals(28, card.getID());

        card = DeckManager.dealNextCard();
        assertEquals(0, card.getID());
    }

   @Test
    public void testGetCardAt() {
       try {
           DeckManager.getCardAt(-1);
           fail("ArrayIndexOutOfBounds Expected");
       } catch ( ArrayIndexOutOfBoundsException exp) {
       }
       assertEquals(0,DeckManager.getCardAt(0).getID());
       assertEquals(28,DeckManager.getCardAt(DeckManager.getSizeDeck()-1).getID());
       try {
           DeckManager.getCardAt(DeckManager.getSizeDeck());
           fail("ArrayIndexOutOfBounds Expected");
       } catch ( ArrayIndexOutOfBoundsException exp) {
       }
    }

    @After
    public void tearDown() {
        Services.closeDataAccess();
    }
}