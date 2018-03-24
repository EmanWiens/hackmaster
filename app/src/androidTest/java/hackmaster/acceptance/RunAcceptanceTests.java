package hackmaster.acceptance;

import junit.framework.Test;
import junit.framework.TestSuite;

public class RunAcceptanceTests
{
	public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Acceptance tests");
        suite.addTest(AcceptanceTests.suite());
        return suite;
    }
}
