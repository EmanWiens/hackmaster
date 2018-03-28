package hackmasterTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import hackmasterTests.businessTest.DeckManagerUnitTest;
import hackmasterTests.businessTest.GameUnitTest;
import hackmasterTests.businessTest.MultiPlayerGameUnitTest;
import hackmasterTests.businessTest.ResourceManagerUnitTest;
import hackmasterTests.businessTest.SinglePlayerGameUnitTest;
import hackmasterTests.objectsTest.CardClassUnitTest;
import hackmasterTests.objectsTest.EnemyAIUnitTest;
import hackmasterTests.objectsTest.PlayerClassUnitTest;
import hackmasterTests.objectsTest.ResourceClassUnitTest;
import hackmasterTests.persistenceTest.DataAccessTest;
import hackmasterTests.objectsTest.PlayerStatsSavesTest;

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
public class AllUnitTests { }