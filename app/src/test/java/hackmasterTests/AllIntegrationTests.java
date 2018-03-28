package hackmasterTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import hackmasterTests.integrationTests.MultiPlayerGameTest;
import hackmasterTests.integrationTests.BusinessPersistenceSeamTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MultiPlayerGameTest.class,
        BusinessPersistenceSeamTest.class
        //GameUnitTest.class
})
public class AllIntegrationTests { }