package hackmaster20.objects;

/**
 * Created by Owner on 2/2/2018.
 */

public class EnemyAI extends PlayerClass {
    public EnemyAI(int id, String n, ResourceClass r, CardClass[] c) {
        super(id, n, r, c);
    }

    private int nextCard = 0;

    public int playNextCard() {
        updateNextCard();
        return nextCard;
    }

    private void updateNextCard() {
        nextCard = (nextCard + 1) % cardsSize();
    }
}
