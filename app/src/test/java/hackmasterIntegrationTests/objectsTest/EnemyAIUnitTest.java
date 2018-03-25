package hackmasterIntegrationTests.objectsTest;

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
import hackmasterIntegrationTests.persistenceTest.DataAccessStub;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class EnemyAIUnitTest {

    Game game = SetUpGame.setUpSinglePlayerGame();
    EnemyAI player;
    int card;

    @Before
    public void setup() {
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

        ArrayList<CardClass> tempList = new ArrayList<>();
        tempList.add(DeckManager.getCardAt(0));
        tempList.add(DeckManager.getCardAt(1));
        tempList.add(DeckManager.getCardAt(2));
        EnemyAI ai = new EnemyAI(0, "Ai", new ResourceClass(100,2,2,2,2,2,2), tempList.toArray(new CardClass[0]));
        int nextCard = ai.playNextCard();
        assertEquals(1, nextCard);

        tempList.clear();
        tempList.add(DeckManager.getCardAt(6));
        tempList.add(DeckManager.getCardAt(7));
        tempList.add(DeckManager.getCardAt(8));
        ai = new EnemyAI(0, "Ai", new ResourceClass(100,2,2,2,2,2,2), tempList.toArray(new CardClass[0]));
        nextCard = ai.playNextCard();
        assertEquals(0, nextCard);

        tempList.clear();
        tempList.add(DeckManager.getCardAt(8));
        tempList.add(DeckManager.getCardAt(6));
        tempList.add(DeckManager.getCardAt(7));
        ai = new EnemyAI(0, "Ai", new ResourceClass(100,2,2,2,2,2,2), tempList.toArray(new CardClass[0]));
        nextCard = ai.playNextCard();
        assertEquals(1, nextCard);

        ai = new EnemyAI(0, "Ai", new ResourceClass(100,2,2,2,2,2,2), DeckManager.getADeck());
        nextCard = ai.playNextCard();
        assertEquals(1, nextCard);
    }

    private void resetDeck() {
        ArrayList<CardClass> testDeck = new ArrayList<>();
        int count = 0;
        testDeck.add(new CardClass(count, "-101 health", "Defense", "Do Nothing",
                new ResourceClass(-101, 0, 0, 0, 0,0, 0), null));
        count++;
        testDeck.add(new CardClass(count, "-100 health", "Defense", "Costs a normal amount",
                new ResourceClass(-100, 0, 0,0, 0,0, 0), null));
        count++;
        testDeck.add(new CardClass(count, "-99 health", "Defense", "Costs a normal amount",
                new ResourceClass(-99, 0, 0,0, 0,0, 0), null));
        count++;

        testDeck.add(new CardClass(count, "-1 hCoin", "Attack", "Costs a lot of Health",
                new ResourceClass(0, -1, 0, 0, 0,0, 0), null));
        count++;
        testDeck.add(new CardClass(count, "-2 hCoin", "Attack", "Costs a lot of HCoin",
                new ResourceClass(0, -2, 0, 0, 0,0, 0), null));
        count++;
        testDeck.add(new CardClass(count, "-3 hCoin", "Attack", "Costs a lot of Botnet",
                new ResourceClass(0, -3, 0, 0, 0,0, 0), null));
        count++;

        testDeck.add(new CardClass(count, "-1 hCoinRate", "Attack", "Costs a lot of CPU",
                new ResourceClass(0, 0, -1, 0, 0,0, 0), null)); //6
        count++;
        testDeck.add(new CardClass(count, "-2 hCoinRate", "Attack", "Makes a lot of Health",
                new ResourceClass(0, 0, -2, 0, 0,0, 0), null));
        count++;
        testDeck.add(new CardClass(count, "-3 hCoinRate", "Attack", "Makes a lot of HCoin",
                new ResourceClass(0, 0, -3, 0, 0,0, 0), null));
        count++;

        testDeck.add(new CardClass(count, "-1 botnet", "Attack", "Makes a lot of BotNet",
                new ResourceClass(0, 0, 0, -1, 0,0, 0), null));
        count++;
        testDeck.add(new CardClass(count, "-2 botnet", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, -2, 0,0, 0), null));
        count++;
        testDeck.add(new CardClass(count, "-3 botnet", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, -3, 0,0, 0), null));
        count++;

        testDeck.add(new CardClass(count, "-1 botnetRate", "Attack", "Makes a lot of BotNet",
                new ResourceClass(0, 0, 0, 0, -1,0, 0), null));
        count++;
        testDeck.add(new CardClass(count, "-2 botnetRate", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, 0, -2,0, 0), null));
        count++;
        testDeck.add(new CardClass(count, "-3 botnetRate", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, 0, -3,0, 0), null));
        count++;

        testDeck.add(new CardClass(count, "-1 cpu", "Attack", "Makes a lot of BotNet",
                new ResourceClass(0, 0, 0, 0, 0,-1, 0), null));
        count++;
        testDeck.add(new CardClass(count, "-2 cpu", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, 0, 0,-2, 0), null));
        count++;
        testDeck.add(new CardClass(count, "-3 cpu", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, 0, 0,-3, 0), null));
        count++;

        testDeck.add(new CardClass(count, "-1 cpuRate", "Attack", "Makes a lot of BotNet",
                new ResourceClass(0, 0, 0, 0, 0,0, -1), null));
        count++;
        testDeck.add(new CardClass(count, "-2 cpuRate", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, 0, 0,0, -2), null));
        count++;
        testDeck.add(new CardClass(count, "-3 cpuRate", "Attack", "Makes a lot of CPU",
                new ResourceClass(0, 0, 0, 0, 0,0, -3), null));
        count++;

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

        ArrayList<CardClass> tempList = new ArrayList<>();
        tempList.add(DeckManager.getCardAt(0));
        tempList.add(DeckManager.getCardAt(5));
        tempList.add(DeckManager.getCardAt(8));
        EnemyAI ai = new EnemyAI(0, "Ai", new ResourceClass(100,2,2,2,2,2,2), tempList.toArray(new CardClass[0]));

        int playCard = ai.playNextCard();
        assertEquals("-101 health", ai.getCard(playCard).getName());
        assertFalse(game.checkCard(playCard, ai));
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
