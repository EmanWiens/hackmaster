package hackmaster.business;

import hackmaster.objects.EnemyAI;
import hackmaster.objects.PlayerClass;
import hackmaster.objects.ResourceClass;

public abstract class SetUpGame {
    public GameInterface setUpSinglePlayerGame(int deck) {
        GameInterface game;

        // TODO call deck manager for deck setup
            DeckManager.initDeck();
            PlayerClass player1 = new PlayerClass(0,
                    "HackerMan",
                    new ResourceClass(100, 10, 2, 10, 2, 10, 2), DeckManager.dealFirstHandOfGame());

            PlayerClass player2 = new EnemyAI(1,
                    "Enemy Bot",
                    new ResourceClass(100, 10, 2, 10, 2, 10, 2), DeckManager.dealFirstHandOfGame());

        return new SinglePlayerGame(player1, player2);
    }

    public GameInterface setUpMultiplayerGame() {
        GameInterface game;

        // TODO call deck manager for deck setup
        DeckManager.initDeck();
        PlayerClass player1 = new PlayerClass(0,
                "HackerMan",
                new ResourceClass(100, 10, 2, 10, 2, 10, 2), DeckManager.dealFirstHandOfGame());

        PlayerClass player2 = new PlayerClass(1,
                "Enemy Bot",
                new ResourceClass(100, 10, 2, 10, 2, 10, 2), DeckManager.dealFirstHandOfGame());

        return new MultiplayerGame(player1, player2);
    }

    public void destroyGameInSession() {
        // TODO function that destructs current game
    }
}
