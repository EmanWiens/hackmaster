package hackmaster.application;

import hackmaster.persistence.DBInterface;
import hackmaster.persistence.DataAccessObject;

public class Services
{
	private static DBInterface dataAccessService = null;

	public static DBInterface createDataAccess(String dbName)
	{
		if (dataAccessService == null)
		{
			dataAccessService = new DataAccessObject(dbName);
			dataAccessService.open(DBController.getDBPathName());
		}
		return dataAccessService;
	}

	public static DBInterface createDataAccess(DBInterface alternateDataAccessService)
	{
		if (dataAccessService == null)
		{
			dataAccessService = alternateDataAccessService;
			dataAccessService.open(DBController.getDBPathName());
		}
		return dataAccessService;
	}

	public static DBInterface getDataAccess(String dbName)
	{
		if (dataAccessService == null)
		{
			System.out.println("Connection to data access has not been established.");
			System.exit(1);
		}
		return dataAccessService;
	}

	public static void closeDataAccess()
	{
		if (dataAccessService != null)
		{
			dataAccessService.close();
		}
		dataAccessService = null;
	}
}
