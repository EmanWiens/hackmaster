package hackmaster.presentation;

import android.graphics.Color;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.owner.hackmaster20.R;

import hackmaster.business.Game;
import hackmaster.business.MultiplayerGame;
import hackmaster.objects.CardClass;
import hackmaster.objects.PlayerClass;

public abstract class Render {
    private static Game gameInSession;
    private static MainActivity mainActivity;
    private static MusicManager musicManager;
    private static boolean firstSetup;

    private enum Layouts { MAIN_ACTIVITY, BATTLE_VIEW, PAUSE_VIEW, RESULTS_VIEW, STATS_VIEW, CONTINUE_VIEW }
    private static Layouts layout;
    private static int contentId;

    private enum DelayState { NO_PENDING, PENDING_DELAY, FINISHED_DELAY }
    private static DelayState delayState;

    private static boolean turnSwitch;

    private static int borderId;

    private static PlayerClass player1;
    private static PlayerClass player2;
    private static String player1Turn;
    private static String player2Turn;
    private static String aiTurn;
    private static boolean multiPlayer;

    private static int aiDelayMilli = 1250;

    public static void updateRender(Game gameInSes, MainActivity mainAct, MusicManager musicManag) {
        gameInSession = gameInSes;
        mainActivity = mainAct;
        musicManager = musicManag;
        firstSetup = true;

        player1 = gameInSession.getPlayer1();
        player2 = gameInSession.getPlayer2();
        borderId = -1;

        if (gameInSession instanceof MultiplayerGame) {
            multiPlayer = true;
        }
        else {
            multiPlayer = false;
        }

        player1Turn = "Player 1's Turn";
        player2Turn = "Player 2's Turn";
        aiTurn = "AI's Turn";
    }

    public static void updateRender(MainActivity mainAct, MusicManager musicManag) {
        borderId = -1;
        gameInSession = null;
        mainActivity = mainAct;
        musicManager = musicManag;
        firstSetup = true;
    }

    public static boolean setContentView(int contId) {
        boolean success;

        contentId = contId;
        success = updateLayoutId();

        if (success) {
            mainActivity.setContentView(contentId);
        }

        return success;
    }

    public static boolean updateScreen() {
        boolean success = false;


        if (contentId == R.layout.battle_view) {
            if (gameInSession.gameDone()) {
                if (gameInSession.getPlayer1Won()) {
                    goToVictory(true);
                } else {
                    goToVictory(false);
                }
            }
            else {
                success = renderBattleView();
            }
        }

        return success;
    }

    private static boolean updateLayoutId() {
        boolean success = false;

        if (contentId == R.layout.main_activity) {
            layout = Layouts.MAIN_ACTIVITY;
            success = true;
        }
        else if (contentId == R.layout.battle_view) {
            layout = Layouts.BATTLE_VIEW;
            success = true;
        }
        else if (contentId == R.layout.continue_view) {
            layout = Layouts.CONTINUE_VIEW;
            success = true;
        }
        else if (contentId == R.layout.pause_view) {
            layout = Layouts.PAUSE_VIEW;
            success = true;
        }
        else if (contentId == R.layout.results_view) {
            layout = Layouts.RESULTS_VIEW;
            success = true;
        }
        else if (contentId == R.layout.stats_view) {
            layout = Layouts.STATS_VIEW;
            success = true;
        }
        return success;
    }

    private static boolean renderBattleView() {
        boolean success = false;
        boolean showContinueView;
        CardClass playedCardTwo;
        CardClass playedCardOne;

        if (layout == Layouts.BATTLE_VIEW) {
            if (firstSetup) {
                setupBattleView();
                firstSetup = false;
            }
            else {
                playedCardOne = gameInSession.getPlayedCardOne();
                playedCardTwo = gameInSession.getPlayedCardTwo();

                renderPlayerResource(player1);
                renderPlayerResource(player2);

                renderDiscard();
                renderCards();
                renderPressedCardBorder(borderId);

                if (!multiPlayer) {
                    if (playedCardOne != null && !gameInSession.getRenderDelay() &&
                            delayState == DelayState.NO_PENDING) {
                        renderPlayedCard(playedCardOne, false);
                    }
                    fillText((TextView)mainActivity.findViewById(R.id.playerTurn), player1Turn);
                    if (playedCardTwo != null) {
                        if (delayState == DelayState.NO_PENDING) {
                            renderPlayedCard(playedCardTwo, true);
                            delayState = DelayState.PENDING_DELAY;
                            fillText((TextView)mainActivity.findViewById(R.id.playerTurn), aiTurn);
                        }
                        else if (delayState == DelayState.FINISHED_DELAY) {
                            renderPlayedCard(playedCardTwo, false);
                            fillText((TextView)mainActivity.findViewById(R.id.playerTurn), player1Turn);
                        }
                        // setDiscard(true);
                    }
                }
                else if (multiPlayer) {
                    // showContinueView = false;
                    if (turnSwitch) {
                        turnSwitch = true;
                        // showContinueView = true;
                    }
                    if (!gameInSession.getPlayer1Turn() && playedCardOne != null) {
                        renderPlayedCard(playedCardOne, false);
                        fillText((TextView)mainActivity.findViewById(R.id.playerTurn), player2Turn);

                        if (turnSwitch) {
                            activateContentView(player2Turn);
                            turnSwitch = false;
                            // setDiscard(true);
                        }
                    } else if (playedCardTwo != null) {
                        renderPlayedCard(playedCardTwo, false);
                        fillText((TextView)mainActivity.findViewById(R.id.playerTurn), player1Turn);

                        if (turnSwitch) {
                            activateContentView(player1Turn);
                            turnSwitch = false;
                            // setDiscard(true);
                        }
                    }
                }
            }
            success = true;
        }
        return success;
    }

    private static void setupBattleView() {
        fillText((TextView)mainActivity.findViewById(R.id.playerTurn), player1Turn);

        if (!gameInSession.gamePaused()) {
            renderPlayerResource(player1);
            renderPlayerResource(player2);
            renderCards();
        }
    }

    private static void renderDiscard() {
        ImageButton btn = mainActivity.findViewById(R.id.discardBtn);
        if (!gameInSession.getDiscard()) {
            btn.setImageResource(R.drawable.discardbutton);
        } else {
            btn.setImageResource(R.drawable.canceldiscard);
        }
    }

    private static void renderCards() {
        if (gameInSession.getPlayer1Turn()) {
            renderTheHandDeck(player1);
        } else {
            renderTheHandDeck(player2);
        }
    }

    private static void renderTheHandDeck(PlayerClass player) {
        for (int i = 0; i < player.getCards().length; i++) {
            if (player.getCards()[i] != null)
                renderCard(player.getCards()[i], i);
        }
    }

    private static void renderPlayedCard(CardClass card, boolean aiDelay) {
        Handler handler = new Handler();
        if (aiDelay && !gameInSession.gameDone()) {
            handler.postDelayed(delayRender(), aiDelayMilli); // DELAY
            gameInSession.setRenderDelay(true);
        }
        else if (gameInSession != null && !gameInSession.gamePaused()) {
            ImageView imageView = mainActivity.findViewById(R.id.imageViewPlayedCard1);
            imageView.setBackgroundResource(returnImageCardID(card.getID()));

            if (gameInSession.getRenderDelay()) {
                delayState = DelayState.FINISHED_DELAY;
                gameInSession.setRenderDelay(false);
                fillText((TextView) mainActivity.findViewById(R.id.playerTurn), player1Turn);
            }
        }
    }

    private static void renderPlayerResource(PlayerClass player) {
        if (player.getId() == 0) {
            fillText((TextView)mainActivity.findViewById(R.id.minerP), player.minerToString());
            fillText((TextView)mainActivity.findViewById(R.id.cSpeedP), player.cSpeedToString());
            fillText((TextView)mainActivity.findViewById(R.id.botnetP), player.botnetToString());

            fillText((TextView)mainActivity.findViewById(R.id.healthP), player.playerHealthToString());
            ProgressBar health = mainActivity.findViewById(R.id.healthPBarP);
            health.setProgress(player.getHealth());
            fillText((TextView)mainActivity.findViewById(R.id.player1), player.getName());
        }
        else if (player.getId() == 1) {
            fillText((TextView)mainActivity.findViewById(R.id.minerE), player.minerToString());
            fillText((TextView)mainActivity.findViewById(R.id.cSpeedE), player.cSpeedToString());
            fillText((TextView)mainActivity.findViewById(R.id.botnetE), player.botnetToString());

            fillText((TextView)mainActivity.findViewById(R.id.healthE), player.playerHealthToString());
            ProgressBar health = mainActivity.findViewById(R.id.healthPBarE);
            health.setProgress(player.getHealth());
            fillText((TextView)mainActivity.findViewById(R.id.player2), player.getName());
        }
    }

    private static void renderCard(CardClass card, int slot) {
        ImageButton imageButton = null;
        int[] imageButtonCardList = new int[]{
                R.id.imageButtonCard0, R.id.imageButtonCard1, R.id.imageButtonCard2,
                R.id.imageButtonCard3, R.id.imageButtonCard4};

        imageButton = mainActivity.findViewById(imageButtonCardList[slot]);
        imageButton.setBackgroundResource(returnImageCardID(card.getID()));
    }

    private static void renderPressedCardBorder(int chosenCard) {
        ImageView[] imageCardBorder = new ImageView[5];
        imageCardBorder[0] = mainActivity.findViewById(R.id.imageBorderCard0);
        imageCardBorder[1] = mainActivity.findViewById(R.id.imageBorderCard1);
        imageCardBorder[2] = mainActivity.findViewById(R.id.imageBorderCard2);
        imageCardBorder[3] = mainActivity.findViewById(R.id.imageBorderCard3);
        imageCardBorder[4] = mainActivity.findViewById(R.id.imageBorderCard4);
        musicManager.playCardSelected(0.8f, 0.8f);
        for (int i = 0; i < imageCardBorder.length; i++) {
            if (i == chosenCard) {
                imageCardBorder[i].setBackgroundResource(R.drawable.image_border);
            }
            else {
                imageCardBorder[i].setBackgroundResource(android.R.color.transparent);
            }
        }
    }

    private static void activateContentView(String playerTurn)
    {
        mainActivity.setContentView(R.layout.continue_view);
        TextView textView = mainActivity.findViewById(R.id.textViewPlayerTurn);
        textView.setText(playerTurn);
        textView.setTextColor(Color.RED);

        if (playerTurn.equals("Player 1's Turn")) {
            textView.setTextColor(Color.BLUE);
        }
    }

    private static Runnable delayRender() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                renderPlayedCard(gameInSession.getPlayedCardTwo(), false);
            }
        };
        return r;
    }

    private static int returnImageCardID(int cardID) {
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

    private static void goToVictory(boolean winner) {
        setContentView(R.layout.results_view);
        gameInSession = null;
        ImageView img = mainActivity.findViewById(R.id.statsImg);
        TextView textView = mainActivity.findViewById(R.id.textViewResult);

        if (winner) {
            img.setImageResource(R.drawable.victory);
            textView.setText("PLAYER 1 WON");
            textView.setTextColor(Color.BLUE);
        } else {
            img.setImageResource(R.drawable.defeat);
            textView.setText("PlAYER 1 LOSE");
            textView.setTextColor(Color.RED);
        }
    }

    private static void fillText (TextView view, String string) { view.setText(string); }

    static void setBorderId(int id) { borderId = id; }
    static void resetDelayState() { delayState = DelayState.NO_PENDING; }
    static void setTurnSwitch() { turnSwitch = true; }
}
