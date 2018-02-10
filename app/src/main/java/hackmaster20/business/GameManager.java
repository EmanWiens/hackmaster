package hackmaster20.business;

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

    private static final int dealCards = 6;
    private static final int maxCards = 50;

    private static DrawToScreen mainActivity;

    public static int handSize() { return dealCards; }
    public static void setSinglePlayer(boolean set) { singlePlayer = set; }
    public static DrawToScreen getMainAct() { return mainActivity; }
    public static boolean inGame() { return inGame; }
    public static void setInGame(boolean value) { inGame = value; }

    //TODO Not sure what it actually does, seems like getPlayer1Turn does the same by returning boolean object
    public static int getPlayerNum() {
        if(player1Turn)
            return 0;
        else
            return 1;
    }
    public static PlayerClass getPlayer1(){ return player1; }
    public static PlayerClass getPlayer2(){ return player1; }


    public boolean getPlayer1Turn() { return player1Turn; }

    public GameManager(DrawToScreen mainAct) {
        mainActivity = mainAct;
        deckM = new DeckManager(mainAct);
        pStats = new PlayerStatsSaves();
        resManager = new ResourceManager(mainAct);
    }

    public static void setUpSingleGame() {
        singlePlayer = true;
        inGame = true;

        deckM.initDeck(maxCards);
        player1 = new PlayerClass(0,
                "p1",
                new ResourceClass(100, 2, 2, 2, 2, 2, 2), deckM.dealCards(dealCards));
        deckM.paintCard(player1.getCards());
        resManager.drawPlayerResource(player1);

        player2 = new EnemyAI(1,
                "p2",
                new ResourceClass(100, 2, 2, 2, 2, 2, 2), deckM.dealCards(dealCards));
        resManager.drawPlayerResource(player2);
    }

    public static void playCardEvent(String name) {
        if (player1Turn) {
            playerTurn(name, player1);
            player1Turn = false;

            if (singlePlayer) {
                // TODO player2.playNextCard();
                // playerTurn(name, player2);
                player1Turn = true;
            }
        }
        else {
        }
    }

    private static void playerTurn(String name, PlayerClass player) {
        int cardIndex = player.findPlayerCardIndex(name);
        CardClass card = DeckManager.dealNextCard();
        player.setCard(cardIndex, card);
        mainActivity.DrawCard(card, cardIndex);

        ResourceManager.applyCard(player1Turn, player1, player2, card);

        resManager.applyTurnRate(player2);
    }
}
