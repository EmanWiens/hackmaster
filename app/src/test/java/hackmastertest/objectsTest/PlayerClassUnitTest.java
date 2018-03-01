package hackmastertest.objectsTest;


import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import hackmaster.objects.PlayerClass;
import hackmaster.objects.ResourceClass;
import hackmaster.objects.CardClass;
import hackmaster.business.DeckManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

public class PlayerClassUnitTest {
    private PlayerClass player1;
    private CardClass[] player1_cards;
    private ResourceClass player1_resource;

    //GenerateCard
    private ResourceClass player2;

    @Before
    public void setUp(){
        DeckManager.initDeck( 4);
        player1_resource = new ResourceClass(1000,50,3, 53, 2, 55, 1);
        player1_cards = DeckManager.dealCards(7);
        player1 = new PlayerClass(1, "Test_Name", player1_resource, player1_cards);
        player2 = new ResourceClass(1000,56,8, 54, 1, 99, 10);
    }

    @Test
    public void testInitPlayerClass() {
        assertNotNull(player1);
        assertEquals("id should be 1",1,player1.getId());
        assertEquals("name should be Test_Name","Test_Name",player1.getName());
        assertEquals("Test if card exists and has the correct amount of cards", 7, player1.getCards().length);
        assertSame("resource object should be same", player1_resource, player1.getResources());
    }

    @Test
    public void testGetCardIndex() {
        assertEquals("The Index should be 1",1, player1.getCardIndex(1,player1.getCards()));
        assertEquals("The Index should be 0",0, player1.getCardIndex(0,player1.getCards()));
        assertEquals("The Index should be 2",2, player1.getCardIndex(2,player1.getCards()));
    }

    @Test
    public void testSetCards() {
        player1.setCard(0, generateCard(1,"test first index", "t1","card 1"));
        player1.setCard(1, generateCard(2,"test1", "t2","card 2"));
        player1.setCard(2, generateCard(3,"test3", "t3","card 3"));
        player1.setCard(6, generateCard(10,"test edge", "t4","card 4"));

        assertEquals("Test set first index", 1, player1.getCard(0).getID());
        assertEquals("Test set card 2", 2, player1.getCard(1).getID());
        assertEquals("Test set card 3", 3, player1.getCard(2).getID());
        assertEquals("Test set edge", 10, player1.getCard(6).getID());

        try {
            player1.setCard(8, generateCard(5,"test out of bounds", "t5","card 5"));
        } catch ( ArrayIndexOutOfBoundsException exp) {
            fail("setCard Out of bounds not handled");
        }
    }

    @Test
    public void testNegativeRates() {
        ResourceClass neg_rates = new ResourceClass(0, 0, -1000, 0, -1000,1, -1000);
        player1.addResources(neg_rates);

        assertEquals("hCoinRate should be 1", 1, player1.getResources().gethCoinRate());
        assertEquals("botnetRate should be 1", 1, player1.getResources().getBotnetRate());
        assertEquals("cpuRate should be 1", 1, player1.getResources().getCpuRate());
    }

    @Test
    public void testNegativeResources() {
        ResourceClass neg_res = new ResourceClass(0, -10000, 0, -10000, 0,-10000, 0);
        player1.addResources(neg_res);

        assertEquals("hCoin should be 0", 0, player1.getResources().gethCoin());
        assertEquals("botnet should be 0", 0, player1.getResources().getBotnet());
        assertEquals("cpu should be 0", 0, player1.getResources().getCpu());
    }

    @After
    public void tearDown(){
        player1_resource = null;
        player1_cards = null;
        player1 = null;
    }

    // HELPER METHOD
    private CardClass generateCard(int id, String name, String type, String desc) {
        CardClass new_card = new CardClass(id, name, type, desc, player1_resource, player2);
        return new_card;
    }
}
