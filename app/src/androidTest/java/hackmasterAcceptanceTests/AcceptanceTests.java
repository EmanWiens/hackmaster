package hackmasterAcceptanceTests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AcceptanceTests
{
    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Acceptance tests");
        suite.addTestSuite(MenuTest.class);
        suite.addTestSuite(GamePlayTest.class);
        return suite;
    }
}

