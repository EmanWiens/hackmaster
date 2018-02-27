package HackMaster.business;

import HackMaster.objects.CardClass;
import HackMaster.objects.EnemyAI;
import HackMaster.presentation.DrawToScreen;
import HackMaster.objects.PlayerClass;
import HackMaster.objects.ResourceClass;
import HackMaster.objects.PlayerStatsSaves;
import android.os.Handler; // DELAY

public class GameManager {
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
    private static boolean test = true;

    public static final int sizeOfHand = 6;
    public static final int maxCards = 50;
    public static final int maxHealth = 100;

    private static DrawToScreen mainActivity;

    public GameManager(DrawToScreen mainAct) {
        mainActivity = mainAct;
        deckM = new DeckManager(mainAct);
        pStats = new PlayerStatsSaves();
        resManager = new ResourceManager(mainAct);
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
        // if (!test) {

            GameManager.drawCurrentGame();
        // }
    }

    public static void playCardEvent(int playerCard) {
        // Handler handler = new Handler(); // DELAY
        if (player1Turn) {
            if(checkCard(playerCard, player1)){
                // if (!test)
                    // mainActivity.drawPlayedCard(player1.getCard(playerCard), );
                playedCard = player1.getCard(playerCard);
                playerTurn(playerCard, player1);
                resManager.applyTurnRate(player2, test);
                player1Turn = false;

                if (singlePlayer) {
                    int enemyCard = ((EnemyAI) player2).playNextCard();
                    //if (!test)
                      //   handler.postDelayed(delayDraw(enemyCard), 2000); // DELAY
                    playedCardAi = player2.getCard(enemyCard);

                    if (enemyCard != -1)
                        playerTurn(enemyCard, player2);
                    resManager.applyTurnRate(player1, test);
                    player1Turn = true;
                }
            }
            else {
                if (cantPlayCard(player1)) {
                    // TODO put the player in a discard mode
                }
            }
        }

        GameManager.drawCurrentGame();
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
        ResourceManager.applyCard(player1Turn, player1, player2, playedCard,test);

        player.setCard(playerCard, nextCard);

        // if (!test && singlePlayer && player1Turn)
           // mainActivity.DrawCard(nextCard, playerCard);
        GameManager.drawCurrentGame();
    }

    public static void discardCard(int playerCard, PlayerClass player) {
        CardClass nextCard = DeckManager.dealNextCard();
        player.setCard(playerCard, nextCard);

        GameManager.drawCurrentGame();
    }

    public static boolean checkCard(int playerCard, PlayerClass player) {
        boolean canPlay = true;
        CardClass card = player.getCard(playerCard);

        ResourceClass cardResource = card.getCardResource().getPlayerR();
        ResourceClass playerResource = player.getResources();

        if(playerResource.getHealth() + cardResource.getHealth() < 0)
            canPlay = false;
        if(playerResource.gethCoin() + cardResource.gethCoin() < 0)
            canPlay = false;
        if(playerResource.getBotnet() + cardResource.getBotnet() < 0)
            canPlay = false;
        if(playerResource.getCpu() + cardResource.getCpu() < 0)
            canPlay = false;

        if(playerResource.gethCoin() + cardResource.gethCoinRate() < 0)
            canPlay = false;
        if(playerResource.getBotnet() + cardResource.getBotnetRate() < 0)
            canPlay = false;
        if(playerResource.getCpu() + cardResource.getCpuRate() < 0)
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
    public static void drawCurrentGame() {
        if (!test) {
            mainActivity.drawPlayerResource(player1);
            mainActivity.drawPlayerResource(player2);

            if (playedCard != null)
                mainActivity.drawPlayedCard(playedCard, false);
            if (playedCardAi != null)
                mainActivity.drawPlayedCard(playedCardAi, true);

            if (player1Turn)
                deckM.paintCard(player1.getCards());
            else
                deckM.paintCard(player2.getCards());
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
