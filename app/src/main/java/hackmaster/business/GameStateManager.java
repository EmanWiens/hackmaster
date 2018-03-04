package hackmaster.business;

/**
 * Created by Owner on 3/3/2018.
 */

// This will hold all the Game state functions
    // like paint and stuffs This is more of an overachring interface to give an understanding of
    // the game states. This class will be aware of the games running and which games to terminate/pause
public class GameStateManager implements GameState {
    GameManager game;
    public GameStateManager() {

    }
}
