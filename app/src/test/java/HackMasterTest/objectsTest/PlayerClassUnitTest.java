package HackMasterTest.objectsTest;


import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import HackMaster.objects.CardResource;
import HackMaster.objects.PlayerClass;
import HackMaster.objects.ResourceClass;
import HackMaster.objects.CardClass;
import HackMaster.business.DeckManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

public class PlayerClassUnitTest {
    private PlayerClass player1;
    private CardClass[] player1_cards;
    private ResourceClass player1_resource;

    //GenerateCard
    private CardResource resManager;
    private ResourceClass player2;

    @Before
    public void setUp(){
        DeckManager.initDeck( 4);
        player1_resource = new ResourceClass(1000,50,3, 53, 2, 55, 1);
        player1_cards = DeckManager.dealCards(7);
        player1 = new PlayerClass(1, "Test_Name", player1_resource, player1_cards);
        player2 = new ResourceClass(1000,56,8, 54, 1, 99, 10);
        resManager = new CardResource(player1_resource, player2);
    }

    @Test
    public void testInitPlayerClass() {
        // TODO write test functions for initializing the playerClass
        assertNotNull(player1);
        assertEquals("id should be 1",1,player1.getId());
        assertEquals("name should be Test_Name","Test_Name",player1.getName());
        assertEquals("Test if card exists and has the correct amount of cards", 7, player1.getCards().length);
        assertSame("resource object should be same", player1_resource, player1.getResources());
    }

    // TODO write tests for the player class functions
    @Test
    public void testSetCards() {
        // TODO write test cases for setting cards
        player1.setCard(0, generateCard(1,"test first index", "t1","card 1"));
        player1.setCard(1, generateCard(2,"test1", "t2","card 2"));
        player1.setCard(2, generateCard(3,"test3", "t3","card 3"));
        player1.setCard(6, generateCard(10,"test edge", "t4","card 4"));

        assertEquals("Test set first index", 1, player1.getCard(0).getID());
        assertEquals("Test set card 2", 2, player1.getCard(1).getID());
        assertEquals("Test set card 3", 3, player1.getCard(2).getID());
        assertEquals("Test set edge", 10, player1.getCard(6).getID());

        try {
            player1.setCard(8, generateCard(5,"test out of bounce", "t5","card 5"));
        } catch ( ArrayIndexOutOfBoundsException exp) {
            fail("setCard Out of bounce not handled");
        }
    }

    @Test
    public void testFindCard() {
        // TODO write test for find card in Deck Manager
        String name_first = player1.getCard(0).getName();
        String name2 = player1.getCard(1).getName();
        String name3 = player1.getCard(2).getName();
        String name_edge = player1.getCard(6).getName();

        assertEquals("Test find first card", 0, player1.findPlayerCardIndex(name_first));
        assertEquals("Test find card 2", 1, player1.findPlayerCardIndex(name2  ));
        assertEquals("Test find card 3", 2, player1.findPlayerCardIndex(name3));
        assertEquals("Test find edge card", 6, player1.findPlayerCardIndex(name_edge));
        assertEquals("Test non-existing card", -1, player1.findPlayerCardIndex("Test_null"));
    }

    @After
    public void tearDown(){
        player1_resource = null;
        player1_cards = null;
        player1 = null;
    }

    // HELPER METHOD
    private CardClass generateCard(int id, String name, String type, String desc) {
        CardClass new_card = new CardClass(id, name, type, desc, resManager);
        return new_card;
    }
}