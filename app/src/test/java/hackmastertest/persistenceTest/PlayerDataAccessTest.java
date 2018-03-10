package hackmastertest.persistenceTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hackmastertest.persistenceTest.DataAccessStub;
import hackmaster.objects.PlayerStatsSaves;
import hackmaster.persistence.PlayerDataAccessInterface;
import hackmaster.application.Services;

public class PlayerDataAccessTest {
    private PlayerStatsSaves playerStats;
    private DataAccessStub stubDB;
    private PlayerDataAccessInterface playerDataAccess;

    @Before
    public void Setup() {
        playerStats = new PlayerStatsSaves();
        stubDB = new DataAccessStub("stubPlayerStats");
        //playerDataAccess = Servic
    }

    // TODO STUB TEST

}
