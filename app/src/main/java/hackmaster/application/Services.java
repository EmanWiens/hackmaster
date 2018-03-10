package hackmaster.application;

import hackmaster.persistence.CardDataAccess;
import hackmaster.persistence.CardDataAccessInterface;
import hackmaster.persistence.DBInterface;
import hackmaster.persistence.DataAccessObject;
import hackmaster.persistence.PlayerDataAccess;
import hackmaster.persistence.PlayerDataAccessInterface;

public class Services
{
	private static DBInterface dataAccessService = null;
	private static PlayerDataAccessInterface playerDataAccessService = null;
	private static CardDataAccessInterface cardDataAccessService = null;

	public static void createDataAccess(String dbName) {
		if (dataAccessService == null) {
			dataAccessService = new DataAccessObject(dbName);
			playerDataAccessService = new PlayerDataAccess();
			cardDataAccessService = new CardDataAccess();

			dataAccessService.open(DBController.getDBPathName());
			playerDataAccessService.open(dataAccessService.getNewStatement());
			cardDataAccessService.open(dataAccessService.getNewStatement());
		}
	}

	public static void createDataAccess(DBInterface altDataAccessService,
											   PlayerDataAccessInterface altPlayerAccessService,
											   CardDataAccessInterface altCardAccessService) {
		if (dataAccessService == null) {
			dataAccessService = altDataAccessService;
			playerDataAccessService = altPlayerAccessService;
			cardDataAccessService = altCardAccessService;

			dataAccessService.open(DBController.getDBPathName());
			playerDataAccessService.open(dataAccessService.getNewStatement());
			cardDataAccessService.open(dataAccessService.getNewStatement());
		}
	}

	public static DBInterface getDataAccess() {
		if (dataAccessService == null) {
			handleUninitializedDB();
		}
		return dataAccessService;
	}

	public static PlayerDataAccessInterface getPlayerDataAccess() {
		if (dataAccessService == null) {
			handleUninitializedDB();
		}
		return playerDataAccessService;
	}

	public static CardDataAccessInterface getCardDataAccess() {
		if (dataAccessService == null) {
			handleUninitializedDB();
		}
		return cardDataAccessService;
	}

	public static void closeDataAccess() {
		if (dataAccessService != null) {
			dataAccessService.close();
			cardDataAccessService.close();
			playerDataAccessService.close();
		}
		dataAccessService = null;
	}

	private static void handleUninitializedDB() {
		System.out.println("Connection to data access has not been established");
		System.exit(1);
	}
}
