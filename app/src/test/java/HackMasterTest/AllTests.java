package HackMasterTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import HackMaster.objects.CardClass;
import HackMasterTest.businessTest.DeckManagerUnitTest;
import HackMasterTest.businessTest.GameManagerUnitTest;
import HackMasterTest.businessTest.ResourceManagerUnitTest;
import HackMasterTest.objectsTest.CardClassUnitTest;
import HackMasterTest.objectsTest.EnemyAIUnitTest;
import HackMasterTest.objectsTest.PlayerClassUnitTest;
import HackMasterTest.objectsTest.ResourceClassUnitTest;
import HackMasterTest.persistenceTest.CardsListTest;
import HackMasterTest.persistenceTest.PlayerStatsSavesTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DeckManagerUnitTest.class,
        GameManagerUnitTest.class,
        ResourceManagerUnitTest.class,
        CardClassUnitTest.class,
        EnemyAIUnitTest.class,
        PlayerClassUnitTest.class,
        ResourceClassUnitTest.class,
        CardsListTest.class,
        PlayerStatsSavesTest.class
})
public class AllTests {

}