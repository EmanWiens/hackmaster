package hackmaster.business;

import hackmaster.objects.EnemyAI;
import hackmaster.objects.PlayerClass;

public class SinglePlayerGame extends Game {
    public SinglePlayerGame(PlayerClass p1, PlayerClass ai) {
        super(p1,ai);
    }

    public void playCardEvent(int playerCard) {
        if (getPlayer1Turn() && !gamePaused() && !getRenderDelay()) {
            if(checkCard(playerCard, getPlayer1()) || getDiscard()) {
                setPlayedCardOne(getPlayer1().getCard(playerCard));
                playerTurn(playerCard, getPlayer1());
                ResourceManager.applyTurnRate(getPlayer2());
                setPlayer1Turn(false);

                // AI turn
                int enemyCard = ((EnemyAI) getPlayer2()).playNextCard();
                setPlayedCardTwo(getPlayer2().getCard(enemyCard));
                if (checkCard(enemyCard, getPlayer1())) {
                    playerTurn(enemyCard, getPlayer2());
                } else {
                    discardCard(enemyCard, getPlayer2());
                }
                ResourceManager.applyTurnRate(getPlayer1());
                setPlayer1Turn(true);
            }
        }
    }
}
