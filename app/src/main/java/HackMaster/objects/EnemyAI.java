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
            nextCard = worstCard(getCards());
        }
        else {
            nextCard = bestCard(playable);
            nextCard = getCardIndex(playable[nextCard].getID(), getCards());
        }

        return nextCard;
    }

    private int worstCard(CardClass[] playable) {
        int worstCard = -1;
        int worstCost = 0;

        for (int i = 0; i < playable.length; i++) {
            ResourceClass cardR = playable[i].getPlayerR();
            int testCost = cardR.getBotnet() + cardR.gethCoin() + cardR.getCpu() + cardR.getHealth();
            if (testCost <= worstCost) {
                worstCost = testCost;
                worstCard = i;
            }
        }

        return worstCard;
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

    //worthHeuristic just doesn't work
    //worth and total are always 0 so worth/total gives NaN
    //Right now i changed it to decide to discard/play the card with the largest total cost
    /*public double worthHeuristic(CardClass assess) {
        double worth = 0;
        double total = 0;

        double[] worthAndTotal;

        ResourceClass playerR = assess.getCardResource().getPlayerR();
        ResourceClass enemyR = assess.getCardResource().getEnemyR();

        worthAndTotal = deathIsNear(assess);
        worth += worthAndTotal[0];
        total += worthAndTotal[1];

        worthAndTotal = checkPlayedCard(assess);
        worth += worthAndTotal[0];
        total += worthAndTotal[1];

        // TODO account what cards you have to plan for future moves
        if (playerR.getBotnetRate() > 0 || playerR.getCpuRate() > 0 || playerR.gethCoinRate() > 0) {
            worth += 1.0;
            total++;
        }

        if(total <= 1) {
            if (assess.getType().equals("Attack")) {
                worth = .75;
                total = 1;
            }
        }
        return worth / total;
    }

    public double[] checkPlayedCard(CardClass assess) {
        double worth = 0;
        double total = 0;
        ResourceClass playerR = assess.getCardResource().getPlayerR();
        CardClass playedCard = GameManager.getPlayedCardOne();

        if (playedCard != null) {
            if (assess.getType().equals("Attack")) {
                if (playedCard.getType().equals("Upgrade") || playedCard.getType().equals("Defense")) {
                    worth += 1.0;
                    total++;
                }

                if (GameManager.getPlayer1Health() < .20 * GameManager.maxHealth) {
                    worth += 1.0;
                    total++;
                }
            }
            if (assess.getType().equals("Defense") || assess.getType().equals("Upgrade")) {
                if (playerR.gethCoinRate() > 0 || playerR.getBotnetRate() > 0 || playerR.getCpuRate() > 0) {
                    worth += 1.00;
                    total++;
                }
                if (playerR.gethCoin() > 0 || playerR.getBotnet() > 0 || playerR.getCpu() > 0) {
                    worth += .5;
                    total++;
                }
            }
        }

        double[] ret = { worth, total };
        return ret;
    }

    public double[] deathIsNear(CardClass assess) {
        double worth = 0;
        double total = 0;

        ResourceClass playerR = assess.getCardResource().getPlayerR();

        if (getHealth() < .20 * GameManager.maxHealth ||
                (GameManager.getPlayedCardOne() != null && GameManager.getPlayedCardOne().getType().equals("Attack"))) {
            if (playerR.getHealth() > 0) {
                worth += 1.0;
                total++;
            }
            if (assess.getType().equals("Defense")) {
                worth += .75;
                total++;
            }
            if (assess.getType().equals("Attack")) {
                worth += .25;
                total++;
            }
            if (assess.getType().equals("Upgrade")) {
                worth += .75;
                total++;
            }
        }

        double[] ret = { worth, total };

        return ret;
    }*/
}
