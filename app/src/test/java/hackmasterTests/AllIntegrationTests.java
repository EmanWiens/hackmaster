package hackmasterTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import hackmasterTests.integrationTests.MultiPlayerGameTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MultiPlayerGameTest.class
        //GameUnitTest.class
})
public class AllIntegrationTests { }