package comp3350.srsys;

import junit.framework.Test;
import junit.framework.TestSuite;

import comp3350.srsys.acceptance.AcceptanceTests;

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
