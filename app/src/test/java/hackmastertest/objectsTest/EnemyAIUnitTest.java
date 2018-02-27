package hackmastertest.objectsTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import hackmaster.business.DeckManager;
import hackmaster.business.GameManager;
import hackmaster.objects.CardClass;
import hackmaster.objects.CardResource;
import hackmaster.objects.EnemyAI;
import hackmaster.objects.ResourceClass;
import hackmaster.presentation.DrawToScreen;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class EnemyAIUnitTest {

    private static DrawToScreen mainAct;
    hackmaster.objects.EnemyAI player;
    hackmaster.business.DeckManager deckM = new DeckManager(mainAct);
    int card;


    @Before
    public void setup() {
        deckM.initDeck(6);
        card = 0;

        GameManager.setSinglePlayer(true);

        hackmaster.objects.ResourceClass r = new ResourceClass(100, 2, 2, 2, 2, 2, 2);
        player = new EnemyAI(1, "Enemy Bot", r, deckM.dealCards(6));

    }

    @Test
    public void testEnemyAI() {
        assertNotNull(player);
    }

    @Test
    public void testNextCard () {
        // TODO which card should be played next based on the heurisitc
    }

    private void resetDeck() {
        // player negative is cost and positive is gain
        // enemy negative is loss and positive is gain

        ArrayList<CardClass> testDeck = new ArrayList<CardClass>();
        testDeck.add(new CardClass(0, "-101 health", "Defense", "Do Nothing",
                new CardResource(new ResourceClass(-101, 0, 0, 0, 0,0, 0), null)));
        testDeck.add(new CardClass(0, "-100 health", "Defense", "Costs a normal amount",
                new CardResource(new ResourceClass(-100, 0, 0,0, 0,0, 0), null)));
        testDeck.add(new CardClass(0, "-99 health", "Defense", "Costs a normal amount",
                new CardResource(new ResourceClass(-99, 0, 0,0, 0,0, 0), null)));

        testDeck.add(new CardClass(0, "-1 hCoin", "Attack", "Costs a lot of Health",
                new CardResource(new ResourceClass(0, -1, 0, 0, 0,0, 0), null)));
        testDeck.add(new CardClass(0, "-2 hCoin", "Attack", "Costs a lot of HCoin",
                new CardResource(new ResourceClass(0, -2, 0, 0, 0,0, 0), null)));
        testDeck.add(new CardClass(0, "-3 hCoin", "Attack", "Costs a lot of Botnet",
                new CardResource(new ResourceClass(0, -3, 0, 0, 0,0, 0), null)));

        testDeck.add(new CardClass(0, "-1 hCoinRate", "Attack", "Costs a lot of CPU",
                new CardResource(new ResourceClass(0, 0, -1, 0, 0,0, 0), null))); //6
        testDeck.add(new CardClass(0, "-2 hCoinRate", "Attack", "Makes a lot of Health",
                new CardResource(new ResourceClass(0, 0, -2, 0, 0,0, 0), null)));
        testDeck.add(new CardClass(0, "-3 hCoinRate", "Attack", "Makes a lot of HCoin",
                new CardResource(new ResourceClass(0, 0, -3, 0, 0,0, 0), null)));

        testDeck.add(new CardClass(0, "-1 botnet", "Attack", "Makes a lot of BotNet",
                new CardResource(new ResourceClass(0, 0, 0, -1, 0,0, 0), null)));
        testDeck.add(new CardClass(0, "-2 botnet", "Attack", "Makes a lot of CPU",
                new CardResource(new ResourceClass(0, 0, 0, -2, 0,0, 0), null)));
        testDeck.add(new CardClass(0, "-3 botnet", "Attack", "Makes a lot of CPU",
                new CardResource(new ResourceClass(0, 0, 0, -3, 0,0, 0), null)));

        testDeck.add(new CardClass(0, "-1 botnetRate", "Attack", "Makes a lot of BotNet",
                new CardResource(new ResourceClass(0, 0, 0, 0, -1,0, 0), null)));
        testDeck.add(new CardClass(0, "-2 botnetRate", "Attack", "Makes a lot of CPU",
                new CardResource(new ResourceClass(0, 0, 0, 0, -2,0, 0), null)));
        testDeck.add(new CardClass(0, "-3 botnetRate", "Attack", "Makes a lot of CPU",
                new CardResource(new ResourceClass(0, 0, 0, 0, -3,0, 0), null)));

        testDeck.add(new CardClass(0, "-1 cpu", "Attack", "Makes a lot of BotNet",
                new CardResource(new ResourceClass(0, 0, 0, 0, 0,-1, 0), null)));
        testDeck.add(new CardClass(0, "-2 cpu", "Attack", "Makes a lot of CPU",
                new CardResource(new ResourceClass(0, 0, 0, 0, 0,-2, 0), null)));
        testDeck.add(new CardClass(0, "-3 cpu", "Attack", "Makes a lot of CPU",
                new CardResource(new ResourceClass(0, 0, 0, 0, 0,-3, 0), null)));

        testDeck.add(new CardClass(0, "-1 cpuRate", "Attack", "Makes a lot of BotNet",
                new CardResource(new ResourceClass(0, 0, 0, 0, 0,0, -1), null)));
        testDeck.add(new CardClass(0, "-2 cpuRate", "Attack", "Makes a lot of CPU",
                new CardResource(new ResourceClass(0, 0, 0, 0, 0,0, -2), null)));
        testDeck.add(new CardClass(0, "-3 cpuRate", "Attack", "Makes a lot of CPU",
                new CardResource(new ResourceClass(0, 0, 0, 0, 0,0, -3), null)));

        deckM.setDeck(testDeck.toArray(new CardClass[0]));
    }

    @Test
    public void testPlayableCards() {
        resetDeck();
        DeckManager.resetIndex();
        EnemyAI ai = new EnemyAI(0, "Ai", new ResourceClass(100,2,2,2,2,2,2), DeckManager.getADeck());

        CardClass[] playable = ai.playableCards();
        assertEquals("-101 health", deckM.getCardAt(0).getName());
        assertEquals("-100 health", deckM.getCardAt(1).getName());

        assertEquals("-101 health", ai.getCard(0).getName());
        assertEquals("-100 health", ai.getCard(1).getName());


        assertEquals(14, playable.length);
        assertEquals("-100 health", playable[0].getName());
        assertEquals("-99 health", playable[1].getName());

        assertEquals("-1 hCoin", playable[2].getName());
        assertEquals("-2 hCoin", playable[3].getName());

        assertEquals("-1 hCoinRate", playable[4].getName());
        assertEquals("-2 hCoinRate", playable[5].getName());

        assertEquals("-1 botnet", playable[6].getName());
        assertEquals("-2 botnet", playable[7].getName());

        assertEquals("-1 botnetRate", playable[8].getName());
        assertEquals("-2 botnetRate", playable[9].getName());

        assertEquals("-1 cpu", playable[10].getName());
        assertEquals("-2 cpu", playable[11].getName());

        assertEquals("-1 cpuRate", playable[12].getName());
        assertEquals("-2 cpuRate", playable[13].getName());
    }

    @After
    public void tearDown(){
        mainAct = null;
        player = null;
        deckM = null;
    }
}