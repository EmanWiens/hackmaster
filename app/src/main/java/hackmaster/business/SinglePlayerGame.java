package hackmaster.business;

import hackmaster.objects.CardClass;
import hackmaster.objects.EnemyAI;
import hackmaster.objects.PlayerClass;
import hackmaster.objects.ResourceClass;

public class SinglePlayerGame extends Game {
    public SinglePlayerGame(PlayerClass p1, PlayerClass ai) {
        super(p1,ai);
    }

    public void playCardEvent(int playerCard) {
        /*if (player1Turn && !paused && !delayAi) {
            if(checkCard(playerCard, player1)){
                playedCard = player1.getCard(playerCard);
                playerTurn(playerCard, player1);
                resManager.applyTurnRate(player2);
                player1Turn = false;

                if (singlePlayer) {
                    int enemyCard = ((EnemyAI) player2).playNextCard();
                    playedCardAi = player2.getCard(enemyCard);
                    if (checkCard(enemyCard, player1))
                        playerTurn(enemyCard, player2);
                    else
                        discardCard(enemyCard, player2);
                    resManager.applyTurnRate(player1);
                    player1Turn = true;
                }
            }
        }*/
    }
}
