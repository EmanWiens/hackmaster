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

    public boolean getPlayer1Turn() { return player1Turn; }

    public GameManager(DrawToScreen mainAct) {
        mainActivity = mainAct;
        deckM = new DeckManager(mainAct);
        pStats = new PlayerStatsSaves();
    }

    public static void setUpSingleGame() {
        singlePlayer = true;

        deckM.initDeck(maxCards);
        player1 = new PlayerClass("p1",
                new ResourceClass(100, 2, 2, 2, 2, 2, 2),
                deckM.dealCards(dealCards));
        deckM.drawCards(player1.getCards());

        player2 = new PlayerClass("p2",
                new ResourceClass(100, 2, 2, 2, 2, 2, 2),
                deckM.dealCards(dealCards));
    }

    public static void playCardEvent(String name) {
        if (player1Turn) {
            int cardIndex = player1.findPlayerCardIndex(name);
            CardClass card = player1.getCardByIndex(cardIndex);
            // TODO take out the card from the player and give the player a new card from the deck
            player1.setCard(cardIndex, DeckManager.getACard());
            // TODO apply the card to the player and enemy

            // Thread.sleep(millis); // timer after the card is played
        }
        else {

        }
    }
}
