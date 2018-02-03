package hackmaster20.business;

import hackmaster20.DrawToScreen;
import hackmaster20.objects.CardClass;
import hackmaster20.objects.ResourceClass;

/**
 * Created by Owner on 1/29/2018.
 */

public class DeckManager {
    private static CardClass[] deck = null;

    private static DrawToScreen mainActivity;
    public DeckManager(DrawToScreen mainAct) {
        mainActivity = mainAct;
    }

    public static CardClass[] testCards() {
        // make some cards where you set all the specs
        int count = 0;
        CardClass cards[] = new CardClass[10];

        CardClass tempCard = new CardClass(0, "CPU Boost", "Upgrade", "Upgrade your CPU",
                new ResourceManager(new ResourceClass(0, -10, 0, 0, 0,1, 0), null));
        cards[count] = tempCard;
        // tempCard.show();
        count++;

        tempCard = new CardClass(1, "More Cores", "Defense", "Upgrade your CPU",
                new ResourceManager(new ResourceClass(0, -5, 10, 0, 0,0, 0), null));
        cards[count] = tempCard;
        count++;

        tempCard = new CardClass(1, "bot.net", "Attack", "Upgrade your CPU",
                new ResourceManager(new ResourceClass(0, -5, 10, -3, 0,0, 2), null));
        cards[count] = tempCard;
        count++;

        tempCard = new CardClass(1, "cut some wires", "Defense", "Upgrade your CPU",
                new ResourceManager(new ResourceClass(0, 0, 0, -20, 0,0, 0)
                        , new ResourceClass(-10, 0, 0, 0, 0,0, 0)));
        cards[count] = tempCard;
        count++;

        tempCard = new CardClass(1, "More Cores", "Defense", "Upgrade your CPU",
                new ResourceManager(new ResourceClass(0, -5, 10, 0, 0,0, 0), null));
        cards[count] = tempCard;
        count++;

        tempCard = new CardClass(1, "More Cores", "Defense", "Upgrade your CPU",
                new ResourceManager(new ResourceClass(0, -5, 10, 0, 0,0, 0), null));
        cards[count] = tempCard;
        count++;

        return cards;
    }

    // create a deck for the whole game
    public static void initDeck(int size) {
        CardClass[] cards = new CardClass[size];
        CardClass temp;

        deck = cards;
    }

    public static CardClass[] dealCards(int deal) {
        CardClass[] cards = testCards(); // = new CardClass[deal];
        CardClass temp;

        for (int i = 0; i < deal; i++) {
            mainActivity.DrawCard(cards[i], i);
        }

        return cards;
    }
    public static int getSizeDeck() {
        return deck.length;
    }
}
