package testClasses.Aria;
import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import setup.TestSessionInitiator;
import utils.DataIO;

/**
 * @author Jrani
 *
 */
public class LoginTest {
	/**
	 This method is to login in Aria as Admin .
	 */
	public static TestSessionInitiator tsi;
	
	@Test(priority=1)
	public void Verify_Login_in_Salesforce(){
	tsi = new TestSessionInitiator();
	tsi.baseAction.launchApplication(DataIO.get("salesForceURL", "TestData"));
	tsi.loginPage.PERFORM_LOGIN_IN_ARIA(DataIO.get("userName", "TestData"),(DataIO.get("password", "TestData")));
	Assert.assertTrue(tsi.homepage.verify_home_page_for_Aria());
}
	@AfterMethod 
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
		tsi.screen.screenShotOnTestFail(testResult);
		tsi.close();
	}
}

