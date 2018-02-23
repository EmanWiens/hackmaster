package HackMaster.objects;

import java.util.ArrayList;

import HackMaster.business.GameManager;

public class EnemyAI extends PlayerClass {
    private int nextCard;

    public EnemyAI(int id, String n, ResourceClass r, CardClass[] c) {
        super(id, n, r, c);
    }

    public int playNextCard() {
        nextCard = -1;

        CardClass[] playable = playableCards();

        if (playable.length == 0)
            GameManager.discardCard(worstCard(), GameManager.getPlayer2());
        else
            nextCard = bestCard(playable);

        return nextCard;
    }

    private int worstCard() {
        // Find the best card in the hand based on resources and other cards

        return 0; // TODO function that finds the worst card
    }

    private int bestCard(CardClass[] playable) {
        return 0; // TODO choose the best card
    }

    public CardClass[] playableCards() {
        ArrayList<CardClass> playable = new ArrayList<CardClass>();

        for (int i = 0; i < cardsSize(); i++) {
            if (GameManager.checkCard(i, this))
                playable.add(getCard(i));
        }

        return playable.toArray(new CardClass[0]);
    }
}
