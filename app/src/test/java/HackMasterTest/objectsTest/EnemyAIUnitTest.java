package HackMasterTest.objectsTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import HackMaster.business.DeckManager;
import HackMaster.business.GameManager;
import HackMaster.objects.CardClass;
import HackMaster.objects.CardResource;
import HackMaster.objects.EnemyAI;
import HackMaster.objects.PlayerClass;
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

        GameManager.setSinglePlayer(true);

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

    private void resetDeck() {
        CardClass[] testDeck = new CardClass[10];
        testDeck[0] = new CardClass(0, "Nothing", "Defense", "Do Nothing",
                new CardResource(new ResourceClass(-101, 0, 0, 0, 0,0, 0), null));
        testDeck[1] = new CardClass(0, "Normal card", "Defense", "Costs a normal amount",
                new CardResource(new ResourceClass(-100, 0, 0,0, 0,0, 0), null));
        testDeck[2] = new CardClass(0, "Expensive Health", "Attack", "Costs a lot of Health",
                new CardResource(new ResourceClass(0, 2, 0, 0, 0,0, 0), null));
        testDeck[3] = new CardClass(0, "Expensive HCoin", "Attack", "Costs a lot of HCoin",
                new CardResource(new ResourceClass(0, 1, 0, 0, 0,0, 0), null));
        testDeck[4] = new CardClass(0, "Expensive BotNet", "Attack", "Costs a lot of Botnet",
                new CardResource(new ResourceClass(0, 0, 0, -2000, 0,0, 0), null));
        testDeck[5] = new CardClass(0, "Expensive GPU", "Attack", "Costs a lot of CPU",
                new CardResource(new ResourceClass(0, 0, 0, 0, 0,-2000, 0), null));
        testDeck[6] = new CardClass(0, "Generate Health", "Attack", "Makes a lot of Health",
                new CardResource(new ResourceClass(2000, 0, 0, 0, 0,0, 0), null));
        testDeck[7] = new CardClass(0, "Generate HCoin", "Attack", "Makes a lot of HCoin",
                new CardResource(new ResourceClass(0, 2000, 0, 0, 0,0, 0), null));
        testDeck[8] = new CardClass(0, "Generate BotNet", "Attack", "Makes a lot of BotNet",
                new CardResource(new ResourceClass(0, 0, 0, 2000, 0,0, 0), null));
        testDeck[9] = new CardClass(0, "Generate CPU", "Attack", "Makes a lot of CPU",
                new CardResource(new ResourceClass(0, 0, 0, 0, 0,2000, 0), null));

        GameManager.setDeck(testDeck);
    }

    @Test
    public void testPlayableCards() {
        EnemyAI ai = (EnemyAI)GameManager.getPlayer2();

        ai.setResources(new ResourceClass(100,2,2,2,2,2,2));
        ArrayList<CardClass> playable = ai.playableCards();
    }

    @After
    public void tearDown(){
        mainAct = null;
        player = null;
        deckM = null;
    }
}
