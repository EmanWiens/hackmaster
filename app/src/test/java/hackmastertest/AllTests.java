package hackmastertest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import hackmastertest.businessTest.DeckManagerUnitTest;
import hackmastertest.businessTest.GameUnitTest;
import hackmastertest.businessTest.ResourceManagerUnitTest;
import hackmastertest.objectsTest.CardClassUnitTest;
import hackmastertest.objectsTest.EnemyAIUnitTest;
import hackmastertest.objectsTest.PlayerClassUnitTest;
import hackmastertest.objectsTest.ResourceClassUnitTest;
import hackmastertest.persistenceTest.PlayerStatsSavesTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DeckManagerUnitTest.class,
        GameUnitTest.class,
        ResourceManagerUnitTest.class,
        CardClassUnitTest.class,
        EnemyAIUnitTest.class,
        PlayerClassUnitTest.class,
        ResourceClassUnitTest.class,
        PlayerStatsSavesTest.class
})
public class AllTests {

}