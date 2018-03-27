package hackmasterAcceptanceTests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.example.owner.hackmaster20.R;
import com.robotium.solo.Solo;

import hackmaster.business.DeckManager;

public class GamePlayTest extends ActivityInstrumentationTestCase2{

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

    public GamePlayTest() throws ClassNotFoundException
    {
        super(launcherActivityClass);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity()); //takes in the instrumentation and the start activity.
        DeckManager.setRandom(new StubRandom());
    }

    @Override
    public void tearDown() throws Exception
    {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void testSinglePlayerGame()
    {
        solo.waitForActivity("Main Activity",1000);
        solo.clickOnImageButton(0);
        solo.waitForView(R.id.battle_view);

        TextView textPlayerHealh = (TextView) solo.getView(R.id.healthP);
        TextView textPlayerMiner = (TextView) solo.getView(R.id.minerP);
        TextView textPlayerCPU = (TextView) solo.getView(R.id.cSpeedP);
        TextView textPlayerBotnet = (TextView) solo.getView(R.id.botnetP);

        TextView textEnemyHealh = (TextView) solo.getView(R.id.healthE);
        TextView textEnemyMiner = (TextView) solo.getView(R.id.minerE);
        TextView textEnemyCPU = (TextView) solo.getView(R.id.cSpeedE);
        TextView textEnemyBotnet = (TextView) solo.getView(R.id.botnetE);

        solo.sleep(2000);
        solo.clickOnImageButton(0);
        solo.sleep (2000);
        assertEquals("Health: 99%", textPlayerHealh.getText().toString());
        assertEquals("Health: 90%", textEnemyHealh.getText().toString());
        solo.clickOnImageButton(2);
        solo.clickOnImageButton(2);
        solo.sleep (2000);
        assertEquals("Health: 74%", textPlayerHealh.getText().toString());
        assertEquals("Health: 80%", textEnemyHealh.getText().toString());
        solo.clickOnImageButton(0);
        solo.sleep (2000);
        assertEquals("Health: 94%", textPlayerHealh.getText().toString());
        assertEquals("Health: 80%", textEnemyHealh.getText().toString());
        solo.clickOnImageButton(6);
        solo.sleep (2000);
        solo.clickOnImageButton(0);
        solo.sleep (2000);
        assertEquals("Health: 94%", textPlayerHealh.getText().toString());
        assertEquals("Health: 80%", textEnemyHealh.getText().toString());
        solo.clickOnImageButton(0);
        solo.sleep (2000);

        assertEquals("Health: 74%", textPlayerHealh.getText().toString());
        assertEquals("Health: 80%", textEnemyHealh.getText().toString());

        assertEquals("\nHackCoin Rate: 1" + "\n----\n" + "HackCoin: 3", textPlayerMiner.getText().toString());
        assertEquals("\nCPU Rate: 2" + "\n----\n" + "CPU: 22", textPlayerCPU.getText().toString());
        assertEquals("\nBotnet gen: 2" + "\n----\n" + "Botnet: 15", textPlayerBotnet.getText().toString());

        assertEquals("\nHackCoin Rate: 3" + "\n----\n" + "HackCoin: 17", textEnemyMiner.getText().toString());
        assertEquals("\nCPU Rate: 2" + "\n----\n" + "CPU: 22", textEnemyCPU.getText().toString());
        assertEquals("\nBotnet gen: 2" + "\n----\n" + "Botnet: 2", textEnemyBotnet.getText().toString());
        solo.goBack();
    }

    // Please note again that this is not a complete set of acceptance tests
    public void testMultiplePlayerGame() {
        solo.waitForActivity("Main Activity",1000);
        solo.clickOnImageButton(1);
        solo.waitForView(R.id.battle_view);

        solo.sleep(1000);
        TextView textPlayerHealh = (TextView) solo.getView(R.id.healthP);
        TextView textPlayerMiner;
        TextView textPlayerCPU;
        TextView textPlayerBotnet;

        TextView textEnemyHealh = (TextView) solo.getView(R.id.healthE);
        TextView textEnemyMiner;
        TextView textEnemyCPU;
        TextView textEnemyBotnet;
        TextView textPlayerTurn;
        solo.clickOnImageButton(0);
        textPlayerTurn = (TextView) solo.getView(R.id.textViewPlayerTurn);
        solo.waitForView(R.id.continue_view);
        assertEquals("Player 2's Turn", textPlayerTurn.getText().toString());
        solo.clickOnButton(0);
        solo.waitForView(R.id.battle_view);
        assertEquals("Health: 100%", textPlayerHealh.getText().toString());
        assertEquals("Health: 90%", textEnemyHealh.getText().toString());
        solo.clickOnImageButton(3);
        textPlayerTurn = (TextView) solo.getView(R.id.textViewPlayerTurn);
        solo.waitForView(R.id.continue_view);
        assertEquals("Player 1's Turn", textPlayerTurn.getText().toString());
        solo.clickOnButton(0);
        solo.waitForView(R.id.battle_view);
        textPlayerHealh = (TextView) solo.getView(R.id.healthP);
        textEnemyHealh = (TextView) solo.getView(R.id.healthE);
        assertEquals("Health: 95%", textPlayerHealh.getText().toString());
        assertEquals("Health: 90%", textEnemyHealh.getText().toString());
        solo.clickOnImageButton(0);
        solo.clickOnButton(0);
        solo.waitForView(R.id.battle_view);


        textPlayerHealh = (TextView) solo.getView(R.id.healthP);
        textPlayerMiner = (TextView) solo.getView(R.id.minerP);
        textPlayerCPU = (TextView) solo.getView(R.id.cSpeedP);
        textPlayerBotnet = (TextView) solo.getView(R.id.botnetP);

        textEnemyHealh = (TextView) solo.getView(R.id.healthE);
        textEnemyMiner = (TextView) solo.getView(R.id.minerE);
        textEnemyCPU = (TextView) solo.getView(R.id.cSpeedE);
        textEnemyBotnet = (TextView) solo.getView(R.id.botnetE);

        assertEquals("Health: 115%", textPlayerHealh.getText().toString());
        assertEquals("Health: 90%", textEnemyHealh.getText().toString());
        assertEquals("\nHackCoin Rate: 1" + "\n----\n" + "HackCoin: 8", textPlayerMiner.getText().toString());
        assertEquals("\nCPU Rate: 2" + "\n----\n" + "CPU: 12", textPlayerCPU.getText().toString());
        assertEquals("\nBotnet gen: 2" + "\n----\n" + "Botnet: 12", textPlayerBotnet.getText().toString());

        assertEquals("\nHackCoin Rate: 2" + "\n----\n" + "HackCoin: 14", textEnemyMiner.getText().toString());
        assertEquals("\nCPU Rate: 2" + "\n----\n" + "CPU: 14", textEnemyCPU.getText().toString());
        assertEquals("\nBotnet gen: 2" + "\n----\n" + "Botnet: 13", textEnemyBotnet.getText().toString());
        solo.clickOnImageButton(0);
        solo.clickOnButton(0);
        solo.clickOnImageButton(1);
        solo.clickOnButton(0);
        solo.clickOnImageButton(2);
        solo.clickOnButton(0);
        solo.clickOnImageButton(3);
        solo.clickOnButton(0);
        solo.clickOnImageButton(4);
        solo.clickOnButton(0);
        solo.clickOnImageButton(2);
        solo.clickOnButton(0);
        solo.clickOnImageButton(3);
        solo.clickOnButton(0);
        solo.clickOnImageButton(4);
        solo.clickOnButton(0);
        solo.clickOnImageButton(3);
        solo.clickOnButton(0);
        solo.clickOnImageButton(2);
        solo.clickOnButton(0);
    }


}
