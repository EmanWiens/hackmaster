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
    private static int index = 0; // shuffle through the deck sequentially

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
        index = (index + 1) % deck.length;
    }

    public static int getCardIndex(String name, CardClass[] hand) {
        // TODO write the function that finds the card by comparing the name and returns the index
        //TODO Comment We have a lot of the same card names Maybe check by something unique like cardID?
        //TODO Just seen you already made ID For Cards=) I will just make them unique
        //TODO But still if you wanna search by name there are multiple cards with the same name. So which index we want from them?
        //TODO We could also make the cardID correspond to index
        //DONE
        // I can't figure out how to get an item out of View (in MainView)
        // might do marc's thing where we shuffle the cards down one and the newest card is
        // always on the right (or left) of the screen
        int index=0;
        for (int i = 0; i < hand.length; i++)
            if (name.equals(hand[i].getName()))
                index=i;

        return index;
    }

    public static CardClass getCardAt(int index){
        return deck[index];
    }

    public static CardClass dealNextCard() {
        updateIndex();
        return deck[index];
    }

    public static int getSizeDeck() { return deck.length; }
}
