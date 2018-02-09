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
    private static int nextIndex = 0; // shuffle through the deck sequentially

    private static DrawToScreen mainActivity;

    public DeckManager(DrawToScreen mainAct) {
        mainActivity = mainAct;
    }

    public static void initDeck(int size) {
        // CardClass[] cards = new CardClass[size];
        // CardClass temp;

        // deck = cards;
        deck = CardsList.presetCards();
    }

    public static CardClass[] dealCards(int deal) {
        CardClass[] cards = new CardClass[deal];

        for (int i = 0; i < deal; i++)
            cards[i] = deck[i];

        return cards;
    }

    public static void paintCard(CardClass[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null)
                mainActivity.DrawCard(list[i], i);
        }
    }

    private static void updateIndex() {
        nextIndex = (nextIndex + 1) % deck.length;
    }

    public static int getCardIndex(String name, CardClass[] hand) {
        int j=-1;
        for (int i = 0; i < hand.length; i++)
            if (name.equals(hand[i].getName()))
                j=i;

        return j;
    }

    public static CardClass getCardAt(int i){
        return deck[i];
    }

    public static CardClass dealNextCard() {
        updateIndex();
        return deck[nextIndex];
    }

    public static int getSizeDeck() { return deck.length; }
}
