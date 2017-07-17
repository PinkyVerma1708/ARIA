package actions;

import org.openqa.selenium.WebDriver;

import actions.BaseActions;

public class AccountsPageActions extends BaseActions{

	public WebDriver driver;
	
	public AccountsPageActions(WebDriver driver){
		super(driver);
		this.driver = driver;
	}
	/**
	 * click_on_newbutton on account
	 */
	//public void click_on_New_button(String Account_Record_type) -- for select record type on QA5 org
	public void click_on_New_button()
	{
		click("New_button", "HomePage");
			//selectOptionByVisibleText("Accountrecord_type", "HomePage", Account_Record_type);
		
	}
	
	public void click_on_continuebutton(){
		click("Continue_button", "HomePage");
	}
	/**
	 * Create new account
	 */
	public void create_new_account(String AccountName,String Type ){
		writeTextInto("AccountName_txtbox", "HomePage", AccountName);
		selectOptionByVisibleText("Type_dropdown", "HomePage", Type);
 		
	}
	public void create_new_Opportunity(String OpportunityName,String Stage_opp,String Close_Date )
	{
		writeTextInto("OppName_txtbox", "HomePage", OpportunityName);
		selectOptionByVisibleText("Stage", "HomePage", Stage_opp);
		writeTextInto("Closedate", "HomePage",Close_Date);
		enterafterselect("Closedate", "HomePage");
 		
	}
	
	//public void enterdate(String Close_Date){
	//	writeTextInto("Closedate", "HomePage",Close_Date);
		//enterafterselect("Closedate", "HomePage");
	//}
		
	
	/**
	 * Click_On_save button 
	 */
	public void Click_on_Savebutton(){
		click("Save_button", "HomePage");
	}
	/**
	 * Verify account details page
	 */
		public boolean verify_account_deatail_page(String verification){
			return getTextAndMatchString("account_detail_txt", "HomePage", verification );
		}
		/**
		 * Click_On_opportunities_link
		 */
		public void Click_On_Opportunities_link(){
			click("Opportunities_on_Account","HomePage");
		}
		/**
		 * Click_On_new_opportunities_button
		 */
		public void Click_On_new_Opportunities_button(){
			click("New_Opportunity_button","HomePage");
		}
		/**
		 * Create new Opportunity
		 */
		
		
				
		
}
	


