package comp3350.srsys.acceptance;

import junit.framework.Assert;

import com.robotium.solo.Solo;

import comp3350.srsys.presentation.HomeActivity;
import android.test.ActivityInstrumentationTestCase2;

public class StudentsTest extends ActivityInstrumentationTestCase2<HomeActivity> {

	private Solo solo;

	public StudentsTest()
	{
		super(HomeActivity.class);
	}

	public void setUp() throws Exception
	{
		solo = new Solo(getInstrumentation(), getActivity());
		
		// Disable this for full acceptance test
		// System.out.println("Injecting stub database.");
		// Services.createDataAccess(new DataAccessStub(Main.dbName));
	}
	
	@Override
	public void tearDown() throws Exception
	{
		solo.finishOpenedActivities();
	}
	
	// Please note again that this is not a complete set of acceptance tests
	
	public void testEditStudent()
	{
		solo.waitForActivity("HomeActivity");
		solo.clickOnButton("Students");
		solo.assertCurrentActivity("Expected activity StudentsActivity", "StudentsActivity");
		
		Assert.assertTrue(solo.searchText("400: Mary Bailey"));
		solo.clickOnText("400: Mary Bailey");
		Assert.assertTrue(solo.searchEditText("400"));
		Assert.assertTrue(solo.searchEditText("Mary Bailey"));
		Assert.assertTrue(solo.searchEditText("Off Campus"));
		
		solo.clearEditText(1);
		solo.enterText(1, "Mary Bucket");
		solo.clearEditText(2);
		solo.enterText(2, "Somewhere Else");
		solo.clickOnButton("Update");
		
		solo.goBack();

		solo.waitForActivity("HomeActivity");
		solo.clickOnButton("Students");
		solo.assertCurrentActivity("Expected activity StudentsActivity", "StudentsActivity");

		solo.waitForText("400: Mary Bucket");
		Assert.assertTrue(solo.searchText("400: Mary Bucket"));
		solo.clickOnText("400: Mary Bucket");
		Assert.assertTrue(solo.searchEditText("400"));
		Assert.assertTrue(solo.searchEditText("Mary Bucket"));
		Assert.assertTrue(solo.searchEditText("Somewhere Else"));
	
		// clean up
		solo.clearEditText(1);
		solo.enterText(1, "Mary Bailey");
		solo.clearEditText(2);
		solo.enterText(2, "Off Campus");
		solo.clickOnButton("Update");
	}
	
	public void testInvalidStudent()
	{
		solo.waitForActivity("HomeActivity");
		solo.clickOnButton("Students");
		solo.assertCurrentActivity("Expected activity StudentsActivity", "StudentsActivity");
		
		solo.clickOnButton("Create");
		Assert.assertTrue(solo.searchText("Warning"));
		solo.goBack();

		Assert.assertTrue(solo.searchText("100: Gary Chalmers"));
		solo.clickOnText("100: Gary Chalmers");
		
		solo.clearEditText(1);
		solo.clickOnButton("Update");
		solo.waitForDialogToOpen();
		Assert.assertTrue(solo.searchText("Warning"));
		solo.goBack();
		
		solo.enterText(1, "Something Something");

		solo.clearEditText(0);
		solo.enterText(0, "987654321");
		solo.clickOnButton("Delete");
		solo.waitForDialogToOpen();
		Assert.assertTrue(solo.searchText("Warning"));
		solo.goBack();

		solo.clickOnButton("Update");
		solo.waitForDialogToOpen();
		Assert.assertTrue(solo.searchText("Fatal error"));
		solo.goBack();
		solo.assertCurrentActivity("Expected activity HomeActivity", "HomeActivity");
	}
}
