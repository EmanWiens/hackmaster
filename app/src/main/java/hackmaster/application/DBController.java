package hackmaster.application;

public abstract class DBController
{
	public static final String dbName = "HM_DB";
	private static String dbPathName = "database/HM_DB";

	public static void startUp()
	{
		Services.createDataAccess(dbName);
	}

	public static void shutDown()
	{
		Services.closeDataAccess();
	}

	public static String getDBPathName() {
		if (dbPathName == null {
			return dbName;
		} else{
			return dbPathName;
		}
	}

	public static void setDBPathName(String pathName) {
		System.out.println("Setting DB path to: " + pathName);
		dbPathName = pathName;
	}
}
