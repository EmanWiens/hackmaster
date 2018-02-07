package hackMaster.businessTest;

import org.junit.Before;
import org.junit.Test;


import hackmaster20.business.CardsList;
import hackmaster20.business.DeckManager;
import hackmaster20.objects.CardClass;

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
        assertEquals("The name  of first Card should be CPU Boost", "CPU Boost", DeckManager.getACard(0).getName());
        assertEquals("The type  of first Card should be Upgrade", "Upgrade", DeckManager.getACard(0).getType());
        assertEquals("The description  of first Card should be Upgrade", "Upgrade your CPU", DeckManager.getACard(0).getDescription());
    }
    @Test
    public void testDealTwoCards() {
        assertEquals("The name  of first Card should be CPU Boost", "More Cores", DeckManager.getACard(1).getName());
        assertEquals("The type  of first Card should be Upgrade", "Defense", DeckManager.getACard(1).getType());
        assertEquals("The description  of first Card should be Upgrade your CPU", "Upgrade your CPU", DeckManager.getACard(1).getDescription());

        assertEquals("The name  of first Card should be bot.net", "bot.net", DeckManager.getACard(2).getName());
        assertEquals("The type  of first Card should be Attack", "Attack", DeckManager.getACard(2).getType());
        assertEquals("The description  of first Card should be Upgrade your CPU", "Upgrade your CPU", DeckManager.getACard(2).getDescription());
    }

    @Test
    public void testInvalidDealCard() {
        try {
            DeckManager.getACard(NULL).getName();
            fail("Null Pointer Expected");
        } catch (Throwable expected) {
        }
        // TO DO: test things like "", "B-", "XYZ", " B", "+D", "A+A" //This is comment from Professors Example
    }

    @Test
    public void testGetCardIndex() {
        //TODO Waiting until we finilize with CardIndex
    }
    @Test
    public void testNullCard() {
        try {
            DeckManager.getACard(NULL);
            fail("Null Pointer Expected");
        } catch (Throwable expected) {
        }
    }
}