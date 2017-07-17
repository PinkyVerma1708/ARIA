package setup;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import utils.DataIO;

/**
 * @author jyoti Rani
 *
 */

public class WebDriverFactory {

	protected static WebDriver driver = null;
	protected static DesiredCapabilities capability = new DesiredCapabilities();
    private RemoteWebDriver remoteDriver;

	/**
	 * This method is to return driver instance for given browserName
	 * 
	 * @param browserName
	 * @return
	 */
	private WebDriver setLocalBrowser() {

		String browserName = DataIO.get("localBrowserName", "Config").toUpperCase();
		switch (browserName) {
		case "FIREFOX":
			driver = getFirefoxDriver(System.getProperty("user.dir")+File.separator+"resources"+File.separator+"drivers"+File.separator+"geckodriver.exe");
			break;
		case "FF":
			driver = getFirefoxDriver(System.getProperty("user.dir")+File.separator+"resources"+File.separator+"drivers"+File.separator+"geckodriver.exe");
			break;
		case "CHROME":
			if(System.getProperty("os.name").startsWith("Mac")){
				driver = getChromeDriver(System.getProperty("user.dir")+File.separator+"resources"+File.separator+"drivers"+File.separator+"chromedriver");
			}
			else {
				driver = getChromeDriver(System.getProperty("user.dir")+File.separator+"resources"+File.separator+"drivers"+File.separator+"chromedriver.exe");
			}
			break;
		case "INTERNETEXPLORER":
			driver = getInternetExplorerDriver(System.getProperty("user.dir")+ "\\resources\\drivers\\IEDriverServer.exe");
			break;
		case "IE":
			driver = getInternetExplorerDriver(System.getProperty("user.dir")+ "\\resources\\drivers\\IEDriverServer.exe");
			break;
		case "HEADLESS":
			driver = getHtmlUnitDriver();
			break;
		case "SAFARI":
			driver = getSafariDriver();
			break;	
		default:
			driver = getFirefoxDriver(System.getProperty("user.dir")+File.separator+"resources"+File.separator+"drivers"+File.separator+"geckodriver.exe");
			break;
		}

		return driver;
	}

	/*	private WebDriver setLocalBrowserMAC() {
		String browserName = DataIO.get("localBrowserName", "Config").toUpperCase();
		switch (browserName) {
		case "FIREFOX":
			driver = getFirefoxDriverMAC();
			break;
		case "FF":
			driver = getFirefoxDriverMAC();
			break;
		case "CHROME":
			driver = getChromeDriver(System.getProperty("user.dir")+ "/resources/drivers/chromedriver");
			break;
		case "SAFARI":
			driver = getSafariDriver();
			break;

		default:
			driver = getSafariDriver();
			break;
		}

		return driver;
	}

	/**
	 * get Browser driver as per the config file
	 * @return
	 */
	public WebDriver getBrowser() {
		if (DataIO.get("serverType", "Config").equalsIgnoreCase("local")) {
			driver = setLocalBrowser();
		} else if (DataIO.get("serverType", "Config").equalsIgnoreCase("remote")) {
			driver = setRemoteDriver();
		}
		return driver;
	}




	/**
	 * Launching firefox browser
	 * @return FirefoxDriver
	 */
	private WebDriver getFirefoxDriver(String driverPath) {
		FirefoxProfile profile = new FirefoxProfile();
		System.setProperty("webdriver.firefox.marionette",driverPath);
		profile.setPreference("browser.cache.disk.enable", false);
		profile.setPreference("network.automatic-ntlm-auth.allow-non-fqdn", true);
		
		return new FirefoxDriver(profile);
	}

	/**
	 * Launch safari browser
	 * @return SafariDriver
	 */
	private WebDriver getSafariDriver() {
		SafariOptions options = new SafariOptions();
		options.setUseCleanSession(true); //if you wish safari to forget session everytime
		return new SafariDriver(options); 
	}

	private WebDriver setRemoteDriver() {
		DesiredCapabilities cap = null;
		String browser = DataIO.get("remoteBrowserName", "Config");
		if (browser.equalsIgnoreCase("firefox")) {
			cap = DesiredCapabilities.firefox();
			cap.setCapability("platform",
					DataIO.get("platform", "Config"));
			cap.setCapability("version",
					DataIO.get("version", "Config"));
			cap.setCapability("name", "Workday Automation");

		} else if (browser.equalsIgnoreCase("chrome")) {
			cap = DesiredCapabilities.chrome();
			cap.setCapability("platform",
					DataIO.get("platform", "Config"));
			cap.setCapability("version",
					DataIO.get("version", "Config"));
			cap.setCapability("name", "Workday Automation");

		} else if (browser.equalsIgnoreCase("Safari")) {
			cap = DesiredCapabilities.safari();
		} else if ((browser.equalsIgnoreCase("ie"))
				|| (browser.equalsIgnoreCase("internetexplorer"))
				|| (browser.equalsIgnoreCase("internet explorer"))) {
			cap = DesiredCapabilities.internetExplorer();
			cap.setCapability("platform",
					DataIO.get("platform", "Config"));
			cap.setCapability("version",
					DataIO.get("version", "Config"));
		}
		String seleniuhubaddress = DataIO.get("remoteURL", "Config");
		URL selserverhost = null;
		try {
			selserverhost = new URL(seleniuhubaddress);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		 cap.setJavascriptEnabled(true);
		
		remoteDriver = new RemoteWebDriver(selserverhost, cap);
		remoteDriver.setFileDetector(new LocalFileDetector());
		

		return remoteDriver;
		
	}

	/**
	 * this Method is for return New IE driver instance
	 * 
	 * @param driverPath
	 * @return IE driver instance
	 */
	private WebDriver getInternetExplorerDriver(String driverPath) {
		System.setProperty("webdriver.ie.driver", driverPath);
		capability.setJavascriptEnabled(true);
		return new InternetExplorerDriver(capability);
	}

	/**
	 * @param driverPath
	 * @return
	 */
	private WebDriver getChromeDriver(String driverPath) {
		System.setProperty("webdriver.chrome.driver", driverPath);
		capability.setJavascriptEnabled(true);
		return new ChromeDriver(capability);
	}

	/**
	 * @param driverPath
	 * @return
	 */
	private WebDriver getHtmlUnitDriver() {
		HtmlUnitDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		return driver;
	}
}
