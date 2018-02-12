package HackMasterTest.objectsTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import HackMaster.business.DeckManager;
import HackMaster.objects.EnemyAI;
import HackMaster.objects.ResourceClass;
import HackMaster.presentation.DrawToScreen;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class EnemyAIUnitTest {

    private static DrawToScreen mainAct;
    HackMaster.objects.EnemyAI player;
    HackMaster.business.DeckManager deckM = new DeckManager(mainAct);
    int card;


    @Before
    public void setup() {
        deckM.initDeck(6);
        card = 0;

        HackMaster.objects.ResourceClass r = new ResourceClass(100, 2, 2, 2, 2, 2, 2);
        player = new EnemyAI(1, "Enemy Bot", r, deckM.dealCards(6));

    }

    @Test
    public void testEnemyAI() {
        assertNotNull(player);
    }

    @Test
    public void testNextCard () {
        card = player.playNextCard();
        assertEquals("The int card should be 1", 1,  card);
        card = player.playNextCard();
        assertEquals("The int card should be 2", 2,  card);
        card = player.playNextCard();
        card = player.playNextCard();
        card = player.playNextCard();
        assertEquals("The int card should be 5", 5,  card);
        card = player.playNextCard();
        assertEquals("The int card should be 0", 0,  card);
    }

    @After
    public void tearDown(){
        mainAct = null;
        player = null;
        deckM = null;
    }
}
