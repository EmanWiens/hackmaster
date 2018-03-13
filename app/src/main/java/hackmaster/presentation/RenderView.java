package hackmaster.presentation;


import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;

import com.example.owner.hackmaster20.R;

import hackmaster.business.Game;
import hackmaster.business.MultiplayerGame;
import hackmaster.business.SinglePlayerGame;
import hackmaster.objects.CardClass;
import hackmaster.objects.PlayerClass;


public class RenderView {
    private Game gameInSession;
    private MainActivity mainActivity;
    boolean setUpDone=false;

    public RenderView(Game gameInSes, MainActivity mainAct) {
        gameInSession = gameInSes;
        mainActivity = mainAct;
        mainActivity.setContentView(R.layout.battle_view);
    }

    public void renderPlayedCard(CardClass card, boolean aiDelay) {
        Handler handler = new Handler();
        if (aiDelay && !gameDone()) {
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

    public void renderBattleView() {
        CardClass playedCardTwo = gameInSession.getPlayedCardTwo();
        CardClass playedCardOne = gameInSession.getPlayedCardOne();
        PlayerClass player1 = gameInSession.getPlayer1();
        PlayerClass player2 = gameInSession.getPlayer2();

        if (!gameInSession.gamePaused()) {
            renderPlayerResource(player1);
            renderPlayerResource(player2);

            if (gameInSession instanceof SinglePlayerGame ) {
                if (playedCardOne != null && !gameInSession.getRenderDelay()) {
                    renderPlayedCard(playedCardOne, false);
                }

                if (playedCardTwo != null && gameInSession instanceof SinglePlayerGame) {
                    renderPlayedCard(playedCardTwo, true);
                }

                if (gameInSession.getRenderDelay()) {
                    fillText((TextView)mainActivity.findViewById(R.id.playerTurn), "AI Turn");
                }
                else {

                }
            }
            else if (gameInSession instanceof MultiplayerGame) {

                if(!gameInSession.getPlayer1Turn() && playedCardOne != null) {
                    renderPlayedCard(playedCardOne, false);
                }
                else if (gameInSession.getPlayer1Turn() && playedCardTwo != null) {
                    renderPlayedCard(playedCardTwo, false);
                }

                if (gameInSession.getPlayer1Turn()) {
                    fillText((TextView)mainActivity.findViewById(R.id.playerTurn), "Player 1's turn");
                }
                else {
                    fillText((TextView)mainActivity.findViewById(R.id.playerTurn), "Player 2's turn");
                }
            }

            if (gameInSession.getPlayer1Turn()) {
                for (int i = 0; i < player1.getCards().length; i++) {
                    if (player1.getCards()[i] != null)
                        renderCard(player1.getCards()[i], i);
                }
            }
            else {
                for (int i = 0; i < player2.getCards().length; i++) {
                    if (player2.getCards()[i] != null)
                        renderCard(player2.getCards()[i], i);
                }
            }

            if(gameInSession.getDiscard()) {
                setDiscard(false);
            } else {
                setDiscard(true);
            }
        }
    }

    public void renderCard(CardClass card, int slot) {
        ImageButton imageButton = null;
        int[] imageButtonCardList = new int[]{
                R.id.imageButtonCard0, R.id.imageButtonCard1,R.id.imageButtonCard2,
                R.id.imageButtonCard3,R.id.imageButtonCard4};

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
     //TODO Make sure Sound Plays   musicManager.playCardSelected(0.8f, 0.8f);

        for (int i = 0; i <= 4; i++) {
            if (i == chosenCard) {
                imageCardBorder[i].setBackgroundResource(R.drawable.image_border);
            }
            else {
                imageCardBorder[i].setBackgroundResource(android.R.color.transparent);
            }
        }
    }

    public void discardPress(View v) {
        if (gameInSession.getDiscard()) {
            setDiscard(true);
        } else {
            setDiscard(false);
        }
    }

    public void setDiscard (boolean toggle) {
        if (toggle) {
            gameInSession.discardOff();
            Button dicardButton = mainActivity.findViewById(R.id.discardBtn);
            dicardButton.setText("DISCARD MODE");
        } else {
            gameInSession.discardOn();
            Button dicardButton = mainActivity.findViewById(R.id.discardBtn);
            dicardButton.setText("CANCEL DISCARD");
        }
    }

    // DELAY
    private Runnable delayRender() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                renderPlayedCard(gameInSession.getPlayedCardTwo(), false);
            }
        };
        return r;
    }

    public void fillText (TextView view, String string) {view.setText(string);}

    private int returnImageCardID(int cardID)
    {
        int[] imageCardList = new int[]{
                R.drawable.morecores,R.drawable.morecores, R.drawable.botnet,
                R.drawable.cutsomewires, R.drawable.upgradebotnet,R.drawable.upgradecpu,
                R.drawable.upgradehashrate,R.drawable.ddos,R.drawable.filetransfer,
                R.drawable.popup,R.drawable.antivirus,R.drawable.firewall,
                R.drawable.playthemarket,R.drawable.overclock,R.drawable.serverfarm,
                R.drawable.expand,R.drawable.marketcrash, R.drawable.networkoutage,
                R.drawable.throttle, R.drawable.hack,R.drawable.debug, R.drawable.exploit,
                R.drawable.zeroday, R.drawable.attackplus, R.drawable.attackplusplus,
                R.drawable.attackphash, R.drawable.extremehack,R.drawable.epichack,
                R.drawable.masshack
        };
        return imageCardList[cardID];
    }

    // TODO should be in Game.java
    public boolean gameDone() {
        boolean result = false;
        if (gameInSession.getPlayer2Health() < 1) {
            result = true;
        }
        if (gameInSession.getPlayer1Health() < 1) {
            result = true;
        }
        return result;
    }

    public void activateContentView()
    {
        gameInSession.pauseGame();
    }
}
