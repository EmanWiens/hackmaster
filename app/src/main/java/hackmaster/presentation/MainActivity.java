package hackmaster.presentation;



import android.app.AlertDialog;
import android.content.DialogInterface;

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
import hackmaster.business.GameManager;
import hackmaster.objects.CardClass;
import hackmaster.objects.PlayerClass;

public class MainActivity extends AppCompatActivity implements DrawToScreen {
    // give a "copy" of the interface to the gameManager
    private GameManager gameManager;
    private MusicManager musicManager;
    private boolean resumeMusic;

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameManager = new GameManager(this);
        musicManager = new MusicManager(this);
        setContentView(R.layout.main_activity);
        musicManager.backGroundMusicStart();
        musicManager.initSoundPool();
        gameManager.initStats();
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


    public void muteSoundBackground(View v){
        ImageButton muteBtn = findViewById(R.id.muteBtn);
        if (resumeMusic) {
            muteBtn.setBackgroundResource(R.drawable.volumemute);
            musicManager.pauseBacgroundMusic();
            resumeMusic=false;
        }
        else {
            muteBtn.setBackgroundResource(R.drawable.volumeunmute);
            musicManager.resumeBacgroundMusic();
            resumeMusic = true;
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
                GameManager.render();
            }
        }
        else {
            if (!gameManager.inGame()) {
                setContentView(R.layout.main_activity);
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
        ImageButton imageButton =  null;
        int newslot=slot;
        int[] imageButtonCardList = new int[]{
                R.id.imageButtonCard0, R.id.imageButtonCard1,R.id.imageButtonCard2,
                R.id.imageButtonCard3,R.id.imageButtonCard4};
        for (int i=0; i<slot;i++)
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

    public void playMessage(View v) {
        setContentView(R.layout.battle_view);
        gameManager.setUpSingleGame();
    }
    public void firstcardPress(View v)
    {
        gameManager.playCardEvent(0);
        cardPress(0);
        if (gameDone())
            getWinner();
    }
    public void secondcardPress(View v)
    {
        gameManager.playCardEvent(1);
        cardPress(1);
        if (gameDone())
            getWinner();
    }
    public void thirdcardPress(View v)
    {
        gameManager.playCardEvent(2);
        cardPress(2);
        if (gameDone())
            getWinner();
    }
    public void fourthcardPress(View v)
    {
        gameManager.playCardEvent(3);
        cardPress(3);
        if (gameDone())
            getWinner();
    }
    public void fifthcardPress(View v)
    {
        gameManager.playCardEvent(4);
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
        // if (gameManager.getPlayer1Turn()) {
//        TextView playedCard = findViewById(R.id.playedCard1);
//        playedCard.setText(card.toString());
        ImageView imageView = findViewById(R.id.imageViewPlayedCard1);
         imageView.setBackgroundResource(returnImageCardID(card.getID()));
        /*}
        else {
            TextView playedCard = findViewById(R.id.playedCard1);
            playedCard.setText(card.toString());
        }*/
    }

    public void pauseMessage(View v) {
        gameManager.pauseGame();
        setContentView(R.layout.pause_view);
    }

    public void pauseResumeMessage(View v) {
        setContentView(R.layout.battle_view);
        gameManager.unpauseGame();
        GameManager.render();
    }

    public void pauseExitMessage(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You are about to exit the game.")
                .setPositiveButton("Exit game", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        setContentView(R.layout.main_activity);
                        GameManager.setInGame(false);
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

        TextView text = (TextView)findViewById(R.id.resultsTextView);

        if (winner) {
            text.setText("Victory");
            gameManager.addWin();
        } else {
            text.setText("Defeat");
            gameManager.addLoss();
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
}