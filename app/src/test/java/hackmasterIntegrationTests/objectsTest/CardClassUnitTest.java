package hackmasterIntegrationTests.objectsTest;

import org.junit.Before;
import org.junit.Test;

import hackmaster.objects.CardClass;
import hackmaster.objects.ResourceClass;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CardClassUnitTest {
    private CardClass cardObj;
    private ResourceClass player1;
    private ResourceClass player2;

    @Before
    public void setup(){
        player1 = new ResourceClass(1000,50,3, 53, 2, 55, 1);
        player2 = new ResourceClass(1000,56,8, 54, 1, 99, 10);
        cardObj = new CardClass(1, "TestCard_1", "Upgrade", "desc", player1, player2);
    }

    @Test
    public void testInitCardClass() {
        assertNotNull(cardObj);
        assertEquals(1, cardObj.getID());
        assertEquals("TestCard_1", cardObj.getName());
        assertEquals("Upgrade", cardObj.getType());
        assertEquals("desc", cardObj.getDescription());
    }

    @Test
    public void testToString(){
        String expectedOutput = String.format("TestCard_1\nType:Upgrade\n%s", cardObj.resString());
        System.out.println(cardObj);
        assertEquals(expectedOutput,cardObj.toString());
    }
}
