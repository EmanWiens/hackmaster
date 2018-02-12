package HackMaster.objects;

public class EnemyAI extends PlayerClass {
    private int nextCard = 0;

    public EnemyAI(int id, String n, ResourceClass r, CardClass[] c) {
        super(id, n, r, c);
    }

    public int playNextCard() {
        updateNextCard();
        return nextCard;
    }

    private void updateNextCard() {
        nextCard = (nextCard + 1) % cardsSize();
    }
}
