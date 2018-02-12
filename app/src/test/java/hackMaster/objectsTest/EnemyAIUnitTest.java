package hackMaster.objectsTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hackmaster20.business.DeckManager;
import hackmaster20.objects.EnemyAI;
import hackmaster20.objects.ResourceClass;
import hackmaster20.presentation.DrawToScreen;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * Created by Owner on 2/3/2018.
         */

public class EnemyAIUnitTest {

    private static DrawToScreen mainAct;
    hackmaster20.objects.EnemyAI player;
    hackmaster20.business.DeckManager deckM = new DeckManager(mainAct);
    int card;


    @Before
    public void setup() {
        deckM.initDeck(6);
        card = 0;

        hackmaster20.objects.ResourceClass r = new ResourceClass(100, 2, 2, 2, 2, 2, 2);
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
