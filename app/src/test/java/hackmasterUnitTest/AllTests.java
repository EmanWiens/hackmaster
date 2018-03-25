package hackmasterUnitTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import hackmasterUnitTest.businessTest.DeckManagerUnitTest;
import hackmasterUnitTest.businessTest.GameUnitTest;
import hackmasterUnitTest.businessTest.MultiPlayerGameUnitTest;
import hackmasterUnitTest.businessTest.ResourceManagerUnitTest;
import hackmasterUnitTest.businessTest.SinglePlayerGameUnitTest;
import hackmasterUnitTest.objectsTest.CardClassUnitTest;
import hackmasterUnitTest.objectsTest.EnemyAIUnitTest;
import hackmasterUnitTest.objectsTest.PlayerClassUnitTest;
import hackmasterUnitTest.objectsTest.ResourceClassUnitTest;
import hackmasterUnitTest.persistenceTest.DataAccessTest;
import hackmasterUnitTest.objectsTest.PlayerStatsSavesTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DeckManagerUnitTest.class,
        GameUnitTest.class,
        MultiPlayerGameUnitTest.class,
        ResourceManagerUnitTest.class,
        SinglePlayerGameUnitTest.class,
        CardClassUnitTest.class,
        EnemyAIUnitTest.class,
        PlayerClassUnitTest.class,
        ResourceClassUnitTest.class,
        DataAccessTest.class,
        PlayerStatsSavesTest.class
})
public class AllTests { }