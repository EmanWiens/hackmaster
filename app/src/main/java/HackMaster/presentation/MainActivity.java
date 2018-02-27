package HackMaster.presentation;



import android.app.AlertDialog;
import android.content.DialogInterface;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.owner.hackmaster20.R;

import HackMaster.business.GameManager;
import HackMaster.objects.CardClass;
import HackMaster.objects.PlayerClass;

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

    public void localMultiplayerMessage(View v) {
        // TODO write the code that sets up a multiplayer game
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

            fillText((TextView)findViewById(R.id.healthE), player.toStringHealth());
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
        TextView textView;
        String cardText = (slot+1) + ". " +card.toString();
        if (slot == 0)
            textView = findViewById(R.id.card0);
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

    synchronized public void drawPlayedCard(CardClass card, boolean delay) {
        // DELAY
        Handler handler = new Handler();
        if (delay) {
            handler.postDelayed(delayDraw(), 2000); // DELAY
            GameManager.setDelayAi(true);
        }
        else {
            GameManager.setDelayAi(false);
            TextView playedCard = findViewById(R.id.playedCard1);
            playedCard.setText(card.toString());
        }
    }

    // DELAY
    public Runnable delayDraw() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                drawPlayedCard(GameManager.getPlayedCardAi(), false);
                GameManager.setPlayer1Turn(true);
            }
        };
        return r;
    }

    public void playMessage(View v) {
        setContentView(R.layout.battle_view);
        gameManager.setUpSingleGame(false);
    }

    public void cardPress(View v) {
        String name[] = ((TextView) v).getText().toString().split("\n");

        if (gameManager.getPlayer1Turn() && !GameManager.getDelayAi()) {
            gameManager.playCardEvent(Character.getNumericValue(name[0].charAt(0)) - 1);
        }
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
