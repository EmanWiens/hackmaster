package hackMaster.businessTest;

import org.junit.Before;
import org.junit.Test;

import hackmaster20.business.DeckManager;
import hackmaster20.business.GameManager;
import hackmaster20.business.ResourceManager;
import hackmaster20.objects.CardClass;
import hackmaster20.objects.CardResource;
import hackmaster20.objects.EnemyAI;
import hackmaster20.objects.PlayerClass;
import hackmaster20.objects.ResourceClass;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.junit.Assert.assertNull;

/**
 * Created by Owner on 2/3/2018.
 */

public class ResourceManagerUnitTest {
    private  CardClass[] testDeck = new CardClass[4];
    private PlayerClass player1;
    private PlayerClass  player2;
    private CardClass  testCard;
    @Before
    public void setUp(){
//        player1 = new ResourceClass(1000,50,3, 53, 2, 55, 1);
//        player2 = new ResourceClass(1000,56,8, 54, 1, 99, 10);
//        resManager = new CardResource(player1, player2);
//        cardObj = new CardClass(1, "TestCard_1", "Upgrade", "desc", resManager);
        int count = 0;

        testDeck[count] = new CardClass(0, "CPU Boost", "Upgrade", "Upgrade your CPU",
                new CardResource(new ResourceClass(0, -10, 0, 0, 0,1, 0), null));
        count++;

        testDeck[count] =  new CardClass(1, "More Cores", "Defense", "Upgrade your CPU",
                new CardResource(new ResourceClass(0, -5, 10, 0, 0,0, 0), null));
        count++;

        testDeck[count] = new CardClass(3, "^^&&^%$$^$(%$$#", "%$$%$%((^%$", "$$%(^)%%^%^",
                new CardResource(new ResourceClass(0, 0, 0, 0, 0,0, 0), null));
        player1 = new PlayerClass(0,
                "HackerMan",
                new ResourceClass(100, 1, 1, 1, 1, 1, 1), testDeck);

        player2 = new EnemyAI(1,
                "Enemy Bot",
                new ResourceClass(100, 1, 1, 1, 1, 1, 1), testDeck);

        testCard =  new CardClass(2, "bot.net", "Attack", "Upgrade your CPU",
                new CardResource(new ResourceClass(100, 1, 1, 1, 1, 1, 1), null));
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
    @Test
    public void testApplyCard()
    {


        ResourceManager.applyCard(true,player1,player2,testCard, true);

        assertEquals("The health of player should be 100", 200, player1.getResources().getHealth());
        assertEquals("The hCoin of player should be 2",2, player1.getResources().gethCoin());
        assertEquals("The hCoinRate of player should be 2",2, player1.getResources().gethCoinRate());
        assertEquals("The botnet of player should be 2", 2, player1.getResources().getBotnet());
        assertEquals("The botnetRate of player should be 2", 2,player1.getResources().getBotnetRate());
        assertEquals("The CPURate of player should be 2", 2, player1.getResources().getCpuRate());
        assertEquals("The terraFlops of player should be 2", 2, player1.getResources().getTerraFlops());

        ResourceManager.applyCard(false,player1,player2,testCard, true);

        assertEquals("The health of player should be 100", 200, player2.getResources().getHealth());
        assertEquals("The hCoin of player should be 2",2, player2.getResources().gethCoin());
        assertEquals("The hCoinRate of player should be 2",2, player2.getResources().gethCoinRate());
        assertEquals("The botnet of player should be 2", 2, player2.getResources().getBotnet());
        assertEquals("The botnetRate of player should be 2", 2,player2.getResources().getBotnetRate());
        assertEquals("The CPURate of player should be 2", 2, player2.getResources().getCpuRate());
        assertEquals("The terraFlops of player should be 2", 2, player2.getResources().getTerraFlops());




    }



    @Test
    public void testInitResourceManager() {
        // TODO write a few inits and tests to see that it works
    }

    // TODO test the functions that are in the resource manager


    @Test
    public void Player_Resources_isNull() {
    //TODO
      //  CardResource.getPlayerR();
      //  assertNull("Player Resources should be null", CardResource.getPlayerR());
    }
    @Test
    public void Enemy_Resources_isNull() {
    //TODO
    //    CardResource.getEnemyR();
        //assertNull("Enemy Resources should be null", CardResource.getEnemyR());
    }
    @Test
    public void toString_isCorrect() {
//       CardResource.toString();
//        assertNull("Enemy Resources should be null", CardResource.getEnemyR());
        //TO DO
    }


}