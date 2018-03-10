package hackmaster.business;

import hackmaster.objects.EnemyAI;
import hackmaster.objects.PlayerClass;
import hackmaster.objects.ResourceClass;

public abstract class SetUpGame {
    public static GameInterface setUpSinglePlayerGame(int deck) {
        GameInterface game = new SinglePlayerGame();

        // TODO call deck manager for deck setup
            DeckManager.initDeck(deck);
            PlayerClass player1 = new PlayerClass(0,
                    "HackerMan",
                    new ResourceClass(100, 10, 2, 10, 2, 10, 2), DeckManager.dealFirstHandOfGame());

            PlayerClass player2 = new EnemyAI(1,
                    "Enemy Bot",
                    new ResourceClass(100, 10, 2, 10, 2, 10, 2), DeckManager.dealFirstHandOfGame());

        return game;
    }

    public static MultiplayerGame setUpMultiplayerGame() {
        return null; // TODO write the setup function that returnds a GameInterface
    }

    public static void destroyGameInSession() {
        // TODO function that destructs current game
    }
}
