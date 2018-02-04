package hackmaster20.presentation;

// import hackmaster20.presentation.*;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.owner.hackmaster20.R;

import hackmaster20.business.GameManager;
import hackmaster20.objects.CardClass;

public class MainActivity extends AppCompatActivity implements DrawToScreen {
    // give a "copy" of the interface to the gameManager
    private GameManager gameManager = new GameManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void DrawCard(CardClass card, int slot) {
        // int id = getResources().getIdentifier(card.getName(), null, null);
        TextView textView = null;

        if (slot == 0)
            textView = (TextView)findViewById(R.id.card0);
        else if (slot == 1)
            textView = (TextView)findViewById(R.id.card1);
        else if (slot == 2)
            textView = (TextView)findViewById(R.id.card2);
        else if (slot == 3)
            textView = (TextView)findViewById(R.id.card3);
        else if (slot == 4)
            textView = (TextView)findViewById(R.id.card4);
        else if (slot == 5)
            textView = (TextView)findViewById(R.id.card5);

        textView.setText(card.toString());
    }

    public void playMessage(View v) {
        setContentView(R.layout.sample_my_view);
        gameManager.setUpSingleGame();
    }

    public void cardPress(View v) {
        TextView playedCard = (TextView)findViewById(R.id.playedCard1);
        playedCard.setText(((TextView)v).getText());
    }
}
