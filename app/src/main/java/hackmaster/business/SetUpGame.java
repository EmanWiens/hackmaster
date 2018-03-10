package hackmaster.business;

import hackmaster.objects.EnemyAI;
import hackmaster.objects.PlayerClass;

public abstract class SetUpGame {
    public GameInterface setUpSinglePlayerGame() {
        GameInterface game = new SinglePlayerGame();

        // TODO call deck manager for deck setup
         /*   deckM.initDeck(maxCards);
            player1 = new PlayerClass(0,
                    "HackerMan",
                    new ResourceClass(100, 10, 2, 10, 2, 10, 2), deckM.dealCards(sizeOfHand));

            player2 = new EnemyAI(1,
                    "Enemy Bot",
                    new ResourceClass(100, 10, 2, 10, 2, 10, 2), deckM.dealCards(sizeOfHand));
    */
        return game;
    }

    public MultiplayerGame setUpMultiplayerGame() {
        return null; // TODO write the setup function that returnds a GameInterface
    }

    public void destroyGameInSession() {
        // TODO function that destructs current game
    }
}
