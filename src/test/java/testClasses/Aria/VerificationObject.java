package testClasses.Aria;
import java.io.IOException;

import org.testng.Assert;
//import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import setup.TestSessionInitiator;
import utils.DataIO;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.Log;

public class VerificationObject 
{
	private TestSessionInitiator tsi;
	
	@BeforeClass
	public void tearUp()
	{
		tsi = new TestSessionInitiator();
		Log.startTestCase("Aria - verify value on account");
		tsi.baseAction.launchApplication(DataIO.get("salesForceURL", "TestData"));
	 }
	
	@AfterClass
	public void tearDown()
	{
		System.out.println("Test case has been executed successfully");
		Log.endTestCase("Verified data");
		tsi.close();
	}
	
	@Test(priority=1)
	public void Verify_Login_in_Salesforce()
	{
	
		tsi.loginPage.PERFORM_LOGIN_IN_ARIA(DataIO.get("userName", "TestData"),(DataIO.get("password", "TestData")));
		Assert.assertTrue(tsi.homepage.verify_home_page_for_Aria());
	}
	@Test(priority=2)
	public void Verify_value_AccountsMRR()
	{
		tsi.valueverify.Click_On_Accounts_Tab();
		Assert.assertTrue(tsi.valueverify.verify_MRR_for_Aria(),"hello" );
	}
	
	@Test(priority=3)
	public void Verify_value_AccountsTCV()
	{		
		Assert.assertTrue(tsi.valueverify.verify_TCV_for_Aria(),"TCV verified");
		//Assert.assertTrue(tsi.valueverify.verify_ACV_for_Aria());
		//Assert.assertTrue(tsi.valueverify.verify_ARR_for_Aria());
	}
	
	@Test(priority=4)
	public void Verify_value_AccountsACV()
	{		
		Assert.assertTrue(tsi.valueverify.verify_ACV_for_Aria(),"ACV verified");
		
	}
	@Test(priority=5)
	public void Verify_value_AccountsARR()
	{		
		Assert.assertTrue(tsi.valueverify.verify_ARR_for_Aria(),"ARR verified");
		
	}
	
	@AfterMethod 
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException
	{ 
		tsi.screen.screenShotOnTestFail(testResult);
		//tsi.close();
	}
}
