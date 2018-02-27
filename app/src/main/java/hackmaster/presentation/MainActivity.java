package hackmaster.presentation;



import android.app.AlertDialog;
import android.content.DialogInterface;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameManager = new GameManager(this);
        setContentView(R.layout.main_activity);
        backGroundMusicStart();
        this.initSoundPool();
    }

    //Credit: https://o7planning.org/en/10521/android-2d-game-tutorial-for-beginners
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
                GameManager.drawCurrentGame();
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

    public void statsPress(View v) {
        setContentView(R.layout.stats_view);
    }

    public void DrawCard(CardClass card, int slot) {
        TextView textView = null;

        String cardText = (slot+1) + ". " +card.toString();
        if (slot == 0) {
            textView = findViewById(R.id.card0);

        }
        else if (slot == 1)
            textView = findViewById(R.id.card1);
        else if (slot == 2)
            textView = findViewById(R.id.card2);
        else if (slot == 3)
            textView = findViewById(R.id.card3);
        else if (slot == 4)
            textView = findViewById(R.id.card4);
        else
            textView = findViewById(R.id.card5);

        textView.setText(cardText);
    }

    public void playMessage(View v) {
        setContentView(R.layout.battle_view);
        gameManager.setUpSingleGame(false);
    }

    public void cardPress(View v) {
        ImageView[]  imageCardBorder = new ImageView[6] ;
        imageCardBorder[0]= findViewById(R.id.imageBorderCard0);
        imageCardBorder[1]= findViewById(R.id.imageBorderCard1);
        imageCardBorder[2]= findViewById(R.id.imageBorderCard2);
        imageCardBorder[3] = findViewById(R.id.imageBorderCard3);
        imageCardBorder[4] = findViewById(R.id.imageBorderCard4);
        imageCardBorder[5] = findViewById(R.id.imageBorderCard5);
        String name[] = ((TextView) v).getText().toString().split("\n");
        playCardSelected();
        int chosenCard= Character.getNumericValue(name[0].charAt(0)) - 1;
            for (int i=0; i<=5;i++)
            {
                if (i==chosenCard) {
                    imageCardBorder[i].setBackgroundResource(R.drawable.image_border);
                }
                else{
                    imageCardBorder[i].setBackgroundResource(android.R.color.transparent);
                }
            }
        if (gameManager.getPlayer1Turn()) {
            gameManager.playCardEvent(chosenCard);
        }
    }

    synchronized public void drawPlayedCard(CardClass card) {
        // if (gameManager.getPlayer1Turn()) {
            TextView playedCard = findViewById(R.id.playedCard1);
            playedCard.setText(card.toString());
        /*}
        else {
            TextView playedCard = findViewById(R.id.playedCard1);
            playedCard.setText(card.toString());
        }*/
    }

    public void DiscardMessage(View v) {
        // TODO write the discard mode function in game manager an call it here.
    }

    public void pauseMessage(View v) {
        gameManager.pauseGame();
        setContentView(R.layout.pause_view);
    }

    public void pauseResumeMessage(View v) {
        setContentView(R.layout.battle_view);
        gameManager.unpauseGame();
        GameManager.drawCurrentGame();
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

    public void pauseStatsMessage(View v) {
        setContentView(R.layout.stats_view);
    }

    public void statsExitMessage(View v) {
        if (gameManager.inGame()) {
            setContentView(R.layout.pause_view);
        } else {
            setContentView(R.layout.main_activity);
        }
    }
}