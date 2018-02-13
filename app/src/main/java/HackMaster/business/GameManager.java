package HackMaster.business;

import HackMaster.objects.CardClass;
import HackMaster.objects.EnemyAI;
import HackMaster.presentation.DrawToScreen;
import HackMaster.objects.PlayerClass;
import HackMaster.objects.ResourceClass;
import HackMaster.objects.PlayerStatsSaves;

/**
 * Created by Owner on 1/29/2018.
 */

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

    public static final int dealCards = 6;
    public static final int maxCards = 50;

    private static DrawToScreen mainActivity;

    public GameManager(DrawToScreen mainAct) {
        mainActivity = mainAct;
        deckM = new DeckManager(mainAct);
        pStats = new PlayerStatsSaves();
        resManager = new ResourceManager(mainAct);
    }
//Created boolean test since it fails at draw(Can't access presentation layer in tests)
    public static void setUpSingleGame(boolean test) {
        singlePlayer = true;
        inGame = true;

        deckM.initDeck(maxCards);
        player1 = new PlayerClass(0,
                "HackerMan",
                new ResourceClass(100, 2, 2, 2, 2, 2, 2), deckM.dealCards(dealCards));

        player2 = new EnemyAI(1,
                "Enemy Bot",
                new ResourceClass(100, 2, 2, 2, 2, 2, 2), deckM.dealCards(dealCards));
        if (!test) {
            deckM.paintCard(player1.getCards());
            resManager.drawPlayerResource(player1);
            resManager.drawPlayerResource(player2);
        }
    }

    public static void playCardEvent(int playerCard, boolean test) {
        if (player1Turn) {
            if(checkCard(playerCard, player1)){
                if (!test)
                    mainActivity.drawPlayedCard(player1.getCard(playerCard));
                playerTurn(playerCard, player1, test);
                resManager.applyTurnRate(player2, test);
                player1Turn = false;

                if (singlePlayer) {
                    int enemyCard = ((EnemyAI) player2).playNextCard();
                    if (!test)
                        mainActivity.drawPlayedCard(player2.getCard(enemyCard));
                    playerTurn(enemyCard, player2, test);
                    resManager.applyTurnRate(player1, test);
                    player1Turn = true;
                }
            }
        }
        else {
            // playerTurn(name, player2);
        }
    }

    private static void playerTurn(int playerCard, PlayerClass player, boolean test) {
        CardClass nextCard = DeckManager.dealNextCard();
        CardClass playedCard = player.getCard(playerCard);
        ResourceManager.applyCard(player1Turn, player1, player2, playedCard,test);

        player.setCard(playerCard, nextCard);

        if (!test && singlePlayer && player1Turn)
            mainActivity.DrawCard(nextCard, playerCard);
    }

    // Discards a card from the players hand if he/she cannot play any of them
    private static void discardCard(int playerCard, PlayerClass player) {
        CardClass nextCard = DeckManager.dealNextCard();
        player.setCard(playerCard, nextCard);
    }

    // Checks if the card at int slot is possible to play
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

        return canPlay;
    }

    public static int getPlayerNum() {
        if(player1Turn)
            return 0;
        else
            return 1;
    }

    public static void drawCurrentGame() {
        mainActivity.drawPlayerResource(player1);
        mainActivity.drawPlayerResource(player2);

        if (player1Turn)
            deckM.paintCard(player1.getCards());
        else
            deckM.paintCard(player2.getCards());
    }

    public static void setInGame(boolean value) { inGame = value; }
    public static void pauseGame() { paused = true; }
    public static void unpauseGame() { paused = false; }
    public static boolean gamePaused() { return paused; }
    public static void setSinglePlayer(boolean set) { singlePlayer = set; }
    public static int handSize() { return dealCards; }
    public static DrawToScreen getMainAct() { return mainActivity; }
    public static boolean inGame() { return inGame; }
    public static PlayerClass getPlayer1(){ return player1; }
    public static PlayerClass getPlayer2(){ return player2; }
    public boolean getPlayer1Turn() { return player1Turn; }
}
