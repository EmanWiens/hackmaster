package hackMaster.businessTest;
import org.junit.Test;
import hackmaster20.business.GameManager;
import static org.junit.Assert.assertEquals;


public class GameManagerUnitTest {

    @Test
    public void SingleGame_isCorrect() {
        GameManager.setUpSingleGame();
        System.out.println(GameManager.getPlayer().getName());
        assertEquals("The name of  player should be", "HackerMan", GameManager.getPlayer().getName());
    }

}