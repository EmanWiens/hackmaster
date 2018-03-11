package hackmaster.business;

import hackmaster.objects.PlayerClass;

public class MultiplayerGame implements GameInterface {
    PlayerClass player1, player2;

    public MultiplayerGame(PlayerClass p1, PlayerClass p2) {
        player1 = p1;
        player2 = p2;
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
