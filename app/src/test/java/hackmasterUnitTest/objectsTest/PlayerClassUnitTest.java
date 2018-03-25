package hackmasterUnitTest.objectsTest;


import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;

import hackmasterOG.application.Services;
import hackmasterOG.objects.PlayerClass;
import hackmasterOG.objects.ResourceClass;
import hackmasterOG.objects.CardClass;
import hackmasterOG.business.DeckManager;
import hackmasterUnitTest.persistenceTest.DataAccessStub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

public class PlayerClassUnitTest {
    private PlayerClass player1;
    private CardClass[] player1_cards;
    private ResourceClass player1_resource;
    private ResourceClass player2;

    @Before
    public void setup(){
        Services.closeDataAccess();
        DataAccessStub dbStub = new DataAccessStub("stub");
        Services.createDataAccess(dbStub,dbStub,dbStub);
        DeckManager.initDeck();

        player1_resource = new ResourceClass(1000,50,3, 0, 2, 55, 1);

        ArrayList<CardClass> listDeck = new ArrayList<>();
        dbStub.getCardSequential(listDeck);
        DeckManager.setDeck(listDeck.toArray(new CardClass[0]));
        DeckManager.resetIndex();

        player1_cards = DeckManager.dealFirstHandOfGame();
        player1 = new PlayerClass(1, "Test_Name", player1_resource, player1_cards);
        player2 = new ResourceClass(1000,56,8, 54, 1, 99, 10);
    }

    @Test
    public void testInitPlayerClass() {
        assertNotNull(player1);
        assertEquals(1,player1.getId());
        assertEquals("Test_Name",player1.getName());
        assertEquals(5, player1.getCards().length);
        assertSame(player1_resource, player1.getResources());
    }

    @Test
    public void testGetCardIndex() {
        assertEquals(-1, player1.getCardIndex(-1));
        assertEquals(0, player1.getCardIndex(0));
        assertEquals(-1, player1.getCardIndex(5));

        player1.setCard(2, DeckManager.dealNextCard());
        assertEquals(2, player1.getCardIndex(5));
    }

    @Test
    public void testPlayableCards() {
        CardClass[] playCards = player1.playableCards();
        assertEquals(3, playCards.length);
        assertEquals(new CardClass(0), playCards[0]);
        assertEquals(new CardClass(1), playCards[1]);
        assertEquals(new CardClass(4), playCards[2]);

    }

    @Test
    public void testNegativeRates() {
        ResourceClass neg_rates = new ResourceClass(0, 0, -1000, 0, -1000,1, -1000);
        player1.addResources(neg_rates);

        assertEquals(1, player1.getResources().gethCoinRate());
        assertEquals(1, player1.getResources().getBotnetRate());
        assertEquals(1, player1.getResources().getCpuRate());
    }

    @Test
    public void testNegativeResources() {
        ResourceClass neg_res = new ResourceClass(0, -10000, 0, -10000, 0, -10000, 0);
        player1.addResources(neg_res);

        assertEquals(0, player1.getResources().gethCoin());
        assertEquals(0, player1.getResources().getBotnet());
        assertEquals(0, player1.getResources().getCpu());
    }

    @Test
    public void testAddHealth() {
        assertEquals(1000, player1.getHealth());
        player1.addHealth(100);
        assertEquals(1100, player1.getHealth());
        player1.addHealth(-1200);
        assertEquals(-100, player1.getHealth());
    }

}
