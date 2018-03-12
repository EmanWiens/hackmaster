package hackmaster.business;

import hackmaster.objects.EnemyAI;
import hackmaster.objects.PlayerClass;
import hackmaster.objects.ResourceClass;

public abstract class SetUpGame {
    private static final ResourceClass startOfGameResources = new ResourceClass(100, 10, 2, 10, 2, 10, 2);

    public static Game setUpSinglePlayerGame() {
        DeckManager.initDeck();

        PlayerClass player1 = new PlayerClass(0,
                "HackerMan",
                startOfGameResources.clone(), DeckManager.dealFirstHandOfGame());

        PlayerClass player2 = new EnemyAI(1,
                "Enemy Bot",
                startOfGameResources.clone(), DeckManager.dealFirstHandOfGame());

        return new SinglePlayerGame(player1, player2);
    }

    public static Game setUpMultiplayerGame() {
        DeckManager.initDeck();

        PlayerClass player1 = new PlayerClass(0,
                "HackerMan",
                startOfGameResources.clone(), DeckManager.dealFirstHandOfGame());

        PlayerClass player2 = new PlayerClass(1,
                "HackerMan-2nd",
                startOfGameResources.clone(), DeckManager.dealFirstHandOfGame());

        return new MultiplayerGame(player1, player2);
    }

    public static final ResourceClass startOfGameResources() { return startOfGameResources.clone(); }
}
