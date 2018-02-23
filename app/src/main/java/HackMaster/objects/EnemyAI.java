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

        CardClass[] playable = playableCards(this);

        if (playable.length == 0)
            GameManager.discardCard(worstCard(), GameManager.getPlayer2());
        else
            nextCard = bestCard(playable);

        return nextCard;
    }

    private int worstCard() {
        return 0; // TODO function that finds the worst card
    }

    private int bestCard(CardClass[] playable) {
        return 0; // TODO choose the best card
    }

    public CardClass[] playableCards(PlayerClass player) {
        ArrayList<CardClass> playable = new ArrayList<CardClass>();

        for (int i = 0; i < GameManager.sizeOfHand; i++) {
            if (GameManager.checkCard(i, this))
                playable.add(player.getCard(i));
        }

        return playable.toArray(new CardClass[0]);
    }
}
