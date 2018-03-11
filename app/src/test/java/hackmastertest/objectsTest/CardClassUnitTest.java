package hackmastertest.objectsTest;

import org.junit.Before;
import org.junit.Test;

import hackmaster.application.Services;
import hackmaster.objects.CardClass;
import hackmaster.objects.ResourceClass;
import hackmastertest.persistenceTest.DataAccessStub;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CardClassUnitTest {
    private CardClass cardObj;
    private ResourceClass player1;
    private ResourceClass player2;

    @Before
    public void setUp(){
        Services.closeDataAccess();
        DataAccessStub dbStub = new DataAccessStub("stub");
        Services.createDataAccess(dbStub,dbStub,dbStub);

        player1 = new ResourceClass(1000,50,3, 53, 2, 55, 1);
        player2 = new ResourceClass(1000,56,8, 54, 1, 99, 10);
        cardObj = new CardClass(1, "TestCard_1", "Upgrade", "desc", player1, player2);
    }

    @Test
    public void testInitCardClass() {
        assertNotNull(cardObj);
        assertEquals("The id should be 1",1, cardObj.getID());
        assertEquals("The name of this card should be TestCard_1","TestCard_1", cardObj.getName());
        assertEquals("The type should be Upgrade","Upgrade", cardObj.getType());
        assertEquals("The description should be desc","desc", cardObj.getDescription());
    }

    @Test
    public void testToString(){
        String expectedOutput = String.format("TestCard_1\nType:Upgrade\n%s", cardObj.resString());
        System.out.println(cardObj);
        assertEquals(expectedOutput,cardObj.toString());
    }
}
