package hackmasterIntegrationTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import hackmaster.business.SinglePlayerGame;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        SinglePlayerGameTest.class
        //GameUnitTest.class
})
public class AllIntegrationTests { }