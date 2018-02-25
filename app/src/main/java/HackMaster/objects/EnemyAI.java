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
        int bestCard = -1;
        double bestCost = 0;

        for (int i = 0; i < playable.length; i++) {
            if (worthHeuristic(playable[i]) >= bestCost) {
                bestCost = worthHeuristic(playable[i]);
                bestCard = i;
            }
        }

        return bestCard;
    }

    public double worthHeuristic(CardClass assess) {
        double worth = 0;
        double total = 0;

        ResourceClass playerR = assess.getCardResource().getPlayerR();
        ResourceClass enemyR = assess.getCardResource().getEnemyR();

        // death is imminent
        if (getHealth() < .20 * GameManager.maxHealth ||
                (GameManager.getPlayedCard() != null && GameManager.getPlayedCard().getType().equals("Attack"))) {
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
        // increase rate overall
        if (playerR.getBotnetRate() > 0 || playerR.getCpuRate() > 0 || playerR.gethCoinRate() > 0) {
            worth += 1.0;
            total++;
        }
        // look at played card
        if (GameManager.getPlayedCard() != null) {
            if (assess.getType().equals("Attack")) {
                if (playerR.gethCoinRate() > 0 || playerR.getBotnetRate() > 0 || playerR.getCpuRate() > 0) {
                    worth += 1.0;
                    total++;
                }
            }
            if (assess.getType().equals("Defense") || assess.getType().equals("Upgrade")) {
                if (playerR.gethCoin() > 0 || playerR.getBotnet() > 0 || playerR.getCpu() > 0) {
                    worth += .75;
                    total++;
                }
            }
        }

        return worth / total;
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
