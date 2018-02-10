package hackMaster.businessTest;

import android.support.annotation.Nullable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.InvalidClassException;
import java.sql.Types;

import hackmaster20.business.DeckManager;
import hackmaster20.business.GameManager;
import hackmaster20.objects.CardClass;
import hackmaster20.objects.CardResource;
import hackmaster20.objects.PlayerClass;
import hackmaster20.objects.ResourceClass;
import hackmaster20.presentation.DrawToScreen;

import static android.util.JsonToken.NULL;
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
        //DeckManager newDeckManager = new  DeckManager( this);
        int count=0;
        CardClass[] testDeck = new CardClass[4];
        testDeck[count] = new CardClass(0, "CPU Boost", "Upgrade", "Upgrade your CPU",
                new CardResource(new ResourceClass(0, -10, 0, 0, 0,1, 0), null));
        count++;

        testDeck[count] =  new CardClass(1, "More Cores", "Defense", "Upgrade your CPU",
                new CardResource(new ResourceClass(0, -5, 10, 0, 0,0, 0), null));
        count++;
        testDeck[count] =  new CardClass(2, "bot.net", "Attack", "Upgrade your CPU",
                new CardResource(new ResourceClass(0, -5, 10, -3, 0,0, 2), null));
        count ++;
        testDeck[count] = new CardClass(3, "^^&&^%$$^$(%$$#", "%$$%$%((^%$", "$$%(^)%%^%^",
                new CardResource(new ResourceClass(0, 0, 0, 0, 0,0, 0), null));
        DeckManager.setDeck(testDeck);
    }

    @Test
    public void testInitDeckManager() {
        //TODO Cover cases when the deck is null and with different sizes
        fail("Test Init Deck ()");
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
    public void testNullPaintCard() {
        try {
            DeckManager.paintCard(null);
            fail("Null Pointer Expected");
        } catch ( NullPointerException exp) {
        }
    }

    @Test
    public void testGetCardIndex() {
        //TODO Waiting until we finilize with CardIndex
        assertEquals("The Index should be 1",1,DeckManager.getCardIndex("More Cores",DeckManager.getADeck()));
        assertEquals("The Index should be 0",0,DeckManager.getCardIndex("&#%%#&^&)@",DeckManager.getADeck()));
        assertEquals("The Index should be 2",2,DeckManager.getCardIndex("bot.net",DeckManager.getADeck()));
    }
    @Test
    public void testNullGetCardIndex() {

    }
    public void testGetCardAt() {
        //TODO test card.getAt(i)
    }
    @Test
    public void testNullCard() {
        // TODO rename to "test card at index NULL"

        try {
            DeckManager.getCardIndex(null,null);
            fail("Null Pointer Expected");
        } catch ( NullPointerException exp) {
        }
    }
//TODO do more test for index and negative int
    @Test
    public void testCardIsNotNull() {
           assertNotNull("Shouldn't be a Null",DeckManager.getCardAt(0));
    }


}