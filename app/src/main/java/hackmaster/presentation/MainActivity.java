package hackmaster.presentation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.owner.hackmaster20.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import hackmaster.application.DBController;
import hackmaster.business.Game;
import hackmaster.business.SetUpGame;
import hackmaster.objects.PlayerStatsSaves;

public class MainActivity extends AppCompatActivity {

    private MusicManager musicManager;
    private Game gameInSession;
    private PlayerStatsSaves playerStats;

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        copyDatabaseToDevice();
        DBController.startUp();
        musicManager = new MusicManager(this);
        musicManager.backGroundMusicStart();
        musicManager.initSoundPool();

        Render.updateRender(this, musicManager);
        Render.setContentView(R.layout.main_activity);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DBController.shutDown();
    }

    public void checkStateSound() {
        ImageButton muteBtn = findViewById(R.id.muteBtn);
        if (musicManager.getStateMusic()) {
            muteBtn.setBackgroundResource(R.drawable.volumeunmute);
        } else {
            muteBtn.setBackgroundResource(R.drawable.volumemute);
        }
    }

    public void muteSoundBackground(View v) {
        // TODO put in render
        ImageButton muteBtn = findViewById(R.id.muteBtn);
        if (musicManager.getStateMusic()) {
            muteBtn.setBackgroundResource(R.drawable.volumemute);
            musicManager.pauseBacgroundMusic();
        } else {
            muteBtn.setBackgroundResource(R.drawable.volumeunmute);
            musicManager.resumeBacgroundMusic();
        }
    }

    @Override
    public void onBackPressed() {
        if (gameInSession != null) {
            if (!gameInSession.gamePaused()) {
                exitGameDialog();
            } else if (gameInSession.gamePaused()) {
                Render.setContentView(R.layout.battle_view);
                gameInSession.unpauseGame();
                Render.setBorderId(-1);
                Render.updateScreen();
            }
        } else {
            if (gameInSession != null) {
                Render.setContentView(R.layout.main_activity);
                checkStateSound();
            }
        }
    }

    private void exitGameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You are about to exit the game.")
                .setPositiveButton("Exit game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Render.setContentView(R.layout.main_activity);
                        checkStateSound();
                        gameInSession = null;
                    }
                })
                .setNegativeButton("Stay in game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        builder.show();
    }

    public void statsPress(View v) {
        Render.setContentView(R.layout.stats_view);
    }

    public void singlePlayMessage(View v) {
        Render.setContentView(R.layout.battle_view);
        gameInSession = SetUpGame.setUpSinglePlayerGame();

        Render.updateRender(gameInSession, this, musicManager);
        Render.setBorderId(-1);
        Render.updateScreen();
    }

    public void multiPlayMessage(View v) {
        Render.setContentView(R.layout.battle_view);
        gameInSession = SetUpGame.setUpMultiplayerGame();

        Render.updateRender(gameInSession, this, musicManager);
        Render.setBorderId(-1);
        Render.updateScreen();
    }

    public void firstcardPress(View v) {
        if (!gameInSession.getRenderDelay()) {
            if (gameInSession.playCardEvent(0)) {
                Render.setBorderId(0);
                Render.updateScreen();
            }
        }
    }

    public void secondcardPress(View v) {
        if (!gameInSession.getRenderDelay()) {
           if( gameInSession.playCardEvent(1)) {
               Render.setBorderId(1);
               Render.updateScreen();
           }
        }
    }

    public void thirdcardPress(View v) {
        if (!gameInSession.getRenderDelay()) {
            if (gameInSession.playCardEvent(2)) {
                Render.setBorderId(2);
                Render.updateScreen();
            }
        }
    }

    public void fourthcardPress(View v) {
        if (!gameInSession.getRenderDelay()) {
           if (gameInSession.playCardEvent(3)) {
               Render.setBorderId(3);
               Render.updateScreen();
           }
        }
    }

    public void fifthcardPress(View v) {
        if (!gameInSession.getRenderDelay()) {
            if (gameInSession.playCardEvent(4)) {
                Render.setBorderId(4);
                Render.updateScreen();
            }
        }
    }
    public void discardPress(View v) {
        if (gameInSession.getDiscard()) {
            Render.setDiscard(true);
            Render.updateScreen();
        } else {
            Render.setDiscard(false);
            Render.updateScreen();
        }
    }
    public void pauseMessage(View v) {
        gameInSession.pauseGame();
        Render.setContentView(R.layout.pause_view);
    }

    public void pauseResumeMessage(View v) {
        Render.setContentView(R.layout.battle_view);
        gameInSession.unpauseGame();
        Render.setBorderId(-1);
        Render.updateScreen();
    }

    public void resumeFromContinueWindow(View v) {
        Render.setContentView(R.layout.battle_view);
        Render.setBorderId(-1);
        Render.updateScreen();
    }

    public void pauseExitMessage(View v) {
        exitGameDialog();
    }

    public void pauseStatsMessage(View v) {
        Render.setContentView(R.layout.stats_view);
    }


    public void statsExitMessage(View v) {
        if (gameInSession != null) {
            Render.setContentView(R.layout.pause_view);
        } else {
            Render.setContentView(R.layout.main_activity);
            checkStateSound();
        }
    }

    public void finishGame(View v)
    {
        Render.setContentView(R.layout.main_activity);
        checkStateSound();
    }


    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {
            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++)
                assetNames[i] = DB_PATH + "/" + assetNames[i];

            copyAssetsToDirectory(assetNames, dataDirectory);

            DBController.setDBPathName(dataDirectory.toString() + "/" + DBController.dbName);

        } catch (IOException ioe) {
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }
}