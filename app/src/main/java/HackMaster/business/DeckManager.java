package HackMaster.business;

import HackMaster.persistence.CardsList;
import HackMaster.presentation.DrawToScreen;
import HackMaster.objects.CardClass;


public class DeckManager {
    private static CardClass[] deck = null;
    private static int nextIndex = 0;

    private static DrawToScreen mainActivity;

    public DeckManager(DrawToScreen mainAct) {
        mainActivity = mainAct;
    }

    public static void initDeck(int size) {
        deck = CardsList.presetCards();
    }

    public static CardClass[] dealCards(int deal) {
        CardClass[] cards = new CardClass[deal];

        for (int i = 0; i < deal; i++) {
            cards[i] = deck[nextIndex];
            updateIndex();
        }
        return cards;
    }

    public static void paintCard(CardClass[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null)
                mainActivity.DrawCard(list[i], i);
        }
    }

    public static int getCardIndex(String name, CardClass[] hand) {
        int j=-1;
        for (int i = 0; i < hand.length; i++)
            if (name.equals(hand[i].getName()))
                j=i;
        return j;
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
}
