package hackmaster20.business;

import hackmaster20.DrawToScreen;
import hackmaster20.objects.PlayerClass;
import hackmaster20.objects.ResourceClass;
import hackmaster20.persistence.playerStatsDatabase;

/**
 * Created by Owner on 1/29/2018.
 */

public class GameManager {
    private static playerStatsDatabase pStats;
    private static PlayerClass player1;
    private static PlayerClass player2;
    private static DeckManager deckM;
    private static boolean playerTurn = true;

    private static boolean singlePlayer = false;

    private static final int dealCards = 7;
    private static final int maxCards = 50;

    private static DrawToScreen mainActivity;

    public GameManager(DrawToScreen m) {
        mainActivity = m;
        deckM = new DeckManager(m);
        pStats = new playerStatsDatabase();
    }

    public static void setUpSingleGame() {
        singlePlayer = true;

        deckM.initDeck(maxCards);
        // comething here isn't working
        player1 = new PlayerClass("SMJVE",
                new ResourceClass(0, 2, 2, 2, 2, 2, 2),
                deckM.dealCards(dealCards));
    }
}
