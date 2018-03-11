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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.owner.hackmaster20.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import hackmaster.application.DBController;
import hackmaster.business.GameManager;
import hackmaster.objects.CardClass;
import hackmaster.objects.PlayerClass;

public class MainActivity extends AppCompatActivity implements DrawToScreen {
    // give a "copy" of the interface to the gameManager
    private GameManager gameManager;
    private MusicManager musicManager;

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        copyDatabaseToDevice();
        DBController.startUp();
        gameManager = new GameManager();
        musicManager = new MusicManager(this);
        setContentView(R.layout.main_activity);
        musicManager.backGroundMusicStart();
        musicManager.initSoundPool();
        gameManager.initStats();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        DBController.shutDown();
    }

    synchronized public void drawPlayedCard(CardClass card, boolean delay) {
        // DELAY
        // Handler handler = new Handler();
        if (delay) {
            //handler.postDelayed(delayDraw(), 2000); // DELAY
            ImageView imageView = findViewById(R.id.imageViewPlayedCard1);
            imageView.setBackgroundResource(returnImageCardID(card.getID()));
        }
        else {
            ImageView imageView = findViewById(R.id.imageViewPlayedCard0);
            imageView.setBackgroundResource(returnImageCardID(card.getID()));
        }
    }

    // DELAY
    public Runnable delayDraw() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                drawPlayedCard(GameManager.getPlayedCardAi(), false);
            }
        };
        return r;
    }

    public void checkStateSound()
    {
        ImageButton muteBtn = findViewById(R.id.muteBtn);
        if (musicManager.getStateMusic()) {
            muteBtn.setBackgroundResource(R.drawable.volumeunmute);
        }
        else {
            muteBtn.setBackgroundResource(R.drawable.volumemute);
        }
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
        else if (gameManager.inGame()) {
            if (!gameManager.gamePaused()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("You are about to exit the game.")
                        .setPositiveButton("Exit game", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                setContentView(R.layout.main_activity);
                                GameManager.setInGame(false);
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
            else if (gameManager.gamePaused()) {
                setContentView(R.layout.battle_view);
                GameManager.unpauseGame();
                renderBattleView();
                //GameManager.render();
            }
        }
        else {
            if (!gameManager.inGame()) {
                setContentView(R.layout.main_activity);
                checkStateSound();
            }
        }
    }

    public void drawPlayerResource(PlayerClass player) {
        if (player.getId() == 0) {
            fillText((TextView)findViewById(R.id.minerP), player.minerToString());
            fillText((TextView)findViewById(R.id.cSpeedP), player.cSpeedToString());
            fillText((TextView)findViewById(R.id.botnetP), player.botnetToString());

            fillText((TextView)findViewById(R.id.healthP), "Health: " + player.getHealth() + "%");
            ProgressBar health = findViewById(R.id.healthPBarP);
            health.setProgress(player.getHealth());
        }
        else if (player.getId() == 1){
            fillText((TextView)findViewById(R.id.minerE), player.minerToString());
            fillText((TextView)findViewById(R.id.cSpeedE), player.cSpeedToString());
            fillText((TextView)findViewById(R.id.botnetE), player.botnetToString());

            fillText((TextView)findViewById(R.id.healthE), "Health: " + player.getHealth() + "%");
            ProgressBar health = findViewById(R.id.healthPBarE);
            health.setProgress(player.getHealth());
        }
    }

    private void fillText (TextView view, String string) {
        view.setText(string);
    }

    //change this (marc)
    public void statsPress(View v) {
        gameManager.initStats();

        setContentView(R.layout.stats_view); //change
        TextView text=(TextView)findViewById(R.id.nicknameTxtView);
        text.setText(gameManager.getPlayerName());

        text=(TextView)findViewById(R.id.winLoseTxtView);
        text.setText(Integer.toString(gameManager.getWin()));
    }

    public void DrawCard(CardClass card, int slot) {
        ImageButton imageButton = null;
        int[] imageButtonCardList = new int[]{
                R.id.imageButtonCard0, R.id.imageButtonCard1,R.id.imageButtonCard2,
                R.id.imageButtonCard3,R.id.imageButtonCard4};
        for (int i=0; i<imageButtonCardList.length;i++)
        {
            if (slot==i)
            {
                imageButton = findViewById(imageButtonCardList[i]);
            }
        }
        imageButton.setBackgroundResource(returnImageCardID(card.getID()));
    }
    public void displayCardImage(int imageID, int imageBtnID)
    {
        int realID=0;
        if (imageID==25)
        {
            realID=R.drawable.debug;
        }
        //ImageButton imageButton = findViewById(R.id.imageButton);
       // imageButton.setBackgroundResource(realID);

    }
    public  void renderBattleView() {
        CardClass playedCardAi = gameManager.getPlayedCardAi();
        CardClass playedCard = gameManager.getPlayedCard();
        PlayerClass player1 = gameManager.getPlayer1();
        PlayerClass player2 = gameManager.getPlayer2();
        boolean player1Turn = gameManager.getPlayer1Turn();
        boolean paused = gameManager.getPausedStatus();
        if (!paused) {
            drawPlayerResource(player1);
            drawPlayerResource(player2);

            if (playedCard != null)
               drawPlayedCard(playedCard, false);
            if (playedCardAi != null)
                drawPlayedCard(playedCardAi, true);

            if (player1Turn)
                for (int i = 0; i < player1.getCards().length; i++) {
                    if (player1.getCards()[i] != null)
                        DrawCard(player1.getCards()[i], i);
                }
            else
                for (int i = 0; i < player2.getCards().length; i++) {
                    if (player2.getCards()[i] != null)
                        DrawCard(player2.getCards()[i], i);
                }
        }
    }
    public void playMessage(View v) {
        setContentView(R.layout.battle_view);
        gameManager.setUpSingleGame();
        renderBattleView();
    }
    public void firstcardPress(View v)
    {
        gameManager.playCardEvent(0);
        renderBattleView();
        cardPress(0);
        if (gameDone())
            getWinner();
    }
    public void secondcardPress(View v)
    {
        gameManager.playCardEvent(1);
        renderBattleView();
        cardPress(1);
        if (gameDone())
            getWinner();
    }
    public void thirdcardPress(View v)
    {
        gameManager.playCardEvent(2);
        renderBattleView();
        cardPress(2);
        if (gameDone())
            getWinner();
    }
    public void fourthcardPress(View v)
    {
        gameManager.playCardEvent(3);
        renderBattleView();
        cardPress(3);
        if (gameDone())
            getWinner();
    }
    public void fifthcardPress(View v)
    {
        gameManager.playCardEvent(4);
        renderBattleView();
        cardPress(4);
        if (gameDone())
            getWinner();
    }
    private void cardPress(int chosenCard) {
        ImageView[]  imageCardBorder = new ImageView[6] ;
        imageCardBorder[0]= findViewById(R.id.imageBorderCard0);
        imageCardBorder[1]= findViewById(R.id.imageBorderCard1);
        imageCardBorder[2]= findViewById(R.id.imageBorderCard2);
        imageCardBorder[3] = findViewById(R.id.imageBorderCard3);
        imageCardBorder[4] = findViewById(R.id.imageBorderCard4);
        musicManager.playCardSelected(0.8f,0.8f);
        for (int i=0; i<=4;i++)
        {
            if (i==chosenCard) {

                imageCardBorder[i].setBackgroundResource(R.drawable.image_border);
            }
            else{
                imageCardBorder[i].setBackgroundResource(android.R.color.transparent);
            }
        }
    }

    synchronized public void drawPlayedCard(CardClass card) {
        ImageView imageView = findViewById(R.id.imageViewPlayedCard1);
         imageView.setBackgroundResource(returnImageCardID(card.getID()));
    }

    public void pauseMessage(View v) {
        gameManager.pauseGame();
        setContentView(R.layout.pause_view);
    }

    public void pauseResumeMessage(View v) {
        setContentView(R.layout.battle_view);
        gameManager.unpauseGame();
        renderBattleView();
    }

    public void pauseExitMessage(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You are about to exit the game.")
                .setPositiveButton("Exit game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        setContentView(R.layout.main_activity);
                        GameManager.setInGame(false);
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

    // change this (marc)
    public void pauseStatsMessage(View v) {
        gameManager.initStats();

        setContentView(R.layout.stats_view);

        TextView text=(TextView)findViewById(R.id.nicknameTxtView);
        text.setText(gameManager.getPlayerName());

        text=(TextView)findViewById(R.id.winLoseTxtView);
        text.setText(Integer.toString(gameManager.getWin()));
    }

    public void statsExitMessage(View v) {
        if (gameManager.inGame()) {
            setContentView(R.layout.pause_view);
        } else {
            setContentView(R.layout.main_activity);
            checkStateSound();
        }
    }

    public int returnImageCardID(int cardID)
    {
        int ImageCardID=0;
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
        for (int i=0; i<imageCardList.length;i++)
        {
            if (cardID==i)
            {
                ImageCardID=imageCardList[i];
            }
        }
        return ImageCardID;
    }
    
    public  void getWinner() {
        if (GameManager.getPlayer2Health() < 1) {
            goToVictory(true);
        } else {//  (GameManager.getPlayer2Health() < 1) {
            goToVictory(false);
        }
    }


    public void goToVictory(boolean winner) {
        setContentView(R.layout.results_view);
        GameManager.setInGame(false);

        ImageView img= (ImageView) findViewById(R.id.statsImg);


        if (winner) {
            gameManager.addWin();
            img.setImageResource(R.drawable.victory);
        } else {
            gameManager.addLoss();
            img.setImageResource(R.drawable.defeat);
        }
    }

    public static boolean gameDone() {
        boolean result = false;
        if (GameManager.getPlayer2Health() < 1) {
            result = true;
        }
        if (GameManager.getPlayer1Health() < 1) {
            result = true;
        }
        return result;
    }
    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

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