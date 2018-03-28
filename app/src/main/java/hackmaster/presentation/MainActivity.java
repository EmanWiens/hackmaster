package hackmaster.presentation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.owner.hackmaster20.R;

import hackmaster.application.DBController;
import hackmaster.business.Game;
import hackmaster.application.AppController;
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
        AppController.copyDatabaseToDevice(this);
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
            musicManager.pauseBackgroundMusic();
        } else {
            muteBtn.setBackgroundResource(R.drawable.volumeunmute);
            musicManager.resumeBackgroundMusic();
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

    private void processCardPress(int playerCard) {
        if (!gameInSession.getRenderDelay()) {
            if (gameInSession.getDiscard()) {
                musicManager.playCardDestroyed(0.8f, 0.8f);
            }

            boolean tempPlayer1Turn = gameInSession.getPlayer1Turn();
            gameInSession.playCardEvent(playerCard);

            if (tempPlayer1Turn != gameInSession.getPlayer1Turn()) {
                Render.setTurnSwitch();
            }

            if (Game.checkCard(playerCard, gameInSession.getCurrentPlayer())) {
                Render.resetDelayState();
                Render.setBorderId(playerCard);
            }

            Render.updateScreen();
        }
    }

    public void firstcardPress(View v) {
        processCardPress(0);
    }

    public void secondcardPress(View v) {
        processCardPress(1);
    }

    public void thirdcardPress(View v) {
        processCardPress(2);
    }

    public void fourthcardPress(View v) {
        processCardPress(3);
    }

    public void fifthcardPress(View v) {
        processCardPress(4);
    }
    public void discardPress(View v) {
        if (gameInSession.getDiscard()) {
            gameInSession.discardOff();
        } else {
            // musicManager.playCardDestroyed(0.8f, 0.8f);
            gameInSession.discardOn();
        }
        Render.updateScreen();
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