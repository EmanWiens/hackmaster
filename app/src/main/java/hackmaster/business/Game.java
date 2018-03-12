package hackmaster.business;

import hackmaster.objects.CardClass;
import hackmaster.objects.PlayerClass;
import hackmaster.objects.PlayerStatsSaves;
import hackmaster.objects.ResourceClass;

public abstract class Game {
    public static final int hand = 5;

    private PlayerStatsSaves pStats;
    private PlayerClass player1;
    private PlayerClass player2;

    private boolean player1Turn;
    private boolean paused;
    private boolean renderDelay;

    private static CardClass playedCardOne;
    private static CardClass playedCardTwo;

    private boolean discard;

    public Game(PlayerClass p1, PlayerClass p2) {
        player1 = p1;
        player2 = p2;
        initStats();

        playedCardTwo = null;
        playedCardOne = null;

        player1Turn = true;
    }

    public void playCardEvent(int playerCard) { System.out.print("Error in game. Please try starting a new game."); }

    public void playerTurn(int playerCard, PlayerClass player) {
        CardClass nextCard = DeckManager.dealNextCard();
        CardClass doNothing = new CardClass(-1, "Do Nothing", "Do Nothing", "Do Nothing", null, null);
        CardClass playedCard = player.getCard(playerCard);

        if (getDiscard()) {
            ResourceManager.applyCard(player1Turn, player1, player2, doNothing);
            discardOff();
        } else {
            ResourceManager.applyCard(player1Turn, player1, player2, playedCard);
        }

        player.setCard(playerCard, nextCard);
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

        if(playerResource.getHealth() + cardResource.getHealth() < 0)
            canPlay = false;
        if(playerResource.gethCoin() + cardResource.gethCoin() < 0)
            canPlay = false;
        if(playerResource.getBotnet() + cardResource.getBotnet() < 0)
            canPlay = false;
        if(playerResource.getCpu() + cardResource.getCpu() < 0)
            canPlay = false;

        if(playerResource.gethCoin() + cardResource.gethCoinRate() < 1)
            canPlay = false;
        if(playerResource.getBotnet() + cardResource.getBotnetRate() < 1)
            canPlay = false;
        if(playerResource.getCpu() + cardResource.getCpuRate() < 1)
            canPlay = false;

        return canPlay;
    }

    //test this (marc)
    public boolean gameDone() {
        boolean result = false;
        if (player2.getHealth() < 1) {
            result = true;
        }
        if (player1.getHealth() < 1) {
            result = true;
        }
        return result;
    }

    public void initStats() {
        if(pStats == null) {
            pStats = new PlayerStatsSaves();
        }

        pStats.setPlayerName("Player_1");
    }

    public int getPlayer1Health() {return player1.getHealth();}
    public int getPlayer2Health() {return player2.getHealth();}
    public String getPlayerName() {return pStats.getName();}
    public int getWin() {return pStats.getWin();}
    public void addWin() { pStats.addWin();}
    public void addLoss() { pStats.addLoss();}
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
}
