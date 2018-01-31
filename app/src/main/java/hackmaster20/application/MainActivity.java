package hackmaster20.application;

// import hackmaster20.presentation.*;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import com.example.owner.hackmaster20.R;

import hackmaster20.business.DeckManager;
import hackmaster20.business.GameManager;
import hackmaster20.objects.PlayerClass;
import hackmaster20.objects.CardClass;
import hackmaster20.objects.ResourceClass;
import hackmaster20.persistence.playerStatsDatabase;

//test
public class MainActivity extends AppCompatActivity {
// comment vlad is awesome
    private GameManager gameManager = new GameManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void playMessage(View v) {
        // do something
        setContentView(R.layout.sample_my_view);
        gameManager.setUpSingleGame();
    }
}
