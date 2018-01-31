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
    public DeckManager(DrawToScreen m) {
        mainActivity = m;
    }

    public static CardClass[] testCards() {
        // make some cards where you set all the specs
        int count = 0;
        CardClass cards[] = new CardClass[10];

        CardClass tempCard = new CardClass("CPU Boost", "Upgrade", "Upgrade your CPU",
                new ResourceManager(new ResourceClass(0, 0, 0, 0, 0,1, 0), null));
        cards[count] = tempCard;
        // tempCard.show();
        count++;

        tempCard = new CardClass("More Cores", "Defense", "Upgrade your CPU",
                new ResourceManager(new ResourceClass(0, -5, 10, 0, 0,0, 0), null));
        cards[count] = tempCard;
        count++;

        return cards;
    }

    public static void initDeck(int size) {
        CardClass[] cards = new CardClass[size];
        CardClass temp;

        deck = cards;
    }

    public static CardClass[] dealCards(int deal) {
        CardClass[] cards = testCards(); // = new CardClass[deal];
        CardClass temp;

        mainActivity.DrawCard(cards[0]);

        for (int i = 0; i < deal; i++) {

        }

        return cards;
    }
}
