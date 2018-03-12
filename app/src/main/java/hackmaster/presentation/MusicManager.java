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
        int  n = rand.nextInt(6) + 1;
        if (n==1) {
            //credit: https://www.youtube.com/watch?v=b-Cr0EWwaTk
            mediaPlayer = MediaPlayer.create(contextVariable, R.raw.javarapsong);
        }
        else if (n==2)
        {
            mediaPlayer = MediaPlayer.create(contextVariable, R.raw.background);
        }
        else if (n==3)
        {
            //credit: https://www.youtube.com/watch?v=FoUWHfh733Y&index=21&list=RDiN1uaITfA1c
            mediaPlayer = MediaPlayer.create( contextVariable, R.raw.dualcore);
        }
        else if (n==4)
        {
            //credit: https://www.youtube.com/watch?v=iN1uaITfA1c&index=1&list=RDiN1uaITfA1c
            mediaPlayer = MediaPlayer.create( contextVariable, R.raw.hackersong);
        }
        else if (n==5)
        {
            //credit: https://www.youtube.com/watch?v=rLsJCCNXUto&list=RDiN1uaITfA1c&index=3
            mediaPlayer = MediaPlayer.create( contextVariable, R.raw.welcometoourworld);
        }
        else if (n==6)
        {
            //credit: https://www.youtube.com/watch?v=Gc74aRe7OLM
            mediaPlayer = MediaPlayer.create(contextVariable, R.raw.piratemusic);
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
