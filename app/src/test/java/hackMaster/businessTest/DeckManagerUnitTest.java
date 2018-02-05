package hackMaster.businessTest;

import org.junit.Test;

import hackmaster20.business.DeckManager;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DeckManagerUnitTest {
    @Test
    public void testInitDeck() {
        DeckManager.initDeck(4);
        int size = DeckManager.getSizeDeck();
        assertEquals("The size should be 4", 4, size);

        DeckManager.initDeck(0);
        size = DeckManager.getSizeDeck();
        assertNotNull("The size should not be NULL", size);
    }
}