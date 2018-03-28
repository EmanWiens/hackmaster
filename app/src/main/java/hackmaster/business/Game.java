package hackmaster.business;

import hackmaster.objects.CardClass;
import hackmaster.objects.PlayerClass;
import hackmaster.objects.ResourceClass;
import hackmaster.presentation.Render;

public abstract class Game {
    public static final int hand = 5;

    private PlayerClass player1;
    private PlayerClass player2;

    private boolean player1Turn;
    private boolean paused;
    private boolean renderDelay;

    private static CardClass playedCardOne;
    private static CardClass playedCardTwo;

    private boolean discard;
    private boolean player1Won;

    public Game(PlayerClass p1, PlayerClass p2) {
        player1 = p1;
        player2 = p2;

        playedCardTwo = null;
        playedCardOne = null;

        player1Turn = true;
    }

    public boolean playCardEvent(int playerCard) {
        System.out.print("Error in game. Please try starting a new game.");
        return false;
    }

    public void playerTurn(int playerCard, PlayerClass player) {
        CardClass nextCard = DeckManager.dealNextCard();
        CardClass doNothing = new CardClass(-1, "Do Nothing", "Do Nothing", "Do Nothing", null, null);
        CardClass playedCard = player.getCard(playerCard);

        if (getDiscard()) {
            //ResourceManager.applyCard(player1Turn, player1, player2, doNothing);
            discardOff();
        } else {
            ResourceManager.applyCard(player1Turn, player1, player2, playedCard);
        }

        player.setCard(playerCard, nextCard);
    }

    public PlayerClass getCurrentPlayer() {
        if (player1Turn) {
            return player1;
        }
        else {
            return player2;
        }
    }

    public void discardCard(int playerCard, PlayerClass player) {
        CardClass nextCard = DeckManager.dealNextCard();
        player.setCard(playerCard, nextCard);
    }

    public static boolean checkCard(int playerCard, PlayerClass player) {
        boolean canPlay = true;
        CardClass card = player.getCard(playerCard);

        ResourceClass cardResource = card.getPlayerR();
        ResourceClass playerResource = player.getResources();

        if(playerResource.getHealth() + cardResource.getHealth() < 0 ||
                playerResource.gethCoin() + cardResource.gethCoin() < 0 ||
                playerResource.getBotnet() + cardResource.getBotnet() < 0 ||
                playerResource.getCpu() + cardResource.getCpu() < 0 ||
                playerResource.gethCoinRate() + cardResource.gethCoinRate() < 1 ||
                playerResource.getBotnetRate() + cardResource.getBotnetRate() < 1 ||
                playerResource.getCpuRate() + cardResource.getCpuRate() < 1) {
            canPlay = false;
        }
        return canPlay;
    }

    public boolean gameDone() {
        boolean result = false;
        if (player2.getHealth() < 1) {
            result = true;
            player1Won = true;
        }
        if (player1.getHealth() < 1) {
            result = true;
        }
        return result;
    }

    public int getPlayer1Health() {return player1.getHealth();}
    public int getPlayer2Health() {return player2.getHealth();}
    public boolean getRenderDelay() { return renderDelay; }
    public void setRenderDelay(boolean set) { renderDelay = set; }
    public CardClass getPlayedCardOne() { return playedCardOne; }
    public CardClass getPlayedCardTwo() { return playedCardTwo; }
    public void pauseGame() { paused = true; }
    public void unpauseGame() { paused = false; }
    public boolean gamePaused() { return paused; }
    public PlayerClass getPlayer1(){ return player1; }
    public PlayerClass getPlayer2(){ return player2; }
    public boolean getPlayer1Turn() { return player1Turn; }
    public void setPlayedCardOne(CardClass card) { playedCardOne = card; }
    public void setPlayedCardTwo(CardClass card) { playedCardTwo = card; }
    synchronized public void setPlayer1Turn(boolean turn) { player1Turn = turn; }
    public void setDeck(CardClass[] set) { DeckManager.setDeck(set); }
    public CardClass getDeckCardAt(int i) { return DeckManager.getCardAt(i); }
    public CardClass[] getDeck() { return DeckManager.getDeck(); }
    public ResourceClass getPlayer1Res() { return player1.getResources(); }
    public ResourceClass getPlayer2Res() { return player2.getResources(); }
    public void discardOn() {discard = true;}
    public void discardOff() {discard = false;}
    public boolean getDiscard() {return discard;}
    public void addHealthPlayer1(int health) {player1.addHealth(health);}
    public void addHealthPlayer2(int health) {player2.addHealth(health);}
    public boolean getPlayer1Won() { return player1Won; }
}