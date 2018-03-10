package hackmastertest.businessTest;

import org.junit.Before;
import org.junit.Test;


import hackmaster.application.Services;
import hackmaster.business.DeckManager;
import hackmaster.objects.CardClass;
import hackmastertest.persistenceTest.DataAccessStub;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

public class DeckManagerUnitTest {
    @Before
    public void setUP(){
        Services.closeDataAccess();
        DataAccessStub dbStub = new DataAccessStub("stub");
        Services.createDataAccess(dbStub,dbStub,dbStub);
        DeckManager.initDeck();
    }


    @Test
    public void testDealInitialHand() {
        CardClass[] deck =DeckManager.dealFirstHandOfGame();
        assertEquals( 0, deck[0].getID());
        assertEquals( 1, deck[1].getID());
        assertEquals( 2, deck[2].getID());
        assertEquals( 3, deck[3].getID());
        assertEquals( 4, deck[4].getID());
    }
    
    @Test
    public void testDealNextCard() {
        CardClass Card1 = DeckManager.dealNextCard();
        assertEquals(0, Card1.getID());

        CardClass Card2 = DeckManager.dealNextCard();

        assertEquals(1,Card2.getID());
    }

    @Test
    public void testDealNextCardBounds() {
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
    
}