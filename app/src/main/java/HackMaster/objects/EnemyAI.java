package hackmaster.objects;

public class EnemyAI extends PlayerClass {
    private int nextCard;

    public EnemyAI(int id, String n, ResourceClass r, CardClass[] c) {
        super(id, n, r, c);
    }

    public int playNextCard() {
        if (nextCard!=0) {
            nextCard = -1;
        }
        CardClass[] playable = playableCards();

        if (playable.length == 0) {
            nextCard = bestCard(getCards());
        }
        else {
            nextCard = bestCard(playable);
            nextCard = getCardIndex(playable[nextCard].getID(), getCards());
        }

        return nextCard;
    }

    private int bestCard(CardClass[] playable) {
        int bestCard = -1;
        int bestCost = 10000;

        for (int i = 0; i < playable.length; i++) {
            ResourceClass cardR = playable[i].getPlayerR();
            int testCost = cardR.getBotnet() + cardR.gethCoin() + cardR.getCpu() + cardR.getHealth();
            if (testCost <= bestCost) {
                bestCost = testCost;
                bestCard = i;
            }
        }

        return bestCard;
    }
}
