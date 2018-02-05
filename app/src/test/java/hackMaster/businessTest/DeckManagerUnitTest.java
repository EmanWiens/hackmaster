package hackMaster.businessTest;

import org.junit.Test;

import hackmaster20.business.DeckManager;
import hackmaster20.objects.CardClass;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;


public class DeckManagerUnitTest {
    @Test
    public void InitDeck_isCorrect() {
        DeckManager.initDeck(4);
        int size = DeckManager.getSizeDeck();
        assertEquals("The size should be 4", 4, size);
    }
    @Test
    public void InitDeck_isNot_Null() {
        DeckManager.initDeck(0);
        int size = DeckManager.getSizeDeck();
        assertNotNull("The size should not be NULL", size);
    }
    @Test
    public void Pick_Cards_isCorrect() {
        CardClass cards[] = DeckManager.pickCards();
        assertEquals("The name  of first Card should be CPU Boost", "CPU Boost", cards[0].getName());
    }
}