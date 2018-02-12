package hackmaster20.business;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

import hackmaster20.objects.CardClass;
import hackmaster20.objects.EnemyAI;
import hackmaster20.presentation.DrawToScreen;
import hackmaster20.objects.PlayerClass;
import hackmaster20.objects.ResourceClass;
import hackmaster20.objects.PlayerStatsSaves;

/**
 * Created by Owner on 1/29/2018.
 */

public class GameManager {
    private static PlayerStatsSaves pStats;
    private static PlayerClass player1;
    private static PlayerClass player2;
    private static DeckManager deckM;
    private static ResourceManager resManager;

    private static boolean player1Turn = true;
    private static boolean paused = false;
    private static boolean inGame = false;
    private static boolean singlePlayer = false;

    public static final int dealCards = 6;
    public static final int maxCards = 50;

    private static DrawToScreen mainActivity;

    public GameManager(DrawToScreen mainAct) {
        mainActivity = mainAct;
        deckM = new DeckManager(mainAct);
        pStats = new PlayerStatsSaves();
        resManager = new ResourceManager(mainAct);
    }
//Created boolean test since it fails at draw(Can't access presentation layer in tests)
    public static void setUpSingleGame(boolean test) {
        singlePlayer = true;
        inGame = true;

        deckM.initDeck(maxCards);
        player1 = new PlayerClass(0,
                "HackerMan",
                new ResourceClass(100, 2, 2, 2, 2, 2, 2), deckM.dealCards(dealCards));

        player2 = new EnemyAI(1,
                "Enemy Bot",
                new ResourceClass(100, 2, 2, 2, 2, 2, 2), deckM.dealCards(dealCards));
        if (!test) {
            deckM.paintCard(player1.getCards());
            resManager.drawPlayerResource(player1);
            resManager.drawPlayerResource(player2);
        }
    }

    public static void playCardEvent(int playerCard, boolean test) {
        if (player1Turn) {
            mainActivity.drawPlayedCard(player1.getCard(playerCard));
             try {
                    Thread.sleep(200);
                }
                catch (InterruptedException e) {
                    // TODO do nothing
                }
            resManager.applyTurnRate(player1,test);
            playerTurn(playerCard, player1, test);
            player1Turn = false;

            if (singlePlayer) {

                /*try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    // TODO do nothing
                }*/
                int enemyCard = ((EnemyAI)player2).playNextCard();
                mainActivity.drawPlayedCard(player2.getCard(enemyCard));
                resManager.applyTurnRate(player2, test);
                playerTurn(enemyCard, player2, test);
                player1Turn = true;
            }
        }
        else {
            // playerTurn(name, player2);
        }
    }

    private static void playerTurn(int playerCard, PlayerClass player, boolean test) {
        CardClass nextCard = DeckManager.dealNextCard();
        CardClass playedCard = player.getCard(playerCard);
        ResourceManager.applyCard(player1Turn, player1, player2, playedCard,test);

        player.setCard(playerCard, nextCard);

        if (!test)
            mainActivity.DrawCard(nextCard, playerCard);
    }

    public static int getPlayerNum() {
        if(player1Turn)
            return 0;
        else
            return 1;
    }

    public static void drawCurrentGame() {
        mainActivity.drawPlayerResource(player1);
        mainActivity.drawPlayerResource(player2);

        if (player1Turn)
            deckM.paintCard(player1.getCards());
        else
            deckM.paintCard(player2.getCards());
    }

    public static void setInGame(boolean value) { inGame = value; }
    public static void setSinglePlayer(boolean set) { singlePlayer = set; }
    public static int handSize() { return dealCards; }
    public static DrawToScreen getMainAct() { return mainActivity; }
    public static boolean inGame() { return inGame; }
    public static PlayerClass getPlayer1(){ return player1; }
    public static PlayerClass getPlayer2(){ return player2; }
    public boolean getPlayer1Turn() { return player1Turn; }
}
