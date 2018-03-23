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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.owner.hackmaster20.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import hackmaster.application.DBController;
import hackmaster.business.Game;
import hackmaster.business.PlayerManager;
import hackmaster.business.SetUpGame;
import hackmaster.objects.PlayerStatsSaves;

public class MainActivity extends AppCompatActivity {

    private MusicManager musicManager;
    private Game gameInSession;
    private RenderView renderView;
    private Render render;
    private PlayerStatsSaves playerStats;
    private String playerName;

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        copyDatabaseToDevice();
        DBController.startUp();
        musicManager = new MusicManager(this);
        musicManager.backGroundMusicStart();
        musicManager.initSoundPool();

        render = Render.setUpRender(gameInSession, this, musicManager);
        render.setContentView(R.layout.signin_view);
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
        // TODO move this into render (since this does a lot of render related ops
        View currLayout = findViewById(android.R.id.content);
        int currLayoutId = currLayout.getId();

        if (currLayoutId == R.id.main_activity) {
            return;
        } else if (gameInSession != null) {
            if (!gameInSession.gamePaused()) {
                exitGameDialog();
            } else if (gameInSession.gamePaused()) {
                render.setContentView(R.layout.battle_view);
                gameInSession.unpauseGame();
                renderView.renderBattleView(-1);
            }
        } else {
            if (gameInSession != null) {
                render.setContentView(R.layout.main_activity);
                checkStateSound();
            }
        }
    }

    private void exitGameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You are about to exit the game.")
                .setPositiveButton("Exit game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        render.setContentView(R.layout.main_activity);
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

    public void statsPress(View v) {
        render.setContentView(R.layout.stats_view);
    }

    public void singlePlayMessage(View v) {
        render.setContentView(R.layout.battle_view);
        gameInSession = SetUpGame.setUpSinglePlayerGame(playerName);

        Render.updateRender(gameInSession, this, musicManager);

        renderView = new RenderView(gameInSession,MainActivity.this,musicManager);
        renderView.setUpBattleView();
    }

    public void multiPlayMessage(View v) {
        render.setContentView(R.layout.battle_view);
        gameInSession = SetUpGame.setUpMultiplayerGame();

        Render.updateRender(gameInSession, this, musicManager);

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
        render.setContentView(R.layout.pause_view);
    }

    public void pauseResumeMessage(View v) {
        render.setContentView(R.layout.battle_view);
        gameInSession.unpauseGame();
        renderView.renderBattleView(-1);
    }

    public void resumeFromContinueWindow(View v) {
        render.setContentView(R.layout.battle_view);
        renderView.renderBattleView(-1);
    }

    public void pauseExitMessage(View v) {
        exitGameDialog();
    }

    public void pauseStatsMessage(View v) {
        render.setContentView(R.layout.stats_view);
    }

    public void signInExitMessage(View v) {
        EditText input = (EditText) findViewById(R.id.playerOneName);
        String inputName = input.getText().toString();
        if(inputName != null && !inputName.isEmpty()){
            playerName = inputName;
            PlayerManager.initPlayer(inputName);
            render.setContentView(R.layout.main_activity);
        }
    }

    public void statsExitMessage(View v) {
        if (gameInSession != null) {
            render.setContentView(R.layout.pause_view);
        } else {
            render.setContentView(R.layout.main_activity);
            checkStateSound();
        }
    }

    public void finishGame(View v) {
        render.setContentView(R.layout.main_activity);
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