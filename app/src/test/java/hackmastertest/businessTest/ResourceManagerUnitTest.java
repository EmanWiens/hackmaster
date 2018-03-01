package hackmastertest.businessTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import hackmaster.business.DeckManager;
import hackmaster.business.GameManager;
import hackmaster.business.ResourceManager;
import hackmaster.objects.CardClass;
import hackmaster.objects.EnemyAI;
import hackmaster.objects.PlayerClass;
import hackmaster.objects.ResourceClass;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class ResourceManagerUnitTest {
    private CardClass[] testDeck = new CardClass[4];
    private PlayerClass player1;
    private PlayerClass  player2;
    private PlayerClass  player3;
    private CardClass testCardEffectPlayerOnly;
    private CardClass testCardEffectEnemyCardOnly;
    private CardClass testCardEffectPlayerAndEnemy;
    @Before
    public void setUp(){
        int count = 0;
        testDeck[count] = new CardClass(0, "CPU Boost", "Upgrade", "Upgrade your CPU",
                new ResourceClass(0, -10, 0, 0, 0,1, 0), null);
        count++;

        testDeck[count] =  new CardClass(1, "More Cores", "Defense", "Upgrade your CPU",
                new ResourceClass(0, -5, 10, 0, 0,0, 0), null);
        count++;

        testDeck[count] = new CardClass(3, "^^&&^%$$^$(%$$#", "%$$%$%((^%$", "$$%(^)%%^%^",
                new ResourceClass(0, 0, 0, 0, 0,0, 0), null);
        player1 = new PlayerClass(0,
                "HackerMan",
                new ResourceClass(100, 1, 1, 1, 1, 1, 1), testDeck);

        player2 = new EnemyAI(1,
                "Enemy Bot",
                new ResourceClass(100, 1, 1, 1, 1, 1, 1), testDeck);

        player3 = new PlayerClass(1,
                "Tester",
                new ResourceClass(100,1, 1, 1, 1, 1, 1), testDeck);

        testCardEffectPlayerOnly =  new CardClass(2, "bot.net", "Attack", "Upgrade your CPU",
                new ResourceClass(100, 1, 1, 1, 1, 1, 1), null);
        testCardEffectEnemyCardOnly =  new CardClass(2, "bot.net", "Attack", "Upgrade your CPU",
                new ResourceClass(0, 0, 0, 0, 0, 0, 0), new ResourceClass(-200, -2, -2, -2, -2, -2, -2));
        testCardEffectPlayerAndEnemy =  new CardClass(2, "bot.net", "Attack", "Upgrade your CPU",
                new ResourceClass(-10, -10, 0, 0, 0, 0, 0), new ResourceClass(-50, 0, 0, 0, 0, 0, 0));
    }

    @Test
    public void testApplyCard()
    {
        // TODO this test should be in gameManager (game manager has this function)

        ResourceManager.applyCard(true,player1,player2, testCardEffectPlayerOnly, true);
        testIndividualResources(200,2,2, 2,2,2, 2, true);

        ResourceManager.applyCard(false,player1,player2, testCardEffectPlayerOnly, true);
        testIndividualResources(200,2,2, 2,2,2, 2, false);

        ResourceManager.applyCard(true,player1,player2, testCardEffectEnemyCardOnly, true);
        testIndividualResources(0,0,1, 0,1,1, 0, false);

        ResourceManager.applyCard(false,player1,player2, testCardEffectEnemyCardOnly, true);
        testIndividualResources(0,0,1, 0,1,1, 0, true);

        ResourceManager.applyCard(true,player1,player2, testCardEffectPlayerAndEnemy, true);
        testEveryoneResources(new int[]{-10, 0, 1, 0, 1, 1, 0},new int[]{-50, 0, 1, 0, 1, 1, 0});

        ResourceManager.applyCard(false,player1,player2, testCardEffectPlayerAndEnemy, true);
        testEveryoneResources(new int[]{-60, 0, 1, 0, 1, 1, 0},new int[]{-60, 0, 1, 0, 1, 1, 0});

    }

    @Test
    public void testApplySingleCardBounds() {
        CardClass[] deck = setDeck();
        EnemyAI ai = new EnemyAI(0, "Ai", new ResourceClass(100,2,2,2,2,2,2), deck);

        assertEquals(false, GameManager.checkCard(0, ai));
        assertEquals(true, GameManager.checkCard(1, ai));
        assertEquals(true, GameManager.checkCard(2, ai));

        assertEquals(true, GameManager.checkCard(3, ai));
        assertEquals(true, GameManager.checkCard(4, ai));
        assertEquals(false, GameManager.checkCard(5, ai));

        assertEquals(true, GameManager.checkCard(6, ai));
        assertEquals(true, GameManager.checkCard(7, ai));
        assertEquals(false, GameManager.checkCard(8, ai));

        assertEquals(true, GameManager.checkCard(9, ai));
        assertEquals(true, GameManager.checkCard(10, ai));
        assertEquals(false, GameManager.checkCard(11, ai));

        assertEquals(true, GameManager.checkCard(12, ai));
        assertEquals(true, GameManager.checkCard(13, ai));
        assertEquals(false, GameManager.checkCard(14, ai));

        assertEquals(true, GameManager.checkCard(15, ai));
        assertEquals(true, GameManager.checkCard(16, ai));
        assertEquals(false, GameManager.checkCard(17, ai));

        assertEquals(true, GameManager.checkCard(18, ai));
        assertEquals(true, GameManager.checkCard(19, ai));
        assertEquals(false, GameManager.checkCard(20, ai));
    }

    private CardClass[] setDeck() {
        // player negative is cost and positive is gain
        // enemy negative is loss and positive is gain

        ArrayList<CardClass> testDeck = new ArrayList<CardClass>();
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
                new ResourceClass(0, 0, -1, 0, 0,0, 0), null));
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


        return testDeck.toArray(new CardClass[0]);
    }

    private void testIndividualResources(int health, int hCoin, int hCoinRate, int botnet, int botnetRate , int CPURate , int terraFlops, boolean player1ToCheck ) {
    if (player1ToCheck) {
        assertEquals("The health of player1 should be " + health, health, player1.getResources().getHealth());
        assertEquals("The hCoin of player1 should be " + hCoin, hCoin, player1.getResources().gethCoin());
        assertEquals("The hCoinRate of player1 should be " + hCoinRate, hCoinRate, player1.getResources().gethCoinRate());
        assertEquals("The botnet of player1 should be " + botnet, botnet, player1.getResources().getBotnet());
        assertEquals("The botnetRate of player1 should be " + botnetRate, botnetRate, player1.getResources().getBotnetRate());
        assertEquals("The CPURate of player1 should be " + CPURate, CPURate, player1.getResources().getCpuRate());
        assertEquals("The terraFlops of player1 should be " + terraFlops, terraFlops, player1.getResources().getCpu());
    }
    else {
        assertEquals("The health of player2 should be " + health, health, player2.getResources().getHealth());
        assertEquals("The hCoin of player2 should be " + hCoin, hCoin, player2.getResources().gethCoin());
        assertEquals("The hCoinRate of player2 should be " + hCoinRate, hCoinRate, player2.getResources().gethCoinRate());
        assertEquals("The botnet of player2 should be " + botnet, botnet, player2.getResources().getBotnet());
        assertEquals("The botnetRate of player2 should be " + botnetRate, botnetRate, player2.getResources().getBotnetRate());
        assertEquals("The CPURate of player2 should be " + CPURate, CPURate, player2.getResources().getCpuRate());
        assertEquals("The terraFlops of player2 should be " + terraFlops, terraFlops, player2.getResources().getCpu());
    }
}
    private void testEveryoneResources(int [] Player1Res ,int [] Player2Res ) {
        assertEquals("The health of player1 should be " + Player1Res[0], Player1Res[0], player1.getResources().getHealth());
        assertEquals("The hCoin of player1 should be " + Player1Res[1], Player1Res[1], player1.getResources().gethCoin());
        assertEquals("The hCoinRate of player1 should be " + Player1Res[2], Player1Res[2], player1.getResources().gethCoinRate());
        assertEquals("The botnet of player1 should be " + Player1Res[3], Player1Res[3], player1.getResources().getBotnet());
        assertEquals("The botnetRate of player1 should be " + Player1Res[4], Player1Res[4], player1.getResources().getBotnetRate());
        assertEquals("The CPURate of player1 should be " + Player1Res[5], Player1Res[5], player1.getResources().getCpuRate());
        assertEquals("The terraFlops of player1 should be " + Player1Res[6], Player1Res[6], player1.getResources().getCpu());

        assertEquals("The health of player2 should be " + Player2Res[0], Player2Res[0], player2.getResources().getHealth());
        assertEquals("The hCoin of player2 should be " + Player2Res[1], Player2Res[1], player2.getResources().gethCoin());
        assertEquals("The hCoinRate of player2 should be " + Player2Res[2], Player2Res[2], player2.getResources().gethCoinRate());
        assertEquals("The botnet of player2 should be " + Player2Res[3], Player2Res[3], player2.getResources().getBotnet());
        assertEquals("The botnetRate of player2 should be " + Player2Res[4], Player2Res[4], player2.getResources().getBotnetRate());
        assertEquals("The CPURate of player2 should be " + Player2Res[5], Player2Res[5], player2.getResources().getCpuRate());
        assertEquals("The terraFlops of player2 should be " + Player2Res[6], Player2Res[6], player2.getResources().getCpu());

    }

    @Test
    public void testApplyTurnRate(){
        ResourceManager.applyTurnRate(player3,true);
        assertEquals("The hCoinRate of player3 should be 2", 2,player3.getResources().gethCoin());
        assertEquals("The terraFlops of player3 should be 2", 2,player3.getResources().getCpu());
        assertEquals("The botnetRate of player3 should be 2", 2,player3.getResources().getBotnet());
    }

    @Test
    public void testNullApplyTurnRate(){
        try {
            ResourceManager.applyTurnRate(null,true);
            fail("Null Pointer Expected");
        } catch ( NullPointerException exp) {
        }
    }

    @Test
    public void testNullApplyCard()
    {
        try {
            ResourceManager.applyCard(true, null,null,null, true);
            fail("Null Pointer Expected");
        } catch ( NullPointerException exp) {
        }
    }
  @After
    public void tearDown() {
        player1=null;
        player2=null;
        testCardEffectPlayerOnly =null;
        testCardEffectEnemyCardOnly =null;
  }

}