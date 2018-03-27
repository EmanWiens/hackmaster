package hackmaster.business;

import hackmaster.application.Services;
import hackmaster.application.AppController;
import hackmaster.persistence.CardDataAccessInterface;
import hackmaster.objects.CardClass;


public abstract class DeckManager {
    private static int nextIndex = 0;
    private static CardClass[] deck = null;
    private static CardDataAccessInterface cardDataAccess;

    public static void initDeck() {
        cardDataAccess = Services.getCardDataAccess();

        AppController.initDeck(cardDataAccess);
    }

    public static CardClass[] dealFirstHandOfGame() {
        CardClass[] cards = new CardClass[Game.hand];

        for (int i = 0; i < Game.hand; i++) {
            cards[i] = deck[nextIndex];
            updateIndex();
        }
        return cards;
    }

    public static CardClass dealNextCard() {
        CardClass nextCard = deck[nextIndex];
        updateIndex();

        return nextCard;
    }

    private static void updateIndex() {
        nextIndex = (nextIndex + 1) % deck.length;
    }
    public static CardClass getCardAt(int i){return deck[i];}
    public static int getSizeDeck() { return deck.length; }
    public static CardClass[] getADeck() { return deck; }
    public static void setDeck(CardClass[] set) { deck = set; }
    public static void resetIndex() { nextIndex = 0; }
    public static int getNextIndex() { return nextIndex; }
    public static void setIndex(int i) {nextIndex = i;}
    public static CardClass[] getDeck() { return deck; }
}
