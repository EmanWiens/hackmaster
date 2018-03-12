package hackmastertest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import hackmastertest.businessTest.DeckManagerUnitTest;
import hackmastertest.businessTest.GameUnitTest;
import hackmastertest.businessTest.MultiPlayerGameUnitTest;
import hackmastertest.businessTest.ResourceManagerUnitTest;
import hackmastertest.businessTest.SinglePlayerGameUnitTest;
import hackmastertest.objectsTest.CardClassUnitTest;
import hackmastertest.objectsTest.EnemyAIUnitTest;
import hackmastertest.objectsTest.PlayerClassUnitTest;
import hackmastertest.objectsTest.ResourceClassUnitTest;
import hackmastertest.persistenceTest.PlayerDataAccessTest;
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
        PlayerStatsSavesTest.class,
        SinglePlayerGameUnitTest.class,
        MultiPlayerGameUnitTest.class,
        PlayerDataAccessTest.class,
        PlayerStatsSavesTest.class
})
public class AllTests {

}