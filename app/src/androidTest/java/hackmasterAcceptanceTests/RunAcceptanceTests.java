package hackmasterAcceptanceTests;

import android.view.Menu;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MenuTest.class,
        GamePlayTest.class
})

public class RunAcceptanceTests { }
