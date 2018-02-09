package hackMaster.businessTest;

import org.junit.Before;
import org.junit.Test;


import hackmaster20.business.DeckManager;

import static java.sql.Types.NULL;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DeckManagerUnitTest {
   // @Test
    @Before
    public void setUP(){
        DeckManager.initDeck(4);
        DeckManager.dealCards(6);
    }

    @Test
    public void testInitDeckManager() {
        //TODO Cover cases when the deck is null and with different sizes
        //DeckManager.initDeck(4);
        int size = DeckManager.getSizeDeck();
        assertEquals("The size should be 7", 7, size);

//        DeckManager.initDeck(0);
//        size = DeckManager.getSizeDeck();
//        assertNotNull("The size should not be NULL", size);
    }

    @Test
    public void testDealOneCard() {
        //TODO Come up with more test cases to test Deal Card methods
        // TODO call deckManager.dealCards and test that instead of calling the cards from deck
        assertEquals("The name  of first Card should be CPU Boost", "CPU Boost", DeckManager.getCardAt(0).getName());
        assertEquals("The type  of first Card should be Upgrade", "Upgrade", DeckManager.getCardAt(0).getType());
        assertEquals("The description  of first Card should be Upgrade", "Upgrade your CPU", DeckManager.getCardAt(0).getDescription());
    }
    @Test
    public void testDealTwoCards() {
        assertEquals("The name  of first Card should be CPU Boost", "More Cores", DeckManager.getCardAt(1).getName());
        assertEquals("The type  of first Card should be Upgrade", "Defense", DeckManager.getCardAt(1).getType());
        assertEquals("The description  of first Card should be Upgrade your CPU", "Upgrade your CPU", DeckManager.getCardAt(1).getDescription());

        assertEquals("The name  of first Card should be bot.net", "bot.net", DeckManager.getCardAt(2).getName());
        assertEquals("The type  of first Card should be Attack", "Attack", DeckManager.getCardAt(2).getType());
        assertEquals("The description  of first Card should be Upgrade your CPU", "Upgrade your CPU", DeckManager.getCardAt(2).getDescription());
    }

    @Test
    public void testInvalidDealCard() {
        try {
            DeckManager.getCardAt(NULL).getName();
            fail("Null Pointer Expected");
        } catch (Throwable expected) {
        }
        // TO DO: test things like "", "B-", "XYZ", " B", "+D", "A+A" //This is comment from Professors Example
    }

    @Test
    public void testGetCardAt() {
        //TODO test card.getAt(i)
    }
    @Test
    public void testNullCard() {
        // TODO rename to "test card at index NULL"
        try {
            DeckManager.getCardAt(NULL);
            fail("Null Pointer Expected");
        } catch (Throwable expected) {
        }
    }
}