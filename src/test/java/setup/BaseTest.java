package setup;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import utils.DataIO;
/**
 * @author jyoti rani
 *
 */
public abstract class BaseTest {

	public static TestSessionInitiator tsi;

	@BeforeSuite
	public void tearUp(){
		tsi = new TestSessionInitiator();
		tsi.baseAction.launchApplication(DataIO.get("salesForceURL", "TestData"));
		tsi.loginPage.PERFORM_LOGIN_IN_ARIA(DataIO.get("admin_email", "TestData"), DataIO.get("admin_password", "TestData"));
		Assert.assertTrue(tsi.homepage.verify_home_page_for_Aria());
	}

	@BeforeTest
	public void setApplicationLaunchingURL(){
		tsi.baseAction.getCurrentURLAndSaveInDataFile("ApplicationLaunchingURL", "TempData");
	}

	@AfterSuite
	public void tearDown(){
		tsi.close();
	}
	
}
	
