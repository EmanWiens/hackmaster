package hackmastertest.objectsTest;

import org.junit.Before;
import org.junit.Test;
import hackmaster.objects.ResourceClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class ResourceClassUnitTest {
    private ResourceClass resource;
    private int[][] testCaseData = {
            {0,1000,50,3,53,2,55,1},
            {1,1001,51,4,54,3,56,2},
            {-2,999,49,2,52,1,54,0}
    };
    private int numberToAdd;

    @Before
    public void setUp(){
        resource = new ResourceClass(1000,50,3, 53, 2, 55, 1);
    }

    @Test
    public void testInitResourceClass() {
        assertNotNull(resource);
        assertEquals("Health should be 1000",1000,resource.getHealth());
        assertEquals("hCoin should be 50",50,resource.gethCoin());
        assertEquals("hCoin rate should be 3",3,resource.gethCoinRate());
        assertEquals("botnet should be 53",53,resource.getBotnet());
        assertEquals("botnetRate should be 2",2,resource.getBotnetRate());
        assertEquals("cpu should be 55",55,resource.getCpu());
        assertEquals("cpuRate should be 1",1,resource.getCpuRate());
    }

    @Test
    public void testSingleResourceAddition() {
        System.out.println("Testing Single Resource Addition...\n");

        // This could be written better...
        for(int i = 0; i < testCaseData.length; i++) {
            numberToAdd = testCaseData[i][0];
            resource.addHealth(numberToAdd);
            resource.addHCoin(numberToAdd);
            resource.addHCoinRate(numberToAdd);
            resource.addBotnet(numberToAdd);
            resource.addBotnetRate(numberToAdd);
            resource.addCpu(numberToAdd);
            resource.addCpuRate(numberToAdd);

            System.out.printf("Number to add -> %d ; Health -> %d ; hCoin -> %d ; hCoinRate -> %d ; " +
                            "Botnet -> %d ; BotnetRate -> %d ; CpuRate -> %d ; cpu -> %d\n",
                    numberToAdd, resource.getHealth(), resource.gethCoin(), resource.gethCoinRate(), resource.getBotnet(),
                    resource.getBotnetRate(), resource.getCpuRate(), resource.getCpu());

            assertEquals(testCaseData[i][1],resource.getHealth());
            assertEquals(testCaseData[i][2],resource.gethCoin());
            assertEquals(testCaseData[i][3],resource.gethCoinRate());
            assertEquals(testCaseData[i][4],resource.getBotnet());
            assertEquals(testCaseData[i][5],resource.getBotnetRate());
            assertEquals(testCaseData[i][6],resource.getCpu());
            assertEquals(testCaseData[i][7],resource.getCpuRate());
        }
    }

    @Test
    public void testAddResources() {
        System.out.println("\nTesting addResources...\n");

        for(int i = 0; i < testCaseData.length; i++) {
            numberToAdd = testCaseData[i][0];
            resource.addResources(new ResourceClass(numberToAdd, numberToAdd, numberToAdd, numberToAdd, numberToAdd, numberToAdd, numberToAdd));

            System.out.printf("Number to add -> %d ; Health -> %d ; hCoin -> %d ; hCoinRate -> %d ; " +
                            "Botnet -> %d ; BotnetRate -> %d ; CpuRate -> %d ; cpu -> %d\n",
                    numberToAdd, resource.getHealth(), resource.gethCoin(), resource.gethCoinRate(), resource.getBotnet(),
                    resource.getBotnetRate(), resource.getCpuRate(), resource.getCpu());

            assertEquals(testCaseData[i][1],resource.getHealth());
            assertEquals(testCaseData[i][2],resource.gethCoin());
            assertEquals(testCaseData[i][3],resource.gethCoinRate());
            assertEquals(testCaseData[i][4],resource.getBotnet());
            assertEquals(testCaseData[i][5],resource.getBotnetRate());
            assertEquals(testCaseData[i][6],resource.getCpu());
            assertEquals(testCaseData[i][7],resource.getCpuRate());
        }
    }

    @Test
    public void testAddRates() {
        System.out.println("\nTesting add Rates...\n");

        resource.increaseHcoinByRate();
        resource.increaseCpuByRate();
        resource.increaseBotnetByRate();
        assertEquals("hCoin should be 53",53, resource.gethCoin());
        assertEquals("botnet should be 55",55, resource.getBotnet());
        assertEquals("cpu should be 56",56, resource.getCpu());
    }
}
