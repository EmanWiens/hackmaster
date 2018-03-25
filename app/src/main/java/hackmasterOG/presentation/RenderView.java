package hackmasterOG.presentation;


import android.graphics.Color;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.owner.hackmaster20.R;

import java.util.LinkedList;

import hackmasterOG.business.Game;
import hackmasterOG.business.MultiplayerGame;
import hackmasterOG.objects.CardClass;
import hackmasterOG.objects.PlayerClass;

public class RenderView {
    private Game gameInSession;
    private MainActivity mainActivity;
    private MusicManager musicManager;
    private PlayerClass player1;
    private PlayerClass player2;
    private String player1Turn;
    private String player2Turn;
    private String aiTurn;
    private boolean showContinueView;
    private CardClass playedCardTwo;
    private CardClass playedCardOne;
    private TextView playerTurnText;
    boolean multiPlayer;

    private LinkedList<Object> renderQ;
    private int priority;

    public RenderView(Game gameInSes, MainActivity mainAct,  MusicManager musicManag) {
        gameInSession = gameInSes;
        mainActivity = mainAct;
        musicManager = musicManag;
        mainActivity.setContentView(R.layout.battle_view);
        priority = 0;
        initSetUp();
    }

    private void initSetUp() {
       if (gameInSession instanceof MultiplayerGame) {
           multiPlayer = true;
       }

       player1 = gameInSession.getPlayer1();
       player2 = gameInSession.getPlayer2();
       playerTurnText = mainActivity.findViewById(R.id.playerTurn);
       player1Turn = "Player 1's Turn";
       player2Turn = "Player 2's Turn";
       aiTurn = "AI Turn";
    }

    public void setUpBattleView() {
        playerTurnText.setText("Player 1 Turn");

        if (!gameInSession.gamePaused()) {
            renderPlayerResource(player1);
            renderPlayerResource(player2);
            renderCards();
        }
    }

    public void setDiscard (boolean toggle) {
        if (toggle) {
            gameInSession.discardOff();
//            Button dicardButton = mainActivity.findViewById(R.id.discardBtn);
//            dicardButton.setText("DISCARD MODE");
            ImageButton btn = (ImageButton)mainActivity.findViewById(R.id.discardBtn);
            btn.setImageResource(R.drawable.discardbutton);
        } else {
            gameInSession.discardOn();
//            Button dicardButton = mainActivity.findViewById(R.id.discardBtn);
//            dicardButton.setText("CANCEL DISCARD");
            ImageButton btn = (ImageButton)mainActivity.findViewById(R.id.discardBtn);
            btn.setImageResource(R.drawable.canceldiscard);
        }
    }

    public void renderBattleView(int borderID) {
            playedCardOne = gameInSession.getPlayedCardOne();
            playedCardTwo = gameInSession.getPlayedCardTwo();
            renderPlayerResource(player1);
            renderPlayerResource(player2);
            showContinueView=false;
            if (borderID!=-1) {
                renderPressedCardBorder(borderID);
                showContinueView=true;
            }
            renderCards();
            if (!multiPlayer) {
                if (playedCardOne != null && !gameInSession.getRenderDelay() )
                    renderPlayedCard(playedCardOne, false);
                playerTurnText.setText(player1Turn);
                if (playedCardTwo != null )
                    renderPlayedCard(playedCardTwo, true);
                playerTurnText.setText(aiTurn);
            } else if (multiPlayer) {

                if (!gameInSession.getPlayer1Turn() && playedCardOne!=null) {
                    renderPlayedCard(playedCardOne, false);
                    playerTurnText.setText(player2Turn);
                    if (showContinueView)
                        activateContentView(player2Turn);
                } else if (playedCardTwo!=null) {
                    renderPlayedCard(playedCardTwo, false);
                    playerTurnText.setText(player1Turn);
                    if (showContinueView)
                    activateContentView(player1Turn);
                }
            }

        if (gameInSession.gameDone())
           {
                getWinner();
           }
    }

    private void renderCards() {
        if (gameInSession.getPlayer1Turn()) {
            renderTheHandDeck(player1);
        } else {
            renderTheHandDeck(player2);
        }
        if (gameInSession.getDiscard()) {
            setDiscard(false);
        } else {
            setDiscard(true);
        }
    }

    private void renderTheHandDeck(PlayerClass player) {
        for (int i = 0; i < player.getCards().length; i++) {
            if (player.getCards()[i] != null)
                renderCard(player.getCards()[i], i);
        }
    }

    public void renderPlayedCard(CardClass card, boolean aiDelay) {
        Handler handler = new Handler();
        if (aiDelay && !gameInSession.gameDone()) {
            handler.postDelayed(delayRender(), 1850); // DELAY
            gameInSession.setRenderDelay(true);
        }
        else if (gameInSession != null && !gameInSession.gamePaused()) {
            ImageView imageView = mainActivity.findViewById(R.id.imageViewPlayedCard1);
            imageView.setBackgroundResource(returnImageCardID(card.getID()));

            if (gameInSession.getRenderDelay()) {
                gameInSession.setRenderDelay(false);
                fillText((TextView) mainActivity.findViewById(R.id.playerTurn), "Player 1's turn");
            }
        }
    }

    public void renderPlayerResource(PlayerClass player) {
        if (player.getId() == 0) {
            fillText((TextView)mainActivity.findViewById(R.id.minerP), player.minerToString());
            fillText((TextView)mainActivity.findViewById(R.id.cSpeedP), player.cSpeedToString());
            fillText((TextView)mainActivity.findViewById(R.id.botnetP), player.botnetToString());

            fillText((TextView)mainActivity.findViewById(R.id.healthP), player.playerHealthToString());
            ProgressBar health = mainActivity.findViewById(R.id.healthPBarP);
            health.setProgress(player.getHealth());
            fillText((TextView)mainActivity.findViewById(R.id.player1), player.getName());
        } else if (player.getId() == 1) {
            fillText((TextView)mainActivity.findViewById(R.id.minerE), player.minerToString());
            fillText((TextView)mainActivity.findViewById(R.id.cSpeedE), player.cSpeedToString());
            fillText((TextView)mainActivity.findViewById(R.id.botnetE), player.botnetToString());

            fillText((TextView)mainActivity.findViewById(R.id.healthE), player.playerHealthToString());
            ProgressBar health = mainActivity.findViewById(R.id.healthPBarE);
            health.setProgress(player.getHealth());
            fillText((TextView)mainActivity.findViewById(R.id.player2), player.getName());
        }
    }

    public void renderCard(CardClass card, int slot) {
        ImageButton imageButton = null;
        int[] imageButtonCardList = new int[]{
                R.id.imageButtonCard0, R.id.imageButtonCard1, R.id.imageButtonCard2,
                R.id.imageButtonCard3, R.id.imageButtonCard4};

        imageButton = mainActivity.findViewById(imageButtonCardList[slot]);
        imageButton.setBackgroundResource(returnImageCardID(card.getID()));
    }

    public void renderPressedCardBorder(int chosenCard) {
        ImageView[] imageCardBorder = new ImageView[6];
        imageCardBorder[0] = mainActivity.findViewById(R.id.imageBorderCard0);
        imageCardBorder[1] = mainActivity.findViewById(R.id.imageBorderCard1);
        imageCardBorder[2] = mainActivity.findViewById(R.id.imageBorderCard2);
        imageCardBorder[3] = mainActivity.findViewById(R.id.imageBorderCard3);
        imageCardBorder[4] = mainActivity.findViewById(R.id.imageBorderCard4);
        musicManager.playCardSelected(0.8f, 0.8f);
        for (int i = 0; i <= 4; i++) {
            if (i == chosenCard) {
                imageCardBorder[i].setBackgroundResource(R.drawable.image_border);
            }
            else {
                imageCardBorder[i].setBackgroundResource(android.R.color.transparent);
            }
        }
    }

    public void activateContentView(String playerTurn)
    {
        mainActivity.setContentView(R.layout.continue_view);
        TextView textView = mainActivity.findViewById(R.id.textViewPlayerTurn);
        textView.setText(playerTurn);
        if (playerTurn.equals("Player 1's Turn"))
        {
            textView.setTextColor(Color.RED);
        }
    }

    private Runnable delayRender() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                renderPlayedCard(gameInSession.getPlayedCardTwo(), false);
            }
        };
        return r;
    }

    private int returnImageCardID(int cardID) {
        int[] imageCardList = new int[]{
                R.drawable.morecores, R.drawable.morecores, R.drawable.botnet,
                R.drawable.cutsomewires, R.drawable.upgradebotnet, R.drawable.upgradecpu,
                R.drawable.upgradehashrate, R.drawable.ddos, R.drawable.filetransfer,
                R.drawable.popup, R.drawable.antivirus, R.drawable.firewall,
                R.drawable.playthemarket, R.drawable.overclock, R.drawable.serverfarm,
                R.drawable.expand, R.drawable.marketcrash, R.drawable.networkoutage,
                R.drawable.throttle, R.drawable.hack, R.drawable.debug, R.drawable.exploit,
                R.drawable.zeroday, R.drawable.attackplus, R.drawable.attackplusplus,
                R.drawable.attackphash, R.drawable.extremehack, R.drawable.epichack,
                R.drawable.masshack
        };
        return imageCardList[cardID];
    }

    public void getWinner() {
        if (gameInSession.getPlayer2Health() < 1) {
            goToVictory(true);
        } else {
            goToVictory(false);
        }
    }

    public void goToVictory(boolean winner) {
        mainActivity.setContentView(R.layout.results_view);
        gameInSession = null;
        ImageView img = mainActivity.findViewById(R.id.statsImg);
        TextView textView = mainActivity.findViewById(R.id.textViewResult);
        if (winner) {
            img.setImageResource(R.drawable.victory);
            textView.setText("PlAYER 1 WIN");
        } else {
            img.setImageResource(R.drawable.defeat);
            textView.setText("PlAYER 1 LOSE");
            textView.setTextColor(Color.RED);
        }
    }

    private void fillText (TextView view, String string) {view.setText(string);}
}




