package hackmaster.business;

import java.util.ArrayList;

import hackmaster.application.Services;
import hackmaster.persistence.CardDataAccessInterface;
import hackmaster.objects.CardClass;


public abstract class DeckManager {
    private static int nextIndex = 0;
    private static CardClass[] deck = null;
    private static CardDataAccessInterface cardDataAccess;

    abstract void methodToOverride(); // TODO example of a method to be overriden by subclass

    public static void initDeck(int size) {
        cardDataAccess = Services.getCardDataAccess();
        ArrayList<CardClass>listDeck = new ArrayList<>(size);
        String emsg = cardDataAccess.getCardSequential(listDeck);
        deck = listDeck.toArray(new CardClass[0]);
        shuffleDeck();
        resetIndex();
    }

    // Knuth shuffle
    // TODO RUN TEST WITH SEED -JANSEN
    public static void shuffleDeck(){
        CardClass temp;
        int pick;
        int n = deck.length;
        for(int i = n - 1; i>=0; i--){
            pick = (int) Math.floor((i+1)*Math.random());
            temp = deck[i];
            deck[i] = deck[pick];
            deck[pick] = temp;
        }
    }

    public static CardClass[] dealFirstHandOfGame() {
        CardClass[] cards = new CardClass[Game.hand];

        for (int i = 0; i < Game.hand; i++) {
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
