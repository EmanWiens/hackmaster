package hackmaster.presentation;



import android.app.AlertDialog;
import android.content.DialogInterface;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.owner.hackmaster20.R;

import java.util.Random;

import hackmaster.business.GameInterface;
import hackmaster.business.GameManager;
import hackmaster.objects.CardClass;
import hackmaster.objects.PlayerClass;

public class MainActivity extends AppCompatActivity implements DrawToScreen {
    // give a "copy" of the interface to the gameManager
    private GameManager gameManager;
    private static final int MAX_STREAMS=100;
    private boolean soundPoolLoaded;
    private boolean resumeMusic;
    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;
    private int soundIdCardSelected;

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameManager = new GameManager(this);
        setContentView(R.layout.main_activity);
        backGroundMusicStart();
        this.initSoundPool();
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

    //Credit: https://o7planning.org/en/10521/android-2d-game-tutorial-for-beginners
    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    private void initSoundPool()  {
        // With Android API >= 21.
        if (Build.VERSION.SDK_INT >= 21 ) {

            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder= new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            this.soundPool = builder.build();
        }
        // With Android API < 21
        else {
            // SoundPool(int maxStreams, int streamType, int srcQuality)
            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }

        // When SoundPool load complete.
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPoolLoaded = true;
                // Playing background sound.
                //     playSoundBackground();
            }
        });

        // Load the sound SelectedCard.mp3 into SoundPool
        this.soundIdCardSelected = this.soundPool.load(this, R.raw.select,1);
    }

    public void playCardSelected()  {
        if(this.soundPoolLoaded) {
            float leftVolumn = 0.8f;
            float rightVolumn =  0.8f;
            // Play sound CardSelected.wav
            int streamId = this.soundPool.play(this.soundIdCardSelected,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }
    public void backGroundMusicStart() {
        resumeMusic=true;
        Random rand = new Random();
        int  n = rand.nextInt(6) + 1;
        if (n==1) {
            //https://www.youtube.com/watch?v=b-Cr0EWwaTk
            mediaPlayer = MediaPlayer.create(this, R.raw.javarapsong);
        }
        else if (n==2)
        {
            mediaPlayer = MediaPlayer.create(this, R.raw.background);
        }
        else if (n==3)
        {
            //credit: https://www.youtube.com/watch?v=FoUWHfh733Y&index=21&list=RDiN1uaITfA1c
            mediaPlayer = MediaPlayer.create(this, R.raw.dualcore);
        }
        else if (n==4)
        {
            //credit: https://www.youtube.com/watch?v=iN1uaITfA1c&index=1&list=RDiN1uaITfA1c
            mediaPlayer = MediaPlayer.create(this, R.raw.hackersong);
        }
        else if (n==5)
        {
            //credit: https://www.youtube.com/watch?v=rLsJCCNXUto&list=RDiN1uaITfA1c&index=3
            mediaPlayer = MediaPlayer.create(this, R.raw.welcometoourworld);
        }
        else if (n==6)
        {
            //credit: https://www.youtube.com/watch?v=Gc74aRe7OLM
            mediaPlayer = MediaPlayer.create(this, R.raw.piratemusic);
        }
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
        mediaPlayer.setLooping(true);
    }
    public void muteSoundBackground(View v){
        ImageButton muteBtn = findViewById(R.id.muteBtn);
        if (resumeMusic) {
            muteBtn.setBackgroundResource(R.drawable.volumemute);
            mediaPlayer.pause();
            resumeMusic=false;
        }
        else {
            muteBtn.setBackgroundResource(R.drawable.volumeunmute);
            mediaPlayer.start();
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
       if (slot == 0) {
           imageButton = findViewById(R.id.imageButtonCard0);
      }
        if (slot == 1) {
            imageButton = findViewById(R.id.imageButtonCard1);
        }
        if (slot == 2) {
            imageButton = findViewById(R.id.imageButtonCard2);
        }
         if (slot == 3) {
            imageButton = findViewById(R.id.imageButtonCard3);
        }
         if (slot == 4) {
            imageButton = findViewById(R.id.imageButtonCard4);
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
        playCardSelected();
        //String name[] = ((TextView) v).getText().toString().split("\n");
        //int chosenCard= Character.getNumericValue(name[0].charAt(0)) - 1;
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
    //TODO Move the returnImageCardID function to Presistence level (OR maybe to domain object)
    public int returnImageCardID(int cardID)
    {
        int ImageCardID= R.drawable.debug;
        if (cardID==28)
        {
            ImageCardID= R.drawable.masshack;
        }
        if (cardID==27)
        {
            ImageCardID= R.drawable.epichack;
        }
        if (cardID==26)
        {
            ImageCardID= R.drawable.extremehack;
        }
        if (cardID==25)
        {
            ImageCardID= R.drawable.attackphash;
        }
        if (cardID==24)
        {
            ImageCardID= R.drawable.attackplusplus;
        }
        if (cardID==23)
        {
            ImageCardID= R.drawable.attackplus;
        }
        if (cardID==22)
        {
            ImageCardID= R.drawable.zeroday;
        }
        if (cardID==21)
        {
            ImageCardID= R.drawable.exploit;
        }
        if (cardID==20)
        {
            ImageCardID= R.drawable.debug;
        }
        if (cardID==19)
        {
            ImageCardID= R.drawable.hack;
        }
        if (cardID==18)
        {
            ImageCardID= R.drawable.throttle;
        }
        if (cardID==17)
        {
            ImageCardID= R.drawable.networkoutage;
        }
        if (cardID==16)
        {
            ImageCardID= R.drawable.marketcrash;
        }
        if (cardID==15)
        {
            ImageCardID= R.drawable.expand;
        }
        if (cardID==14)
        {
            ImageCardID= R.drawable.serverfarm;
        }
        if (cardID==13)
        {
            ImageCardID= R.drawable.overclock;
        }
        if (cardID==12)
        {
            ImageCardID= R.drawable.playthemarket;
        }
        if (cardID==11)
        {
            ImageCardID= R.drawable.firewall;
        }
        if (cardID==10)
        {
            ImageCardID= R.drawable.antivirus;
        }
        if (cardID==9)
        {
            ImageCardID= R.drawable.popup;
        }
        if (cardID==8)
        {
            ImageCardID= R.drawable.filetransfer;
        }
        if (cardID==7)
        {
            ImageCardID= R.drawable.ddos;
        }
        if (cardID==6)
        {
            ImageCardID= R.drawable.upgradehashrate;
        }
        if (cardID==5)
        {
            ImageCardID= R.drawable.upgradecpu;
        }
        if (cardID==4)
        {
            ImageCardID= R.drawable.upgradebotnet;
        }
        if (cardID==3)
        {
            ImageCardID= R.drawable.cutsomewires;
        }
        if (cardID==2)
        {
            ImageCardID= R.drawable.botnet;
        }
        if (cardID==1)
        {
            ImageCardID= R.drawable.morecores;
        }
        if (cardID==0)
        {
            ImageCardID= R.drawable.morecores;
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