package setup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import actions.HomePageActions;
import setup.Screenshot;
import actions.Account_ValueVerify;
import actions.AccountsPageActions;
import actions.AddPlan_Opp;

import actions.EditRate_Action;
import actions.LoginPageActions;
import actions.BaseActions;
import actions.MidTermplan_1;
import actions.SwapPlan_MidTerm;
import utils.BasePage;
import utils.DataIO;

/**
 * @author Jyoti Rani
 */
public class TestSessionInitiator{

	public TestSessionInitiator() {
		if (driver == null) {
			launchBrowserSession();
		}
		verificationErrors = new StringBuffer();
	}

	public static WebDriver driver = null;
	private WebDriverFactory wdf = new WebDriverFactory();

	//Define all the page objects will null
	public BasePage baseui = null;
	public BaseActions baseAction = null;
	public LoginPageActions loginPage = null;
	public Screenshot screen = null;
	public HomePageActions homepage = null;
	public AccountsPageActions accountPage=null;
	public AddPlan_Opp addplan=null;
	public Account_ValueVerify valueverify=null;
	public MidTermplan_1 midtermplan=null;
	public SwapPlan_MidTerm swap_midterm=null;
	public EditRate_Action edit_rate=null;
	
	
	/**
	 * Initialize all the page objects for all action classes
	 */
	private void _initActions(){
		baseAction = new BaseActions(driver);
		loginPage= new LoginPageActions(driver);
		screen = new Screenshot(driver);
		homepage = new HomePageActions(driver);
		accountPage= new AccountsPageActions(driver);
		addplan= new AddPlan_Opp(driver);
		valueverify = new Account_ValueVerify(driver);
		midtermplan= new MidTermplan_1(driver);
		swap_midterm= new SwapPlan_MidTerm(driver);
		edit_rate= new EditRate_Action(driver);
		
	}

	/**
	 * Launching browser instance
	 */
	private void launchBrowserSession(){
		_browserConfig();
		_initActions();	
	}

	/**
	 * This method is to configure the browser setting.
	 */
	private void _browserConfig(){
		driver = wdf.getBrowser();	
		driver.manage().deleteAllCookies();
		if(DataIO.get("localBrowserName", "Config").equalsIgnoreCase("chrome")){
			driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(DataIO.getConfig("timeout")), TimeUnit.SECONDS);
	}

	/**
	 * This keyword is used to launch applications that use authentication
	 * to validate user
	 * @param applicationpath
	 * @param authUser
	 * @param authPed
	 */
	public void launchApplication(String applicationpath, String authUser, String authPwd, String browser) {
		System.out.println("browser name=="+ browser);
		if(!browser.equalsIgnoreCase("IE")){
			applicationpath = applicationpath.replace(
					"http://",
					"http://" + authUser.replaceAll("@", "%40") + ":"
							+ authPwd.replaceAll("@", "%40") + "@");
			Reporter.log("The application url is :- " + applicationpath, true);
			driver.get(applicationpath);
		}
	}

	public void getURL(String URL){
		driver.get(URL);
	}

	public void close(){
		try{ 
			driver.quit();
			if (driver != null) { 
				driver.quit(); 
			} 
			System.out.println(driver);
		}catch(Exception e) { 
		}
	}

	public void verifyTrue(boolean condition, String test) {
		try {
			Assert.assertTrue(condition,test);
		} catch (Error e) {
			verificationErrors.append(e);
			Reporter.log("failed");
		}
	}

	private StringBuffer verificationErrors;
	

}


