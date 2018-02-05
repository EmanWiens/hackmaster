package hackMaster.businessTest;

import org.junit.Test;

import hackmaster20.business.ResourceManager;
import static org.junit.Assert.assertNull;


public class ResourceManagerUnitTest {

    @Test
    public void Player_Resources_isNull() {
        ResourceManager.getPlayerR();
        assertNull("Player Resources should be null", ResourceManager.getPlayerR());
    }
    @Test
    public void Enemy_Resources_isNull() {
        ResourceManager.getEnemyR();
        assertNull("Enemy Resources should be null", ResourceManager.getEnemyR());
    }
    @Test
    public void toString_isCorrect() {
//       ResourceManager.toString();
//        assertNull("Enemy Resources should be null", ResourceManager.getEnemyR());
        //TO DO
    }

}