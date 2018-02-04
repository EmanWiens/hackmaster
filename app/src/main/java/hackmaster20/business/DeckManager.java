package hackmaster20.business;

import hackmaster20.presentation.DrawToScreen;
import hackmaster20.objects.CardClass;
import hackmaster20.persistence.CardsList;

/**
 * Created by Owner on 1/29/2018.
 */

public class DeckManager {
    private static CardClass[] deck = null;

    private static DrawToScreen mainActivity;
    public DeckManager(DrawToScreen mainAct) {
        mainActivity = mainAct;
    }

    // create a deck for the whole game
    public static void initDeck(int size) {
        CardClass[] cards = new CardClass[size];
        CardClass temp;

        deck = cards;
    }

    public static CardClass[] dealCards(int deal) {
        CardClass[] cards = CardsList.presetCards(); // = new CardClass[deal];
        CardClass temp;

        // TODO hand out random card
        for (int i = 0; i < deal; i++) {
            mainActivity.DrawCard(cards[i], i);
        }

        return cards;
    }

    public static int getCardIndex(String name, CardClass[] hand) {
        // TODO write the function that finds the card by comparinf the name and returns the index
        return 0;
    }

    public static CardClass getACard() {
        // TODO return a card to whomever played the card based on what cards have been played
        return null;
    }

    public static int getSizeDeck() {
        return deck.length;
    }
}
