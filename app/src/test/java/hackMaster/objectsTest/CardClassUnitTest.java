package hackMaster.objectsTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import hackmaster20.objects.CardClass;
import hackmaster20.objects.CardResource;
import hackmaster20.objects.ResourceClass;

/**
 * Created by Owner on 2/3/2018.
 */

public class CardClassUnitTest {
    private CardClass cardObj;
    private CardResource resManager;
    private ResourceClass player1;
    private ResourceClass player2;

    @Before
    public void setUp(){
        player1 = new ResourceClass(1000,50,50,50,2,2,2);
        player2 = new ResourceClass(1000,50,50, 2, 2, 2, 2);
        resManager = new CardResource(player1, player2);
        cardObj = new CardClass(1, "TestCard_1", "Upgrade", "desc", resManager);
    }


    @Test
    public void testInitCardClass() {
        // TODO write tests for initializing cards and test that it works
        assertNotNull(cardObj);
        assertEquals("The id should be 1",1, cardObj.getID());
        assertEquals("The name of this card should be TestCard_1","TestCard_1", cardObj.getName());
        assertEquals("The type should be Upgrade","Upgrade", cardObj.getType());
        assertEquals("The description should be desc","desc", cardObj.getDescription());
    }

    // TODO test the functions in the CardClass functions
    @Test
    public void testToString(){
        String expectedOutput = String.format("TestCard_1\ntype:Upgrade\n%s", resManager.toString());
        System.out.println(cardObj);
        assertEquals(expectedOutput,cardObj.toString());
    }

    @After
    public void tearDown(){
        player1 = null;
        player2 = null;
        resManager = null;
        cardObj = null;
    }
}
