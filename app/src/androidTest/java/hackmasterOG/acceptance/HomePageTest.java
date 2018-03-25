package hackmasterOG.acceptance;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

//import hackmaster.presentation.MainActivity;

public class HomePageTest extends ActivityInstrumentationTestCase2{

    private Solo solo;
    private static final String LauncherActivity = "hackmaster.presentation.MainActivity";
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "hackmaster.presentation.MainActivity";

    private static Class<?> launcherActivityClass;
    static{
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public HomePageTest() throws ClassNotFoundException
    {
        super(launcherActivityClass);
    }

//    public void setUp() throws Exception
//    {
//        solo = new Solo(getInstrumentation(), getActivity());
//
//        // Disable this for full acceptance test
//        // System.out.println("Injecting stub database.");
//        // Services.createDataAccess(new DataAccessStub(Main.dbName));
//    }
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity()); //takes in the instrumentation and the start activity.
    }

    @Override
    public void tearDown() throws Exception
    {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    // Please note again that this is not a complete set of acceptance tests
    public void testMainMenu()
    {
        //solo.waitForActivity("Main Activity");
       // solo.clickOnButton("Single Player");
      //  solo.clickOnButton(R.id.singlePlayBtn);
          solo.clickOnImage(0);
     //   solo.clickOnButton(R.id.singlePlayBtn);
    }

}
