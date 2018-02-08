package hackMaster.objectsTest;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import hackmaster20.objects.PlayerClass;
import hackmaster20.objects.ResourceClass;
import hackmaster20.objects.CardClass;
import hackmaster20.business.DeckManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Created by Owner on 2/3/2018.
 */

public class PlayerClassUnitTest {
    private PlayerClass player1;
    private CardClass[] player1_cards;
    private ResourceClass player1_resource;

    @Before
    public void setUp(){
        DeckManager.initDeck(4);
        fail("DeckManager initDeck not done");
        player1_resource = new ResourceClass(1000,50,3, 53, 2, 55, 1);
        player1_cards = DeckManager.dealCards(7);
        player1 = new PlayerClass(1, "Test_Name", player1_resource, player1_cards);
    }

    @Test
    public void runDeckManagerUnitTest() {
        fail("Need to test DeckManager first, run Deck Manager Unit Test here?");
    }

    @Test
    public void testInitPlayerClass() {
        // TODO write test functions for initializing the playerClass
        assertNotNull(player1);
        assertEquals("id should be 1",1,player1.getId());
        assertEquals("name should be Test_Name",50,player1.getName());
        fail("Test if Player has resources");
        fail("Test if Player has the correct cards");
    }

    // TODO write tests for the player class functions
    @Test
    public void testSetCards() {
        // TODO write test cases for setting cards
        fail("Need to add test cases for set cards");
    }

    @Test
    public void testFindCard() {
        // TODO write test for find card in Deck Manager
        fail("Need to have DeckManager.getCardIndex() working and tested");
        fail("Write some test for PlayerClass find card, and getCardByIndex");
    }

    @Test
    public void testAddRatesAndResources() {
        // TODO write test for adding resource for Player 1
        fail("Write test for adding resources");
    }

    @After
    public void tearDown(){
        player1_resource = null;
        player1_cards = null;
        player1 = null;
    }
}
