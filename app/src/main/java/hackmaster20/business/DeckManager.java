package hackmaster20.business;

import java.util.Random;

import hackmaster20.presentation.DrawToScreen;
import hackmaster20.objects.CardClass;

/**
 * Created by Owner on 1/29/2018.
 */

public class DeckManager {
    private static CardClass[] deck = null;
    private static Random rand = new Random();
    private static int index = 0;

    private static DrawToScreen mainActivity;
    public DeckManager(DrawToScreen mainAct) {
        mainActivity = mainAct;
    }

    // create a deck for the whole game
    public static void initDeck(int size) {
        // CardClass[] cards = new CardClass[size];
        // CardClass temp;

        // deck = cards;
        deck = CardsList.presetCards();
    }

    public static CardClass[] dealCards(int deal) {
        CardClass[] cards = new CardClass[deal];

        for (int i = 0; i < deal; i++) {
            cards[i] = deck[i];
        }

        return cards;
    }

    public static void drawCards(CardClass[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null)
                mainActivity.DrawCard(list[i], i);
        }
    }

    private static void updateIndex() {
        index = (index + 1) % deck.length;
    }

    public static int getCardIndex(String name, CardClass[] hand) {
        // TODO write the function that finds the card by comparinf the name and returns the index
        return 0;
    }

    public static CardClass getACard() {
        updateIndex();
        return deck[index];
    }

    public static int getSizeDeck() {
        return deck.length;
    }
}
