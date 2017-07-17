package actions;

import org.openqa.selenium.WebDriver;

import actions.BaseActions;

/**
 * @author Jrani
 *
 */
public class HomePageActions extends BaseActions{
	
	WebDriver driver;
	
	public HomePageActions(WebDriver driver){
		super(driver);
		this.driver = driver;
	}
		 /**
		 *This Method is to verify home page for Aria
		 *
		 *@author jyoti rani
		 */
	    public boolean verify_home_page_for_Aria(){
				return isElementDisplayed("AriaLogo", "HomePage");
		}
	    /**
		 * Click_On_Accounts_Tab
		 */
		public void Click_On_Accounts_Tab(){
			hardWait(3);
			click("Accounts_tab","HomePage");
		}
		
}
