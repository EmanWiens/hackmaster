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
        // TODO fix tests for the new deck
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
        // player negative is cost and positive is gain
        // enemy negative is loss and positive is gain

        ArrayList<CardClass> testDeck = new ArrayList<CardClass>();
        testDeck.add(new CardClass(0, "Nothing", "Defense", "Do Nothing",
                new CardResource(new ResourceClass(-101, 0, 0, 0, 0,0, 0), null)));
        testDeck.add(new CardClass(0, "hundred", "Defense", "Costs a normal amount",
                new CardResource(new ResourceClass(-100, 0, 0,0, 0,0, 0), null)));
        testDeck.add(new CardClass(0, "Normal card", "Defense", "Costs a normal amount",
                new CardResource(new ResourceClass(-99, 0, 0,0, 0,0, 0), null)));
        testDeck.add(new CardClass(0, "Expensive Health", "Attack", "Costs a lot of Health",
                new CardResource(new ResourceClass(0, -1, 0, 0, 0,0, 0), null)));
        testDeck.add(new CardClass(0, "Expensive HCoin", "Attack", "Costs a lot of HCoin",
                new CardResource(new ResourceClass(0, -2, 0, 0, 0,0, 0), null)));
        testDeck.add(new CardClass(0, "Expensive BotNet", "Attack", "Costs a lot of Botnet",
                new CardResource(new ResourceClass(0, -3, 0, 0, 0,0, 0), null)));
        testDeck.add(new CardClass(0, "Expensive GPU", "Attack", "Costs a lot of CPU",
                new CardResource(new ResourceClass(0, 0, -1, 0, 0,0, 0), null))); //6
        testDeck.add(new CardClass(0, "Generate Health", "Attack", "Makes a lot of Health",
                new CardResource(new ResourceClass(0, 0, -2, 0, 0,0, 0), null)));
        testDeck.add(new CardClass(0, "Generate HCoin", "Attack", "Makes a lot of HCoin",
                new CardResource(new ResourceClass(0, 0, -3, 0, 0,0, 0), null)));
        testDeck.add(new CardClass(0, "Generate BotNet", "Attack", "Makes a lot of BotNet",
                new CardResource(new ResourceClass(0, 0, 0, -1, 0,0, 0), null)));
        testDeck.add(new CardClass(0, "Generate CPU", "Attack", "Makes a lot of CPU",
                new CardResource(new ResourceClass(0, 0, 0, -2, 0,0, 0), null)));
        testDeck.add(new CardClass(0, "Generate CPU", "Attack", "Makes a lot of CPU",
                new CardResource(new ResourceClass(0, 0, 0, -3, 0,0, 0), null)));

        deckM.setDeck(testDeck.toArray(new CardClass[0]));
    }

    @Test
    public void testPlayableCards() {
        EnemyAI ai = (EnemyAI)GameManager.getPlayer2();
        DeckManager.resetIndex();
        resetDeck();

        ai.setResources(new ResourceClass(100,2,2,2,2,2,2));
        for(int i = 0; i < GameManager.sizeOfHand; i++)
            ai.setCard(i, deckM.getCardAt(i));

        CardClass[] playable = ai.playableCards(ai);
        assertEquals("Nothing", deckM.getCardAt(0).getName());
        assertEquals("hundred", deckM.getCardAt(1).getName());

        assertEquals("Nothing", ai.getCard(0).getName());
        assertEquals("hundred", ai.getCard(1).getName());

        assertEquals("hundred", playable[0].getName());
        assertEquals("Normal card", playable[1].getName());
        assertEquals("Expensive Health", playable[2].getName());
        assertEquals("Expensive HCoin", playable[3].getName());
        assertEquals("Expensive BotNet", playable[4].getName());
        assertEquals("Expensive GPU", playable[4].getName());
    }

    @After
    public void tearDown(){
        mainAct = null;
        player = null;
        deckM = null;
    }
}
