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

import com.example.owner.hackmaster20.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import hackmaster.application.DBController;
import hackmaster.business.Game;
import hackmaster.business.SetupDB;
import hackmaster.business.SetupGame;
import hackmaster.objects.PlayerStatsSaves;

public class MainActivity extends AppCompatActivity {

    private MusicManager musicManager;
    private Game gameInSession;
    private PlayerStatsSaves playerStats;

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SetupDB.copyDatabaseToDevice(this);
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
        gameInSession = SetupGame.setUpSinglePlayerGame();

        Render.updateRender(gameInSession, this, musicManager);
        Render.setBorderId(-1);
        Render.updateScreen();
    }

    public void multiPlayMessage(View v) {
        Render.setContentView(R.layout.battle_view);
        gameInSession = SetupGame.setUpMultiplayerGame();

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
            musicManager.playCardDestroyed(0.8f, 0.8f);
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
}