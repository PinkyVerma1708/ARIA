package actions;

import org.openqa.selenium.WebDriver;

import utils.BasePage;
import utils.DataIO;

/**
 * @author jyoti Rani
 *
 */
public class BaseActions extends BasePage {
	protected static String currentUrl = null;
	protected WebDriver driver;
	public BaseActions(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void logTestMethod(){
		logMessage("hello this is log test");
	}

	/**
	 * This method is to fetch current browser URL and save it to test data file
	 * @param property
	 * @param fileName
	 */
	public void getCurrentURLAndSaveInDataFile(String property, String fileName){
		currentUrl = getCurrentURL();
	}
	
	/**
	 * this method is to refresh the browser session as per its pre-stage
	 * @param property
	 * @param fileName
	 */
	public void refeshLaunchingURL(String property, String fileName, String className){
		System.out.println("***************** Test "+ className + " Executed ****************");
		String[] URL = currentUrl.trim().split(".com");
		try{
			navigateToURL(currentUrl);
			if(isElementPresent("logged_in_as_user_txt", "HomePage")){
				navigateToURL(URL[0].trim()+".com/secur/logout.jsp");
				navigateToURL(currentUrl);
			}
			if (!isElementPresent("GlobalSearch_button", "HomePage")) {
				driver.manage().deleteAllCookies();
				navigateToURL(currentUrl);
				System.out.println("@@@@@@ ---- Login Page Displayed");
				new LoginPageActions(driver).PERFORM_LOGIN_IN_ARIA(DataIO.get("admin_email", "TestData"), DataIO.get("admin_password", "TestData"));
	
			}
		}catch(Exception e){
			System.out.println("Exception in refreshing the session");
		}
	}
}
