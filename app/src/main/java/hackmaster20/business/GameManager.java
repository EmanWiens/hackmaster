package hackmaster20.business;

import hackmaster20.objects.CardClass;
import hackmaster20.presentation.DrawToScreen;
import hackmaster20.objects.PlayerClass;
import hackmaster20.objects.ResourceClass;
import hackmaster20.persistence.PlayerStatsSaves;

/**
 * Created by Owner on 1/29/2018.
 */

public class GameManager {
    private static PlayerStatsSaves pStats;
    private static PlayerClass player1;
    private static PlayerClass player2;
    private static DeckManager deckM;

    private static boolean player1Turn = true;
    private static boolean paused = false;

    private static boolean singlePlayer = false;

    private static final int dealCards = 6;
    private static final int maxCards = 50;

    private static DrawToScreen mainActivity;

    public static void setSinglePlayer(boolean set) { singlePlayer = set; }
    public static DrawToScreen getMainAct() { return mainActivity; }

    public boolean getPlayerTurn() { return player1Turn; }

    public GameManager(DrawToScreen mainAct) {
        mainActivity = mainAct;
        deckM = new DeckManager(mainAct);
        pStats = new PlayerStatsSaves();
    }

    public static void setUpSingleGame() {
        singlePlayer = true;

        deckM.initDeck(maxCards);
        // comething here isn't working
        player1 = new PlayerClass("SMJVE",
                new ResourceClass(0, 2, 2, 2, 2, 2, 2),
                deckM.dealCards(dealCards));
        // SinglePlayerGameLoop();
    }

    public static void playCardEvent(String name) {
        // TODO write a function that finds the card played by the current player and does the game things with it
        CardClass card;
        if (player1Turn) {
            // card = player1.searchCards();
        }
        else {
            // card = player2.searchCards();
        }
    }

    // gameManager methods to keep the game in a suspended loop
    private static void SinglePlayerGameLoop() {
        boolean exitGame = false;

        while (true) {
            if (exitGame) // break out at any point
                break;
            // TODO write the player check functions
        }
    }

    // suspend a loop until the player makes their turn
    private static void waitForPlayer() {
        boolean moveMade = false;

        while (!moveMade) {
            // TODO make the a listener that listens to which card you clicked
        }
    }
}
