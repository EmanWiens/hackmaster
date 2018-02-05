package hackmaster20.application;

// import hackmaster20.presentation.*;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import com.example.owner.hackmaster20.R;

import hackmaster20.DrawToScreen;
import hackmaster20.business.DeckManager;
import hackmaster20.business.GameManager;
import hackmaster20.objects.PlayerClass;
import hackmaster20.objects.CardClass;
import hackmaster20.objects.ResourceClass;
import hackmaster20.persistence.playerStatsDatabase;

public class MainActivity extends AppCompatActivity implements DrawToScreen {
    // give a "copy" of the interface to the gameManager
    private GameManager gameManager = new GameManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void DrawCard(CardClass card) {
        TextView tv1 = (TextView)findViewById(R.id.card0);
        tv1.setText(card.toString());
    }

    public void playMessage(View v) {
        setContentView(R.layout.sample_my_view);
        gameManager.setUpSingleGame();
    }
}
