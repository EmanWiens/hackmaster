package hackmaster.presentation;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.content.res.AssetManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;

import com.example.owner.hackmaster20.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import hackmaster.application.DBController;
import hackmaster.business.DeckManager;
import hackmaster.business.Game;
import hackmaster.business.MultiplayerGame;
import hackmaster.business.SetUpGame;
import hackmaster.business.SinglePlayerGame;
import hackmaster.objects.CardClass;
import hackmaster.objects.PlayerClass;
import hackmaster.objects.PlayerStatsSaves;

public class MainActivity extends AppCompatActivity {
    // give a "copy" of the interface to the gameManager
    private MusicManager musicManager;
    private Game gameInSession;
    private PlayerStatsSaves playerStats; // TODO move the player stats out of game manager into main for now

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

    public void renderPlayedCard(CardClass card, boolean aiDelay) {
        Handler handler = new Handler();
        if (aiDelay && !gameDone()) {
            handler.postDelayed(delayRender(), 1850); // DELAY
            gameInSession.setRenderDelayToggle(true);
        }
        else if (gameInSession != null && !gameInSession.gamePaused()) {
            ImageView imageView = findViewById(R.id.imageViewPlayedCard1);
            imageView.setBackgroundResource(returnImageCardID(card.getID()));

            if (gameInSession.getRenderDelayToggle())
                gameInSession.setRenderDelayToggle(false);
        }
    }

    // DELAY
    public Runnable delayRender() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                renderPlayedCard(gameInSession.getPlayedCardTwo(), false);
            }
        };
        return r;
    }

    public void checkStateSound()
    {
        ImageButton muteBtn = findViewById(R.id.muteBtn);
        if (musicManager.getStateMusic())
            muteBtn.setBackgroundResource(R.drawable.volumeunmute);
        else
            muteBtn.setBackgroundResource(R.drawable.volumemute);
    }

    public void muteSoundBackground(View v){
        ImageButton muteBtn = findViewById(R.id.muteBtn);
        if (musicManager.getStateMusic()) {
            muteBtn.setBackgroundResource(R.drawable.volumemute);
            musicManager.pauseBacgroundMusic();
        }
        else {
            muteBtn.setBackgroundResource(R.drawable.volumeunmute);
            musicManager.resumeBacgroundMusic();
        }
    }

    @Override
    public void onBackPressed() {
        View currLayout = findViewById(android.R.id.content);
        int currLayoutId = currLayout.getId();

        if (currLayoutId == R.id.main_activity)
            return;
        else if (gameInSession != null) {
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
            }
            else if (gameInSession.gamePaused()) {
                setContentView(R.layout.battle_view);
                gameInSession.unpauseGame();
                renderBattleView();
            }
        }
        else {
            if (gameInSession != null) {
                setContentView(R.layout.main_activity);
                checkStateSound();
            }
        }
    }

    public void renderPlayerResource(PlayerClass player) {
        if (player.getId() == 0) {
            fillText((TextView)findViewById(R.id.minerP), player.minerToString());
            fillText((TextView)findViewById(R.id.cSpeedP), player.cSpeedToString());
            fillText((TextView)findViewById(R.id.botnetP), player.botnetToString());

            fillText((TextView)findViewById(R.id.healthP), player.playerHealthToString());
            ProgressBar health = findViewById(R.id.healthPBarP);
            health.setProgress(player.getHealth());
            fillText((TextView)findViewById(R.id.player1), player.getName());
        }
        else if (player.getId() == 1) {
            fillText((TextView)findViewById(R.id.minerE), player.minerToString());
            fillText((TextView)findViewById(R.id.cSpeedE), player.cSpeedToString());
            fillText((TextView)findViewById(R.id.botnetE), player.botnetToString());

            fillText((TextView)findViewById(R.id.healthE), player.playerHealthToString());
            ProgressBar health = findViewById(R.id.healthPBarE);
            health.setProgress(player.getHealth());
            fillText((TextView)findViewById(R.id.player2), player.getName());
        }
    }

    private void fillText (TextView view, String string) {
        view.setText(string);
    }

    // TODO change this (marc)
    public void statsPress(View v) {
        setContentView(R.layout.stats_view); //change
        TextView text=(TextView)findViewById(R.id.nicknameTxtView);

        text=(TextView)findViewById(R.id.winLoseTxtView);
    }

    public void renderCard(CardClass card, int slot) {
        ImageButton imageButton = null;
        int[] imageButtonCardList = new int[]{
                R.id.imageButtonCard0, R.id.imageButtonCard1,R.id.imageButtonCard2,
                R.id.imageButtonCard3,R.id.imageButtonCard4};

        imageButton = findViewById(imageButtonCardList[slot]);
        imageButton.setBackgroundResource(returnImageCardID(card.getID()));
    }

    public void renderBattleView() {
        CardClass playedCardTwo = gameInSession.getPlayedCardTwo();
        CardClass playedCardOne = gameInSession.getPlayedCardOne();
        PlayerClass player1 = gameInSession.getPlayer1();
        PlayerClass player2 = gameInSession.getPlayer2();

        if (!gameInSession.gamePaused()) {
            renderPlayerResource(player1);
            renderPlayerResource(player2);

            if (gameInSession instanceof SinglePlayerGame ) {
                if (playedCardOne != null && !gameInSession.getRenderDelayToggle())
                    renderPlayedCard(playedCardOne, false);

                if (playedCardTwo != null && gameInSession instanceof SinglePlayerGame)
                    renderPlayedCard(playedCardTwo, true);
            }
            else if (gameInSession instanceof MultiplayerGame) {
                if(!gameInSession.getPlayer1Turn() && playedCardOne != null)
                    renderPlayedCard(playedCardOne, false);
                else if (gameInSession.getPlayer1Turn() && playedCardTwo != null)
                    renderPlayedCard(playedCardTwo, false);
            }

            if (gameInSession.getPlayer1Turn()) {
                for (int i = 0; i < player1.getCards().length; i++) {
                    if (player1.getCards()[i] != null)
                        renderCard(player1.getCards()[i], i);
                }
            }
            else {
                for (int i = 0; i < player2.getCards().length; i++) {
                    if (player2.getCards()[i] != null)
                        renderCard(player2.getCards()[i], i);
                }
            }
        }
    }

    public void singlePlayMessage(View v) {
        setContentView(R.layout.battle_view);
        gameInSession = SetUpGame.setUpSinglePlayerGame();
        renderBattleView();
    }

    public void multiPlayMessage(View v) {
        setContentView(R.layout.battle_view);

        gameInSession = SetUpGame.setUpMultiplayerGame();
        renderBattleView();
    }

    public void firstcardPress(View v)
    {
        if (!gameInSession.getRenderDelayToggle()) {
            gameInSession.playCardEvent(0);
            renderBattleView();
            renderPressedCardBorder(0);
            setDiscard(true);
            if (gameDone())
                getWinner();
        }
    }

    public void secondcardPress(View v)
    {
        if (!gameInSession.getRenderDelayToggle()) {
            gameInSession.playCardEvent(1);
            renderBattleView();
            renderPressedCardBorder(1);
            setDiscard(true);
            if (gameDone())
                getWinner();
        }
    }

    public void thirdcardPress(View v)
    {
        if (!gameInSession.getRenderDelayToggle()) {
            gameInSession.playCardEvent(2);
            renderBattleView();
            renderPressedCardBorder(2);
            setDiscard(true);
            if (gameDone())
                getWinner();
        }
    }

    public void fourthcardPress(View v)
    {
        if (!gameInSession.getRenderDelayToggle()) {
            gameInSession.playCardEvent(3);
            renderBattleView();
            renderPressedCardBorder(3);
            setDiscard(true);
            if (gameDone())
                getWinner();
        }
    }

    public void fifthcardPress(View v)
    {
        if (!gameInSession.getRenderDelayToggle()) {
            gameInSession.playCardEvent(4);
            renderBattleView();
            renderPressedCardBorder(4);
            setDiscard(true);
            if (gameDone())
                getWinner();
        }
    }

    private void renderPressedCardBorder(int chosenCard) {
        ImageView[] imageCardBorder = new ImageView[6];
        imageCardBorder[0] = findViewById(R.id.imageBorderCard0);
        imageCardBorder[1] = findViewById(R.id.imageBorderCard1);
        imageCardBorder[2] = findViewById(R.id.imageBorderCard2);
        imageCardBorder[3] = findViewById(R.id.imageBorderCard3);
        imageCardBorder[4] = findViewById(R.id.imageBorderCard4);
        musicManager.playCardSelected(0.8f, 0.8f);

        for (int i = 0; i <= 4; i++) {
            if (i == chosenCard)
                imageCardBorder[i].setBackgroundResource(R.drawable.image_border);
            else
                imageCardBorder[i].setBackgroundResource(android.R.color.transparent);
        }
    }

    public void pauseMessage(View v) {
        gameInSession.pauseGame();
        setContentView(R.layout.pause_view);
    }

    public void pauseResumeMessage(View v) {
        setContentView(R.layout.battle_view);
        gameInSession.unpauseGame();
        renderBattleView();
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
        // gameInSession.initStats();

        setContentView(R.layout.stats_view);

        TextView text=(TextView)findViewById(R.id.nicknameTxtView);
        // text.setText(gameInSession.getPlayerName());

        text=(TextView)findViewById(R.id.winLoseTxtView);
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

    public int returnImageCardID(int cardID)
    {
        int[] imageCardList = new int[]{
                R.drawable.morecores,R.drawable.morecores, R.drawable.botnet,
                R.drawable.cutsomewires, R.drawable.upgradebotnet,R.drawable.upgradecpu,
                R.drawable.upgradehashrate,R.drawable.ddos,R.drawable.filetransfer,
                R.drawable.popup,R.drawable.antivirus,R.drawable.firewall,
                R.drawable.playthemarket,R.drawable.overclock,R.drawable.serverfarm,
                R.drawable.expand,R.drawable.marketcrash, R.drawable.networkoutage,
                R.drawable.throttle, R.drawable.hack,R.drawable.debug, R.drawable.exploit,
                R.drawable.zeroday, R.drawable.attackplus, R.drawable.attackplusplus,
                R.drawable.attackphash, R.drawable.extremehack,R.drawable.epichack,
                R.drawable.masshack
        };
        return imageCardList[cardID];
    }

    public void getWinner() {
        if (gameInSession.getPlayer2Health() < 1) {
            goToVictory(true);
        } else {//  (GameManager.getPlayer2Health() < 1) {
            goToVictory(false);
        }
    }

    public void goToVictory(boolean winner) {
        setContentView(R.layout.results_view);
        // GameManager.setInGame(false);
        gameInSession = null;

        ImageView img= (ImageView) findViewById(R.id.statsImg);
        if (winner) {
            // gameInSession.addWin();
            img.setImageResource(R.drawable.victory);
        } else {
            // gameInSession.addLoss();
            img.setImageResource(R.drawable.defeat);
        }
    }

    // TODO should be in Game.java
    public boolean gameDone() {
        boolean result = false;
        if (gameInSession.getPlayer2Health() < 1)
            result = true;
        if (gameInSession.getPlayer1Health() < 1)
            result = true;
        return result;
    }

    public void discardPress(View v) {
        if (gameInSession.getDiscard() == true) {
            setDiscard(true);
        } else {
            setDiscard(false);
        }
    }

    public void setDiscard (boolean toggle) {
        if (toggle) {
            gameInSession.discardOff();
            Button dicardButton = (Button)findViewById(R.id.discardBtn);
            dicardButton.setText("DISCARD MODE");
        } else {
            gameInSession.discardOn();
            Button dicardButton = (Button)findViewById(R.id.discardBtn);
            dicardButton.setText("CANCEL DISCARD");
        }
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