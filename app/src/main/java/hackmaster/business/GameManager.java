package hackmaster.business;

import hackmaster.objects.CardClass;
import hackmaster.objects.EnemyAI;
import hackmaster.presentation.DrawToScreen;
import hackmaster.objects.PlayerClass;
import hackmaster.objects.ResourceClass;
import hackmaster.objects.PlayerStatsSaves;

// Will manage the overall game that is in progress
// will hold the functions that are common to single and multiplayer game
public class GameManager implements GameInterface {
    private static PlayerStatsSaves pStats;
    private static PlayerClass player1;
    private static PlayerClass player2;
    private static DeckManager deckM;
    private static ResourceManager resManager;

    private static boolean player1Turn = true;
    private static boolean paused = false;
    private static boolean inGame = false;
    private static boolean singlePlayer = false;
    private static boolean delayAi = false;

    private static CardClass playedCard = null;
    private static CardClass playedCardAi = null;

    //Created boolean test since it fails at draw(Can't access presentation layer in tests)
    private static boolean test;

    public static final int sizeOfHand = 6;
    public static final int maxCards = 50;
    public static final int maxHealth = 100;

    private static DrawToScreen mainActivity;

    public GameManager(DrawToScreen mainAct) {
        mainActivity = mainAct;
        deckM = new DeckManager();
        pStats = new PlayerStatsSaves();
        resManager = new ResourceManager();
        test = false;
    }

    public static void setUpSingleGame() {
        singlePlayer = true;
        inGame = true;

        deckM.initDeck(maxCards);
        player1 = new PlayerClass(0,
                "HackerMan",
                new ResourceClass(100, 2, 2, 2, 2, 2, 2), deckM.dealCards(sizeOfHand));

        player2 = new EnemyAI(1,
                "Enemy Bot",
                new ResourceClass(100, 2, 2, 2, 2, 2, 2), deckM.dealCards(sizeOfHand));

        GameManager.render();
    }

    public static void playCardEvent(int playerCard) {
        if (player1Turn) {
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
        }
        GameManager.render();
    }

    public static boolean cantPlayCard(PlayerClass player) {
        for (int i = 0; i < player.cardsSize(); i++)
            if (checkCard(i, player))
                return false;
        return true;
    }

    private static void playerTurn(int playerCard, PlayerClass player) {
        CardClass nextCard = DeckManager.dealNextCard();
        CardClass playedCard = player.getCard(playerCard);
        ResourceManager.applyCard(player1Turn, player1, player2, playedCard);
        player.setCard(playerCard, nextCard);
    }

    public static void discardCard(int playerCard, PlayerClass player) {
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

    public static int getPlayerNum() {
        if(player1Turn)
            return 0;
        else
            return 1;
    }

    // TODO make this the function that everyone calls to update the screen
    public static void render() {
        if (!test) {
            mainActivity.drawPlayerResource(player1);
            mainActivity.drawPlayerResource(player2);

            if (playedCard != null)
                mainActivity.drawPlayedCard(playedCard, false);
            if (playedCardAi != null)
                mainActivity.drawPlayedCard(playedCardAi, true);

            if (player1Turn)
                for (int i = 0; i < player1.getCards().length; i++) {
                    if (player1.getCards()[i] != null)
                        mainActivity.DrawCard(player1.getCards()[i], i);
                }
            else
                for (int i = 0; i < player2.getCards().length; i++) {
                    if (player2.getCards()[i] != null)
                        mainActivity.DrawCard(player2.getCards()[i], i);
                }
        }
    }

    public static int getPlayer1Health() {
        if (inGame)
            return player1.getHealth();
        return -1;
    }
    public static int getPlayer2Health() {
        if (inGame)
            return player2.getHealth();
        return -1;
    }

    //test this (marc)
    public static void initStats() {
        pStats = new PlayerStatsSaves();
        pStats.setPlayerName("Pwn0gr4ph1c"); // change name later
        pStats.addWin(); // remove this
        pStats.addWin(); // remove this
    }


    //test this (marc)
    public static String getPlayerName() {
        return pStats.getName();
    }

    //test this (marc)
    public static int getWin() {
        return pStats.getWin();
    }

    public static void runAsTest() { test = true; }
    public static void setDelayAi(boolean b) { delayAi = b; }
    public static boolean getDelayAi() { return delayAi; }
    public static CardClass getPlayedCard() { return playedCard; }
    public static CardClass getPlayedCardAi() { return playedCardAi; }
    public static void setInGame(boolean value) { inGame = value; }
    public static void pauseGame() { paused = true; }
    public static void unpauseGame() { paused = false; }
    public static boolean gamePaused() { return paused; }
    public static void setSinglePlayer(boolean set) { singlePlayer = set; }
    public static int handSize() { return sizeOfHand; }
    public static DrawToScreen getMainAct() { return mainActivity; }
    public static boolean inGame() { return inGame; }
    public static PlayerClass getPlayer1(){ return player1; }
    public static PlayerClass getPlayer2(){ return player2; }
    public boolean getPlayer1Turn() { return player1Turn; }
    public static void setPlayer1Turn(boolean turn) { player1Turn = turn; }
    public static void setDeck(CardClass[] set) { deckM.setDeck(set); }
    public static CardClass getDeckCardAt(int i) { return deckM.getCardAt(i); }
    public static int getDeckMangerDealNextCard() { return deckM.getNextIndex(); }
}
