package hackmasterAcceptanceTests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.example.owner.hackmaster20.R;
import com.robotium.solo.Solo;



public class MenuTest extends ActivityInstrumentationTestCase2{

    private Solo solo;
    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "hackmaster.presentation.MainActivity";

    private static Class<?> launcherActivityClass;
    static{
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public MenuTest() throws ClassNotFoundException {
        super(launcherActivityClass);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity()); //takes in the instrumentation and the start activity.
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void testStats() {
        solo.waitForActivity("Main Activity",2000);
        solo.clickOnImageButton(3);
        solo.clickOnImage(2);
        solo.waitForActivity("stats_view",2000);

        TextView textViewNickname = (TextView) solo.getView(R.id.color);
        TextView textViewRank = (TextView) solo.getView(R.id.rankTitleTextView);
        TextView textViewLegendaryRank = (TextView) solo.getView(R.id.textView7);
        TextView textViewWinLose = (TextView) solo.getView(R.id.winLoseTitleTxtVw);

        assertEquals("Nickname:", textViewNickname.getText().toString());
        assertEquals("Rank:", textViewRank.getText().toString());
        assertEquals("Win/Lose", textViewWinLose.getText().toString());
        assertEquals("TheLegend27", textViewLegendaryRank.getText().toString());

        solo.clickOnImage(0);
    }

    public void testPauseMenu() {
        solo.waitForActivity("Main Activity",1000);
        solo.clickOnImageButton(0);
        solo.clickOnImageButton(3);
        solo.waitForView(R.id.battle_view);
        solo.clickOnImage(14);
        solo.waitForActivity("pause_view",5000);
        TextView textViewPauseBtn = (TextView) solo.getView(R.id.pauseText);
        assertEquals("Pause Menu", textViewPauseBtn.getText().toString());
    }

}
