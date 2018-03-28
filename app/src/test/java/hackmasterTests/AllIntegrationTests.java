package hackmasterTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import hackmasterTests.integrationTests.MultiPlayerGameTest;
import hackmasterTests.integrationTests.BusinessPersistenceSeamTest;
import hackmasterTests.integrationTests.SinglePlayerGameTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MultiPlayerGameTest.class,
        BusinessPersistenceSeamTest.class,
        SinglePlayerGameTest.class
})
public class AllIntegrationTests { }