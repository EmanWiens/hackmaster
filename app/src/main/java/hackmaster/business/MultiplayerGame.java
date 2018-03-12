package hackmaster.business;

import hackmaster.objects.PlayerClass;

public class MultiplayerGame extends Game {
    public MultiplayerGame(PlayerClass p1, PlayerClass p2) {
        super(p1,p2);
    }

    public void playCardEvent(int playerCard) {
        if (getPlayer1Turn() && !gamePaused()) {
            if(checkCard(playerCard, getPlayer1())|| getDiscard()){
                setPlayedCardOne(getPlayer1().getCard(playerCard));
                playerTurn(playerCard, getPlayer1());
                ResourceManager.applyTurnRate(getPlayer2());
                setPlayer1Turn(false);
                discardOff();
            }
        }
        else if (!gamePaused()) {
            if(checkCard(playerCard, getPlayer2())|| getDiscard()){
                setPlayedCardTwo(getPlayer2().getCard(playerCard));
                playerTurn(playerCard, getPlayer2());
                ResourceManager.applyTurnRate(getPlayer1());
                setPlayer1Turn(true);
                discardOff();
            }
        }
    }
}
