package hackmastertest.objectsTest;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import hackmaster.application.Services;
import hackmaster.business.DeckManager;
import hackmaster.business.Game;
import hackmaster.business.SetUpGame;
import hackmaster.business.SinglePlayerGame;
import hackmaster.objects.CardClass;
import hackmaster.objects.EnemyAI;
import hackmaster.objects.ResourceClass;
import hackmastertest.persistenceTest.DataAccessStub;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class EnemyAIUnitTest {

    Game game = SetUpGame.setUpSinglePlayerGame();
    EnemyAI player;
    int card;

    @Before
    public void setUp() {
        Services.closeDataAccess();
        DataAccessStub dbStub = new DataAccessStub("stub");
        Services.createDataAccess(dbStub,dbStub,dbStub);
        // DeckManager.initDeck();
        card = 0;

        ResourceClass r = new ResourceClass(100, 2, 2, 2, 2, 2, 2);
        player = new EnemyAI(1, "Enemy Bot", r, DeckManager.dealFirstHandOfGame());
    }

    @Test
    public void testEnemyAI() {
        assert(game instanceof SinglePlayerGame);
        assert(game.getPlayer2() instanceof EnemyAI);
    }


    @Test
    public void testNextCard () {
        resetDeck();
        DeckManager.resetIndex();
        EnemyAI ai = new EnemyAI(0, "Ai", new ResourceClass(100,2,2,2,2,2,2), DeckManager.getADeck());

        int nextCard = ai.playNextCard(game);

    }

    private void resetDeck() {
        ArrayList<CardClass> testDeck = new ArrayList<>();
        testDeck.add(new CardClass(0, "-101 health", "Defense", "Do Nothing",
                new ResourceClass(-101, 0, 0, 0, 0,0, 0), null));
        testDeck.add(new CardClass(0, "-100 health", "Defense", "Costs a normal amount",
                new ResourceClass(-100, 0, 0,0, 0,0, 0), null));
        testDeck.add(new CardClass(0, "-99 health", "Defense", "Costs a normal amount",
                new ResourceClass(-99, 0, 0,0, 0,0, 0), null));

        testDeck.add(new CardClass(0, "-1 hCoin", "Attack", "Costs a lot of Health",
                new ResourceClass(0, -1, 0, 0, 0,0, 0), null));
        testDeck.add(new CardClass(0, "-2 hCoin", "Attack", "Costs a lot of HCoin",
                new ResourceClass(0, -2, 0, 0, 0,0, 0), null));
        testDeck.add(new CardClass(0, "-3 hCoin", "Attack", "Costs a lot of Botnet",
                new ResourceClass(0, -3, 0, 0, 0,0, 0), null));

        testDeck.add(new CardClass(0, "-1 hCoinRate", "Attack", "Costs a lot of CPU",
                new ResourceClass(0, 0, -1, 0, 0,0, 0), null)); //6
        testDeck.add(new CardClass(0, "-2 hCoinRate", "Attack", "Makes a lot of Health",
                new ResourceClass(0, 0, -2, 0, 0,0, 0), null));
        testDeck.add(new CardClass(0, "-3 hCoinRate", "Attack", "Makes a lot of HCoin",
                new ResourceClass(0, 0, -3, 0, 0,0, 0), null));

        testDeck.add(new CardClass(0, "-1 botnet", "Attack", "Makes a lot of BotNet",
                new ResourceClass(0, 0, 0, -1, 0,0, 0), null));
        testDeck.add(new CardClass(0, "-2 botnet", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, -2, 0,0, 0), null));
        testDeck.add(new CardClass(0, "-3 botnet", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, -3, 0,0, 0), null));

        testDeck.add(new CardClass(0, "-1 botnetRate", "Attack", "Makes a lot of BotNet",
                new ResourceClass(0, 0, 0, 0, -1,0, 0), null));
        testDeck.add(new CardClass(0, "-2 botnetRate", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, 0, -2,0, 0), null));
        testDeck.add(new CardClass(0, "-3 botnetRate", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, 0, -3,0, 0), null));

        testDeck.add(new CardClass(0, "-1 cpu", "Attack", "Makes a lot of BotNet",
                new ResourceClass(0, 0, 0, 0, 0,-1, 0), null));
        testDeck.add(new CardClass(0, "-2 cpu", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, 0, 0,-2, 0), null));
        testDeck.add(new CardClass(0, "-3 cpu", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, 0, 0,-3, 0), null));

        testDeck.add(new CardClass(0, "-1 cpuRate", "Attack", "Makes a lot of BotNet",
                new ResourceClass(0, 0, 0, 0, 0,0, -1), null));
        testDeck.add(new CardClass(0, "-2 cpuRate", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, 0, 0,0, -2), null));
        testDeck.add(new CardClass(0, "-3 cpuRate", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, 0, 0,0, -3), null));

        DeckManager.setDeck(testDeck.toArray(new CardClass[0]));
    }

    @Test
    public void testPlayableCards() {
        resetDeck();
        DeckManager.resetIndex();
        EnemyAI ai = new EnemyAI(0, "Ai", new ResourceClass(100,2,2,2,2,2,2), DeckManager.getADeck());

        CardClass[] playable = ai.playableCards();
        assertEquals(11, playable.length);

        assertEquals("-101 health", ai.getCard(0).getName());
        assertEquals("-100 health", ai.getCard(1).getName());


        assertEquals("-100 health", playable[0].getName());
        assertEquals("-99 health", playable[1].getName());

        assertEquals("-1 hCoin", playable[2].getName());
        assertEquals("-2 hCoin", playable[3].getName());

        assertEquals("-1 hCoinRate", playable[4].getName());

        assertEquals("-1 botnet", playable[5].getName());
        assertEquals("-2 botnet", playable[6].getName());

        assertEquals("-1 botnetRate", playable[7].getName());

        assertEquals("-1 cpu", playable[8].getName());
        assertEquals("-2 cpu", playable[9].getName());

        assertEquals("-1 cpuRate", playable[10].getName());
    }

    @Test
    public void testAIDiscard() {
        resetDeck();
        DeckManager.resetIndex();
        EnemyAI ai = new EnemyAI(0, "Ai", new ResourceClass(100,2,2,2,2,2,2), DeckManager.getADeck());

        CardClass[] playable = ai.playableCards();
        assertEquals(11, playable.length);

        fail("Write the tests to see that the AI discards");
    }

    @Test
    public void testAIBestCard() {
        resetDeck();
        DeckManager.resetIndex();
        EnemyAI ai = new EnemyAI(0, "Ai", new ResourceClass(100,2,2,2,2,2,2), DeckManager.getADeck());

        ArrayList<CardClass> tempList = new ArrayList<>();
        tempList.add(DeckManager.getCardAt(0));
        tempList.add(DeckManager.getCardAt(1));
        tempList.add(DeckManager.getCardAt(2));
        int bestCard = ai.bestCard(tempList.toArray(new CardClass[0]));
        assertEquals(0, bestCard);

        tempList.add(DeckManager.getCardAt(6));
        tempList.add(DeckManager.getCardAt(7));
        tempList.add(DeckManager.getCardAt(8));
        bestCard = ai.bestCard(tempList.toArray(new CardClass[0]));
        assertEquals(0, bestCard);

        tempList.clear();
        tempList.add(DeckManager.getCardAt(8));
        tempList.add(DeckManager.getCardAt(7));
        tempList.add(DeckManager.getCardAt(6));
        bestCard = ai.bestCard(tempList.toArray(new CardClass[0]));
        assertEquals(2, bestCard);

        tempList.clear();
        tempList.add(DeckManager.getCardAt(6));
        tempList.add(DeckManager.getCardAt(8));
        tempList.add(DeckManager.getCardAt(7));
        bestCard = ai.bestCard(tempList.toArray(new CardClass[0]));
        assertEquals(2, bestCard);

        bestCard = ai.bestCard(DeckManager.getADeck());
        assertEquals(0, bestCard);
    }
}
