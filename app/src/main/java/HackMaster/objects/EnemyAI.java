package HackMaster.objects;

import java.util.ArrayList;

import HackMaster.business.DeckManager;
import HackMaster.business.GameManager;

public class EnemyAI extends PlayerClass {
    private int nextCard;

    public EnemyAI(int id, String n, ResourceClass r, CardClass[] c) {
        super(id, n, r, c);
    }

    public int playNextCard() {
        nextCard = -1;

        CardClass[] playable = playableCards();

        if (playable.length == 0) {
            int temp = worstCard(playable);

            if (temp == -1)
                temp = 0;
            temp = DeckManager.getCardIndex(playable[temp].getName(), getCards());
            GameManager.discardCard(temp, GameManager.getPlayer2());
        }
        else {
            nextCard = bestCard(playable);

            if (nextCard == -1)
                nextCard = 0;

            nextCard = DeckManager.getCardIndex(playable[nextCard].getName(), getCards());
        }

        return nextCard;
    }

    private int worstCard(CardClass[] playable) {
        int worstCard = -1;
        double worstCost = 0;

        for (int i = 0; i < playable.length; i++) {
            if (1 - worthHeuristic(playable[i]) <= worstCost) {
                worstCost = 1 - worthHeuristic(playable[i]);
                worstCard = i;
            }
        }

        return worstCard;
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
        CardClass playedCard = GameManager.getPlayedCard();

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

        double[] ret = { worth, total };

        return ret;
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
