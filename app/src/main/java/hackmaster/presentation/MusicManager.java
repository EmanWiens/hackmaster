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
    private static Context contextVariable;
    private boolean soundPoolLoaded;
    private boolean musicOn;

    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;
    private int soundIdCardSelected;

    public MusicManager(Context context) {
        contextVariable= context;
    }
    //Credit: https://o7planning.org/en/10521/android-2d-game-tutorial-for-beginners
    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public void initSoundPool()  {
        if (Build.VERSION.SDK_INT >= 21 ) {

            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder= new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            this.soundPool = builder.build();
        }
        else {
            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPoolLoaded = true;
            }
        });
        this.soundIdCardSelected = this.soundPool.load(contextVariable, R.raw.select,1);
    }
    public void backGroundMusicStart() {
        musicOn =true;
        Random rand = new Random();
        int  randomNum = rand.nextInt(6) + 1;
        int[] songsList = new int[]{
                R.raw.javarapsong,  R.raw.background, R.raw.dualcore,
                R.raw.hackersong, R.raw.piratemusic, R.raw.welcometoourworld};
        mediaPlayer = MediaPlayer.create(contextVariable, songsList[randomNum]);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }
    public void playCardSelected(float leftVolumn, float rightVolumn) {
        if(getSoundPoolLoaded()) {
            // Play sound CardSelected.wav
            this.soundPool.play(this.soundIdCardSelected,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }
    public  void pauseBacgroundMusic() {
        mediaPlayer.pause();
        musicOn =false;
    }
    public  void resumeBacgroundMusic() {
        mediaPlayer.start();
        musicOn =true;
    }
    private boolean getSoundPoolLoaded() {return soundPoolLoaded;}
    public  boolean getStateMusic() {return musicOn;}
}
