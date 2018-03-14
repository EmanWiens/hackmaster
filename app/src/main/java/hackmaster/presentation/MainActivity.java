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
    // give a "copy" of the interface to the gameManager
    private MusicManager musicManager;
    private Game gameInSession;
    private RenderView renderView;
    private PlayerStatsSaves playerStats;

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        copyDatabaseToDevice();
        DBController.startUp();
        musicManager = new MusicManager(this);
        setContentView(R.layout.main_activity);
        musicManager.backGroundMusicStart();
        musicManager.initSoundPool();
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
        View currLayout = findViewById(android.R.id.content);
        int currLayoutId = currLayout.getId();

        if (currLayoutId == R.id.main_activity) {
            return;
        } else if (gameInSession != null) {
            if (!gameInSession.gamePaused()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("You are about to exit the game.")
                        .setPositiveButton("Exit game", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                setContentView(R.layout.main_activity);
                                checkStateSound();
                            }
                        })
                        .setNegativeButton("Stay in game", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });

                builder.show();
            } else if (gameInSession.gamePaused()) {
                setContentView(R.layout.battle_view);
                gameInSession.unpauseGame();
                renderView.renderBattleView(-1);
            }
        } else {
            if (gameInSession != null) {
                setContentView(R.layout.main_activity);
                checkStateSound();
            }
        }
    }

    // TODO change this (marc)
    public void statsPress(View v) {
        setContentView(R.layout.stats_view); //change
        TextView text = (TextView) findViewById(R.id.nicknameTxtView);
        text = (TextView) findViewById(R.id.winLoseTxtView);
    }

    public void singlePlayMessage(View v) {
        setContentView(R.layout.battle_view);
        gameInSession = SetUpGame.setUpSinglePlayerGame();
        renderView = new RenderView(gameInSession,MainActivity.this,musicManager);
        renderView.setUpBattleView();
    }

    public void multiPlayMessage(View v) {
        setContentView(R.layout.battle_view);
        gameInSession = SetUpGame.setUpMultiplayerGame();
        renderView = new RenderView(gameInSession, MainActivity.this,musicManager);
        renderView.setUpBattleView();
    }

    public void firstcardPress(View v) {
        if (!gameInSession.getRenderDelay()) {
            if (gameInSession.playCardEvent(0)) {
                renderView.renderBattleView(0);
            }
        }
    }

    public void secondcardPress(View v) {
        if (!gameInSession.getRenderDelay()) {
           if( gameInSession.playCardEvent(1))
            renderView.renderBattleView(1);
        }
    }

    public void thirdcardPress(View v) {
        if (!gameInSession.getRenderDelay()) {
            if (gameInSession.playCardEvent(2))
            renderView.renderBattleView(2);
        }
    }

    public void fourthcardPress(View v) {
        if (!gameInSession.getRenderDelay()) {
           if (gameInSession.playCardEvent(3))
            renderView.renderBattleView(3);
        }
    }

    public void fifthcardPress(View v) {
        if (!gameInSession.getRenderDelay()) {
            if (gameInSession.playCardEvent(4))
            renderView.renderBattleView(4);
        }
    }
    public void discardPress(View v) {
        if (gameInSession.getDiscard() == true) {
            renderView.setDiscard(true);
        } else {
            renderView.setDiscard(false);
        }
    }
    public void pauseMessage(View v) {
        gameInSession.pauseGame();
        setContentView(R.layout.pause_view);
    }

    public void pauseResumeMessage(View v) {
        setContentView(R.layout.battle_view);
        gameInSession.unpauseGame();
        renderView.initSetUp();
        renderView.renderBattleView(-1);
    }
    public void resumeFromContinueWindow(View v) {
        setContentView(R.layout.battle_view);
        renderView.initSetUp();
        renderView.renderBattleView(-1);
    }

    public void pauseExitMessage(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You are about to exit the game.")
                .setPositiveButton("Exit game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        setContentView(R.layout.main_activity);
                        gameInSession = null;
                        checkStateSound();
                    }
                })
                .setNegativeButton("Stay in game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        builder.show();
    }

    //TODO change this (marc)
    public void pauseStatsMessage(View v) {
        setContentView(R.layout.stats_view);

        TextView text = (TextView) findViewById(R.id.nicknameTxtView);
        // text.setText(gameInSession.getPlayerName());

        text = (TextView) findViewById(R.id.winLoseTxtView);
        // text.setText(Integer.toString(gameInSession.getWin()));
    }



    public void statsExitMessage(View v) {
        if (gameInSession != null) {
            setContentView(R.layout.pause_view);
        } else {
            setContentView(R.layout.main_activity);
            checkStateSound();
        }
    }
    public void finishGame(View v)
    {
        setContentView(R.layout.main_activity);
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
            //TODO: Do exception handling Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
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