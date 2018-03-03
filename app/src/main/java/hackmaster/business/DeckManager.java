package hackmaster.business;

import hackmaster.persistence.CardsList;
import hackmaster.objects.CardClass;


public class DeckManager {
    private static CardClass[] deck = null;
    private static int nextIndex = 0;


    public DeckManager() {

    }

    public static void initDeck(int size) {
        deck = CardsList.presetCards();
        resetIndex();
    }

    public static CardClass[] dealCards(int deal) {
        CardClass[] cards = new CardClass[deal];

        for (int i = 0; i < deal; i++) {
            cards[i] = deck[nextIndex];
            updateIndex();
        }
        return cards;
    }

    private static void updateIndex() {
        nextIndex = (nextIndex + 1) % deck.length;
    }

    public static CardClass dealNextCard() {
        CardClass nextCard = deck[nextIndex];
        updateIndex();

        return nextCard;
    }

    public static CardClass getCardAt(int i){return deck[i];}
    public static int getSizeDeck() { return deck.length; }
    public static CardClass  [] getADeck() { return deck; }
    public static void setDeck(CardClass[] set) { deck = set; }
    public static void resetIndex() { nextIndex = 0; }
    public static int getNextIndex() { return nextIndex; }
}
