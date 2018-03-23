package hackmastertest.objectsTest;

import org.junit.Before;
import org.junit.Test;
import hackmaster.objects.ResourceClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class ResourceClassUnitTest {
    private ResourceClass resource;

    @Before
    public void setup(){
        resource = new ResourceClass(100,100,5, 0, -2, -50, 0);
    }

    @Test
    public void testInitResourceClass() {
        assertNotNull(resource);
        assertEquals(100, resource.getHealth());
        assertEquals(100, resource.gethCoin());
        assertEquals(5, resource.gethCoinRate());
        assertEquals(0, resource.getBotnet());
        assertEquals(-2, resource.getBotnetRate());
        assertEquals(-50, resource.getCpu());
        assertEquals(0, resource.getCpuRate());
    }

    @Test
    public void testSetResourceClass() {
        resource.setBotnet(10);
        resource.setCpu(-10);
        resource.sethCoin(0);
        resource.setBotnetRate(0);
        resource.setCpuRate(-1);
        resource.sethCoinRate(2);
        resource.setHealth(50);
        assertEquals(10, resource.getBotnet());
        assertEquals(-10, resource.getCpu());
        assertEquals(0, resource.gethCoin());
        assertEquals(0, resource.getBotnetRate());
        assertEquals(-1, resource.getCpuRate());
        assertEquals(2, resource.gethCoinRate());
        assertEquals(50, resource.getHealth());
    }

    @Test
    public void testSingleResourceAddition() {
        resource.addBotnet(10);
        resource.addCpu(-10);
        resource.addHCoin(0);
        resource.addBotnetRate(0);
        resource.addCpuRate(-1);
        resource.addHCoinRate(2);
        resource.addHealth(50);
        assertEquals(10, resource.getBotnet());
        assertEquals(-60, resource.getCpu());
        assertEquals(100, resource.gethCoin());
        assertEquals(-2, resource.getBotnetRate());
        assertEquals(-1, resource.getCpuRate());
        assertEquals(7, resource.gethCoinRate());
        assertEquals(150, resource.getHealth());
    }

    @Test
    public void testAddResources() {
        resource.addResources(new ResourceClass(50, 0,2,10,0,-10,-1));
        assertEquals(10, resource.getBotnet());
        assertEquals(-60, resource.getCpu());
        assertEquals(100, resource.gethCoin());
        assertEquals(-2, resource.getBotnetRate());
        assertEquals(-1, resource.getCpuRate());
        assertEquals(7, resource.gethCoinRate());
        assertEquals(150, resource.getHealth());
    }

    @Test
    public void testAddRates() {
        resource.increaseHcoinByRate();
        resource.increaseCpuByRate();
        resource.increaseBotnetByRate();
        assertEquals(105, resource.gethCoin());
        assertEquals(-2, resource.getBotnet());
        assertEquals(-50, resource.getCpu());
    }
}
