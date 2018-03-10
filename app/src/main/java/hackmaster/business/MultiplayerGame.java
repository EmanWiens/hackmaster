package hackmaster.business;

import hackmaster.objects.PlayerClass;

public class MultiplayerGame extends Game {
    public MultiplayerGame(PlayerClass p1, PlayerClass p2) {
        super(p1,p2);
    }

    public void playCardEvent(int playerCard) {
        /*if (player1Turn && !paused) {
            if(checkCard(playerCard, player1)){
                playedCard = player1.getCard(playerCard);
                playerTurn(playerCard, player1);
                resManager.applyTurnRate(player2);
                player1Turn = false;
            }
        }
        else if (!paused) {

        }*/
    }
}
