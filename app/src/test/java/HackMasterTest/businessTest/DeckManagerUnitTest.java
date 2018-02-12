package HackMasterTest.businessTest;

import org.junit.Before;
import org.junit.Test;
import HackMaster.business.DeckManager;
import HackMaster.objects.CardClass;
import HackMaster.objects.CardResource;
import HackMaster.objects.ResourceClass;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

public class DeckManagerUnitTest {
    @Before
    public void setUP(){
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
   }

    @Test
    public void testDealOneCard() {
        CardClass[] Deck =DeckManager.dealCards(1);
        assertEquals("The name  of first Card should be CPU Boost", "CPU Boost", Deck[0].getName());
        assertEquals("The type  of first Card should be Upgrade", "Upgrade", Deck[0].getType());
        assertEquals("The description  of first Card should be Upgrade", "Upgrade your CPU", Deck[0].getDescription());
    }

    @Test
    public void testDealTwoCards() {
        CardClass[] Deck =DeckManager.dealCards(3);
        assertEquals("The name  of first Card should be CPU Boost", "More Cores", Deck[1].getName());
        assertEquals("The type  of first Card should be Upgrade", "Defense", Deck[1].getType());
        assertEquals("The description  of first Card should be Upgrade your CPU", "Upgrade your CPU", Deck[1].getDescription());

        assertEquals("The name  of first Card should be bot.net", "bot.net", Deck[2].getName());
        assertEquals("The type  of first Card should be Attack", "Attack", Deck[2].getType());
        assertEquals("The description  of first Card should be Upgrade your CPU", "Upgrade your CPU", Deck[2].getDescription());
    }

    @Test
    public void testInvalidDealCards(){
        try {
            DeckManager.dealCards(-1);
            fail("NegativeArraySize Expected");
        } catch ( NegativeArraySizeException exp) {
        }
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
        assertEquals("The Index should be 1",1,DeckManager.getCardIndex("More Cores",DeckManager.getADeck()));
        assertEquals("The Index should be 0",-1,DeckManager.getCardIndex("&#%%#&^&)@",DeckManager.getADeck()));
        assertEquals("The Index should be 2",2,DeckManager.getCardIndex("bot.net",DeckManager.getADeck()));
    }

    @Test
    public void testDealNextCard() {
        CardClass Card1 = DeckManager.dealNextCard();
        assertEquals("The name  of first Card should be CPU Boost", "More Cores", Card1.getName());
        assertEquals("The type  of first Card should be Upgrade", "Defense", Card1.getType());
        assertEquals("The description  of first Card should be Upgrade your CPU", "Upgrade your CPU", Card1.getDescription());

        CardClass Card2 = DeckManager.dealNextCard();

        assertEquals("The name  of first Card should be bot.net", "bot.net",Card2.getName());
        assertEquals("The type  of first Card should be Attack", "Attack", Card2.getType());
        assertEquals("The description  of first Card should be Upgrade your CPU", "Upgrade your CPU", Card2.getDescription());

    }

   @Test
    public void testGetCardAt() {
        assertEquals("The name  of first Card should be CPU Boost", "CPU Boost", DeckManager.getCardAt(0).getName());
        assertEquals("The type  of first Card should be Upgrade", "Upgrade", DeckManager.getCardAt(0).getType());
        assertEquals("The description  of first Card should be Upgrade", "Upgrade your CPU", DeckManager.getCardAt(0).getDescription());

        assertEquals("The name  of first Card should be CPU Boost", "More Cores", DeckManager.getCardAt(1).getName());
        assertEquals("The type  of first Card should be Upgrade", "Defense", DeckManager.getCardAt(1).getType());
        assertEquals("The description  of first Card should be Upgrade your CPU", "Upgrade your CPU", DeckManager.getCardAt(1).getDescription());

        assertEquals("The name  of first Card should be bot.net", "bot.net", DeckManager.getCardAt(2).getName());
        assertEquals("The type  of first Card should be Attack", "Attack", DeckManager.getCardAt(2).getType());
        assertEquals("The description  of first Card should be Upgrade your CPU", "Upgrade your CPU", DeckManager.getCardAt(2).getDescription());
    }

    @Test
    public void testInvalidGetCardAt(){
        try {
            DeckManager.getCardAt(-1);
            fail("ArrayIndexOutOfBounds Expected");
        } catch ( ArrayIndexOutOfBoundsException exp) {
        }
    }


    @Test
    public void testNullGetCardIndex() {
        try {
            DeckManager.getCardIndex(null,null);
            fail("Null Pointer Expected");
        } catch ( NullPointerException exp) {
        }
    }

    @Test
    public void testCardIsNotNull() {
           assertNotNull("Shouldn't be a Null",DeckManager.getCardAt(0));
    }


}