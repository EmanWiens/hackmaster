package hackmaster.presentation;


import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.owner.hackmaster20.R;

import java.util.Random;


public class MusicManager {
    private static final int MAX_STREAMS=100;
    private static DrawToScreen mainActivity;
    private boolean soundPoolLoaded;
    private boolean resumeMusic;
    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;
    private int soundIdCardSelected;

    public MusicManager(DrawToScreen mainActivity) {
         mainActivity = mainActivity;
    }
    //Credit: https://o7planning.org/en/10521/android-2d-game-tutorial-for-beginners
    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public void initSoundPool()  {
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
        this.soundIdCardSelected = this.soundPool.load((Context) mainActivity, R.raw.select,1);
    }
    public void backGroundMusicStart() {
        resumeMusic=true;
        Random rand = new Random();
        int  n = rand.nextInt(6) + 1;
        if (n==1) {
            //https://www.youtube.com/watch?v=b-Cr0EWwaTk
            mediaPlayer = MediaPlayer.create((Context) mainActivity, R.raw.javarapsong);
        }
        else if (n==2)
        {
            mediaPlayer = MediaPlayer.create((Context) mainActivity, R.raw.background);
        }
        else if (n==3)
        {
            //credit: https://www.youtube.com/watch?v=FoUWHfh733Y&index=21&list=RDiN1uaITfA1c
            mediaPlayer = MediaPlayer.create((Context) mainActivity, R.raw.dualcore);
        }
        else if (n==4)
        {
            //credit: https://www.youtube.com/watch?v=iN1uaITfA1c&index=1&list=RDiN1uaITfA1c
            mediaPlayer = MediaPlayer.create((Context) mainActivity, R.raw.hackersong);
        }
        else if (n==5)
        {
            //credit: https://www.youtube.com/watch?v=rLsJCCNXUto&list=RDiN1uaITfA1c&index=3
            mediaPlayer = MediaPlayer.create((Context) mainActivity, R.raw.welcometoourworld);
        }
        else if (n==6)
        {
            //credit: https://www.youtube.com/watch?v=Gc74aRe7OLM
            mediaPlayer = MediaPlayer.create((Context) mainActivity, R.raw.piratemusic);
        }
        mediaPlayer.start(); // no need to call prepare(); create() does that for you
        mediaPlayer.setLooping(true);
    }
    public void playCardSelected(float leftVolumn, float rightVolumn) {
        if(getSoundPoolLoaded()) {
            // Play sound CardSelected.wav
            this.soundPool.play(this.soundIdCardSelected,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }
    private boolean getSoundPoolLoaded() {return soundPoolLoaded;}
    public  void pauseBacgroundMusic() {mediaPlayer.pause();}
    public  void resumeBacgroundMusic() {mediaPlayer.pause();}
}
