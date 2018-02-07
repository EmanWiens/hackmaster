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
    private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameManager = new GameManager(this);
        setContentView(R.layout.main_activity);
    }

    @Override
    public void onBackPressed() {
        View currtLayout = findViewById(android.R.id.content);
        int currLayoutId = currtLayout.getId();

        if (currLayoutId == R.id.main_activity)
            return;
        else
            setContentView(R.layout.main_activity);
    }

    public void statsPress(View v) {
        setContentView(R.layout.stats_view);
    }

    public void DrawCard(CardClass card, int slot) {
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
        setContentView(R.layout.battle_view);
        gameManager.setUpSingleGame();
    }

    public void cardPress(View v) {
        String name[] = null;
        name = ((TextView) v).getText().toString().split("\n");
        TextView playedCard = (TextView) findViewById(R.id.playedCard);
        // Thread.sleep(500);
        playedCard.setText(((TextView) v).getText());

        if (name != null)
            gameManager.playCardEvent(name[0]);

    }
}
