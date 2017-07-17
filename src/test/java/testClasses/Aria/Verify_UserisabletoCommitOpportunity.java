package testClasses.Aria;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import setup.TestSessionInitiator;
import utils.DataIO;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.Log;


public class Verify_UserisabletoCommitOpportunity
{
	private TestSessionInitiator tsi;
	/**
	This method is to login in Aria as Admin .
	*/
	
	@BeforeClass
	public void tearUp(){
		tsi = new TestSessionInitiator();
		Log.startTestCase("Aria - create master plan flow");
		tsi.baseAction.launchApplication(DataIO.get("salesForceURL", "TestData"));
	 }
	
	@AfterClass
	public void tearDown(){
		System.out.println("Test case has been executed successfully");
		Log.endTestCase("Plan creation");
		tsi.close();
	}
		
	@Test(priority=1)
	public void Verify_Login_in_Salesforce(){
	
		tsi.loginPage.PERFORM_LOGIN_IN_ARIA(DataIO.get("userName", "TestData"),(DataIO.get("password", "TestData")));
		Assert.assertTrue(tsi.homepage.verify_home_page_for_Aria());
	}
	/**
	This test case is to Create new account .
	*/
@Test(priority=2)
public void Create_New_Account()
{
	tsi.homepage.Click_On_Accounts_Tab();
	//tsi.accountPage.click_on_New_button(DataIO.get("Account_Record_type", "TestData"));-- for record type onQA5 Org
	tsi.accountPage.click_on_New_button();
	//tsi.accountPage.click_on_continuebutton();
	tsi.accountPage.create_new_account(DataIO.get("AccountName", "TestData"), DataIO.get("Type", "TestData"));
    tsi.accountPage.Click_on_Savebutton();
    Assert.assertTrue(tsi.accountPage.verify_account_deatail_page("Account Detail"));
	}
/**
This test case is to Create new Opportunity .
*/
@Test(priority=3)
public void Create_New_Opportunity()
{
	tsi.accountPage.Click_On_Opportunities_link();
	tsi.accountPage.Click_On_new_Opportunities_button();
	tsi.accountPage.create_new_Opportunity(DataIO.get("OpportunityName", "TestData"), DataIO.get("Stage_opp", "TestData"),DataIO.get("Close_Date", "TestData"));
	
	//tsi.accountPage.enterdate("22/02/2017");
	//tsi.accountPage.enterdate(DataIO.get("Close_Date", "TestData"), DataIO.get("Type", "TestData"));
	//tsi.accountPage.selectstage();
	//tsi.accountPage.selectstage(DataIO.get("Stage_opp", "TestData"), DataIO.get("Type", "TestData"));
	//tsi.accountPage.Click_on_Savebutton();

}
@Test(priority=4)
public void Add_Plan_Opportunity(){
	tsi.addplan.click_on_Add_Modify_Button(DataIO.get("Frame_Status", "TestData"));
	Assert.assertTrue(tsi.addplan.verify_addmodify_page("Add/Modify Plans"));
}

@Test(priority=5)

public void Add_BillingGroups()
{
	
	tsi.addplan.Billing_Groups(DataIO.get("Billing_Group", "TestData"),DataIO.get("Client_ID", "TestData"));
	Assert.assertTrue(tsi.addplan.verify_addmodify_page("Add/Modify Plans"));
	
}
@Test(priority=6)
public void Plan_selection()
{
	//Assert.assertTrue(tsi.addplan.verify_planselection("Add Plans"));
	//tsi.addplan.plan_selection(DataIO.get("CurrencyOption", "TestData"));
	tsi.addplan.plan_selection();
	Assert.assertTrue(tsi.addplan.verify_addmodify_page("Add/Modify Plans"));
	tsi.addplan.plan_save();
	
}
@Test(priority=7)
public void midplan()
{
	tsi.midtermplan.click_midplan_Add_Modify_Button();
	tsi.midtermplan.planSelect_midterm();
	tsi.midtermplan.midtermplan_save();
}

@AfterMethod 
public void takeScreenShotOnFailure(ITestResult testResult) throws IOException { 
	tsi.screen.screenShotOnTestFail(testResult);
	//tsi.close();
}
}
