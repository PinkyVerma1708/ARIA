package utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

/**
 * @author neerajagarwal
 * 
 */
public class BasePage extends Locators {
	public WebDriver driver;

	public BasePage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}list

	public void click(String elementName, String fileName) {
		
		try {
			
			getElement(elementName, fileName).click();
		
    	} catch (Exception ex2) {
			logMessage("Element " + elementName + " could not be clicked! ");
			ex2.printStackTrace();
		}
	}
	
	
	public void click(String elementName, String fileName, String textToReplace) {
		// TODO Auto-generated method stub
		getElement(elementName, fileName, textToReplace).click();
	}
	//public void click(String elementName, String fileName, String textToReplace) {
		//try {
		//	getElement(elementName, fileName, textToReplace).click();
			
		//	} catch (Exception ex2) {
		//	logMessage("Element " + elementName + " could not be clicked! "+ ex2.getMessage());
		//	ex2.printStackTrace();
		//}
	//}

	public void writeTextInto(String elementName, String fileName,
			String textToReplace, String inputText) {
		
		try {
			getElement(elementName, fileName).clear();
			getElement(elementName, fileName, textToReplace).sendKeys(inputText);
			getElement(elementName, fileName, textToReplace).sendKeys(Keys.ENTER);
		
		} catch (Exception ex2) {
			ex2.printStackTrace();
		}
	}

	public void writeTextInto(String elementName, String fileName, String text) {
		// TODO Auto-generated method stub
		getElement(elementName, fileName).clear();
		getElement(elementName, fileName).sendKeys(text);
		

	}
	public void enterafterselect(String elementName, String fileName) {
		
		getElement(elementName, fileName).sendKeys(Keys.ENTER);
	}

    public void pressenter()
    {
    	driver.switchTo().alert().accept();
    }
	public void hardWait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void launchApplication(String URL) {
		driver.get(URL);
	}

	protected String getPageTitle() {
		return driver.getTitle();
	}

	public void logMessage(String message) {
		Reporter.log(message, true);
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	protected void verifyPageUrlContains(String pageUrlPart) {
		String currentUrl = getCurrentURL();
		// assertThat("FAILED: wrong page. Url does not contain expected value",
		// currentUrl, containsString(pageUrlPart));
		logMessage("PASSED: Current Page url '" + currentUrl
				+ "' is expected!!!");
	}

	protected WebElement getElementByIndex(List<WebElement> elementlist,
			int index) {
		return elementlist.get(index);
	}

	protected WebElement getElementByExactText(List<WebElement> elementlist,
			String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().equalsIgnoreCase(elementtext.trim())) {
				element = elem;
			}
		}
		// FIXME: handle if no element with the text is found in list No element
		// exception
		if (element == null) {
		}
		return element;
	}
	
	
	
	
	
	protected WebElement getElementByContainsText(List<WebElement> elementlist,
			String elementtext) {
		WebElement element = null;
		for (WebElement elem : elementlist) {
			if (elem.getText().contains(elementtext.trim())) {
				element = elem;
			}
		}
		// FIXME: handle if no element with the text is found in list
		if (element == null) {
		}
		return element;
	}


	public void switchwindow(String winHandleParent) {
		try {
			hardWait(3);
			for (String winHandleChild : driver.getWindowHandles()) {
				if (!winHandleChild.contains(winHandleParent)) {
					driver.switchTo().window(winHandleChild);
					hardWait(3);
				}
			}

		} catch (Exception e) {
			e.getMessage();
		}

	}

	protected void switchToFrame(WebElement element) {
		// switchToDefaultContent();
		wait.waitForElementToBeAppear(element);
		driver.switchTo().frame(element);
	}
	
	protected void uidialogbox(WebElement element) {
		// switchToDefaultContent();
		wait.waitForElementToBeAppear(element);
		element.click();
	}

	public void switchToFramebyIndex(int value) {
		hardWait(1);
		driver.switchTo().frame(value);
	}

	public void switchToDefaultContent() {
		hardWait(1);
		driver.switchTo().defaultContent();
		hardWait(1);
	}
	
	public void refresh()
	{
		
		driver.navigate().refresh();
		hardWait(2);
	}

	protected void executeJavascript(String script) {
		((JavascriptExecutor) driver).executeScript(script);
	}

	protected void javaScriptClick(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	protected void hover(WebElement element) {
		Actions hoverOver = new Actions(driver);
		hoverOver.moveToElement(element).build().perform();
	}

	protected void click_and_Select_and_Replace_ContentwithNewValue(
			String elementName, String fileName, String inputText) {
		Actions ClickSelectReplace = new Actions(driver);
		ClickSelectReplace.moveToElement(getElement(elementName, fileName))
				.clickAndHold().sendKeys(inputText).release().build().perform();
	}

	public void handleAlert() {
		try {
			hardWait(2);
			driver.switchTo().alert().accept();
			logMessage("Alert handled..");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			logMessage("Alert Not Present");
		}
	}
	
	public String verifyAlertPresentOrNot() {
		String alertText=null;
		try {
			alertText=driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();
			
		} catch (Exception e) {
			logMessage("Alert Not Present");
		}
		return alertText;
	}
	


	protected void scrollDown(WebElement element) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView(true);", element);
	}

	protected void hoverClick(WebElement element) {
		Actions hoverClick = new Actions(driver);
		hoverClick.moveToElement(element).click().build().perform();
	}

	private boolean isElementAHyperlink(WebElement element) {
		boolean flag = false;
		System.err.println("****** tag Name = " + element.getTagName());
		if (element.getTagName().equals("a")) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	protected void verifyElementIsHyperlinked(WebElement element) {
		Assert.assertTrue(isElementAHyperlink(element), "[Failed]: Element \'"
				+ element.getText() + "\' is not a hyperlink");
		logMessage("[Passed: Element \'" + element.getText()
				+ "\' is a hyperlink]");
	}

	protected void verifyElementIsNotHyperlinked(WebElement element) {
		Assert.assertTrue(!(isElementAHyperlink(element)),
				"[Failed]: Element \'" + element.getText()
						+ "\' is a hyperlink");
		logMessage("[Passed: Element \'" + element.getText()
				+ "\' is not a hyperlink]");
	}

	public String getText(String locator, String fileName) {
		String getText = null;
		getText = getElement(locator, fileName).getText();
		return getText;
	}

	public String getvaluefromCheckbox(String locator, String fileName,
			String attribute) {
		String getText = null;
		getText = getElement(locator, fileName).getAttribute(attribute);
		if (getText.equals("Checked")) {
			getText = "Yes";
			return getText;
		} else {
			getText = "No";
			return getText;
		}
	}
	
	/*public void selectcheckbox(String locator, String fileName)
	{
		
		
		  if(getElement(locator, fileName).isSelected())
		  {
			 getElement(locator, fileName).click();
		  }
		
	}*/
	
	
	public boolean CheckingCheckboxforSurvey(String locator, String fileName){
		
		if (getElement(locator, fileName).getAttribute("title").equalsIgnoreCase("Checked")){
			return true;  
		}
	return false;
	}
	
	public boolean getAttributeAndMatchString(String locator, String fileName,
			String attribute,String textToVerify) {
		hardWait(3);
		if (getElement(locator, fileName).getAttribute(attribute).trim()
				.contains(textToVerify)) {
			return true;
		} else {
			return false;
		}	
	}
	
	


	public String getText(String locator, String fileName, String textToReplace) {
		String getText = null;
		getText = getElement(locator, fileName, textToReplace).getText();
		return getText;
	}

	public boolean getTextAndMatchString(String locator, String fileName,
			String textToReplace, String textToVerify) {
		hardWait(3);
		if (getElement(locator, fileName, textToReplace).getText().trim().contains(textToVerify)) {
			return true;
			
		} else {
			return false;
		}
	}

	public boolean getTextAndMatchString(String locator, String fileName,
			String textToVerify) {
		hardWait(1);
		if (getElement(locator, fileName).getText().trim().contains(textToVerify)) {
			return true;
		} else {
			return false;
		}
	}
	
	/*public boolean getTextAndMatchValue(String locator, String fileName,
			String locator1, String fileName1) {
		hardWait(1);
		WebElement val1=getElement(locator, fileName);
		WebElement Val2=getElement(locator1, fileName1);
		//if (val1.getText().trim().equalsIgnoreCase(Val2.getText().trim()))
		if (val1.getText().trim().equalsIgnoreCase(Val2.getText().trim()))
		 {
			return true;
		} else {
			return false;
		}
	}*/
	
	public boolean getTextAndMatchValue(WebElement val1, WebElement val2) {
		return val1.getText().trim().equalsIgnoreCase(val2.getText().trim());
		
	}
	
	public boolean getTextAndcompareValue(WebElement val1, WebElement val2) {
		return val1.getText().trim().contains(val2.getText().trim());
	
	}
	
	public void selectOptionByVisibleText(String dropdownElement,
			String fileName, String optionNeedToSelect) {
		Select dropdown = new Select(getElement(dropdownElement, fileName));
		dropdown.selectByVisibleText(optionNeedToSelect);
		hardWait(1);
	}

	public void selectOptionByIndex(String dropdownElement, String fileName,
			int index) {
		
		Select dropdown = new Select(getElement(dropdownElement, fileName));
		dropdown.selectByIndex(index);
	}

	public void navigateToURL(String URL) {
		driver.navigate().to(URL);
	}

	public void gotobackpage() {
		hardWait(1);
		driver.navigate().back();
		hardWait(1);
	}

	public void closeCurrentBrowserWindow() {
		driver.close();
	}

	public void closeAllBrowserWindow() {
		driver.quit();
	}

	public boolean isElementPresent(String locator, String fileName) {
		boolean flag = false;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			if (getElementWithoutWait(locator, fileName) != null) {
				flag = true;
			}
		} catch (NoSuchElementException e) {
		} catch (Exception e1) {
		}
		driver.manage()
				.timeouts()
				.implicitlyWait(Integer.parseInt(DataIO.getConfig("timeout")),
						TimeUnit.SECONDS);
		return flag;
	}

	public boolean isElementPresent(String locator, String fileName,
			String textToReplace) {
		boolean flag = false;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {
			if (getElementWithoutWait(locator, fileName, textToReplace) != null) {
				flag = true;
			}
		} catch (NoSuchElementException e) {
		} catch (Exception e1) {
		}
		driver.manage()
				.timeouts()
				.implicitlyWait(Integer.parseInt(DataIO.getConfig("timeout")),
						TimeUnit.SECONDS);
		return flag;
	}

	public boolean getcheckboxvalue(String locator, String fileName,
			String replacement, String attribute) {
		String getText = null;
		getText = getElement(locator, fileName, replacement).getAttribute(
				attribute);
		System.out.println("getText=" + getText);
		if (getText.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementsPresent(String locator, String fileName) {
		boolean flag = false;
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try {
			if (getElementsWithoutWait(locator, fileName).size() > 0) {
				flag = true;
			}
		} catch (NoSuchElementException e) {
			flag = false;
		}
		driver.manage()
				.timeouts()
				.implicitlyWait(Integer.parseInt(DataIO.getConfig("timeout")),
						TimeUnit.SECONDS);
		return flag;
	}
	
	
	public boolean isElementsPresent(String locator, String fileName, String textToReplace) {
		boolean flag = false;
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		try {
			if (getElementsWithoutWait(locator, fileName, textToReplace).size() > 0) {
				flag = true;
			}
		} catch (NoSuchElementException e) {
			flag = false;
		}
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(DataIO.getConfig("timeout")),
						TimeUnit.SECONDS);
		return flag;
	}

	public boolean isElementDisplayed(String locator, String fileName) {
		hardWait(1);
		try {
			if (getElement(locator, fileName).isDisplayed()) {
				return true;
			} else if (getElement(locator, fileName) == null) {
				return false;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isElementclickable(String locator, String fileName) {
		try {
			if (getElement(locator, fileName).getTagName() == "a") {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getTagNameofElement(String locator, String fileName) {
		
			 String tagname = getElement(locator, fileName).getTagName();
			 return tagname;
			}
	
	public boolean isElementDisplayed(String locator, String fileName,
			String textToReplace) {
		hardWait(1);
		try {
			if (getElement(locator, fileName, textToReplace).isDisplayed()) {
				return true;
			} else if (getElement(locator, fileName, textToReplace) == null) {
				return false;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public void captureScreenshot(String screenshotName) {

		try {
			TakesScreenshot ts = (TakesScreenshot) driver;

			File source = ts.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(source, new File("./Screenshots/"
					+ screenshotName + ".png"));

			System.out.println("Screenshot taken");
		} catch (Exception e) {

			System.out.println("Exception while taking screenshot "
					+ e.getMessage());
		}
	}

	public void rightClick(String elementName, String fileName) {
		Actions action = new Actions(driver);
		action.contextClick(getElement(elementName, fileName)).build()
				.perform();

	}

	public void actionClick(String elementName, String fileName) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(elementName, fileName)).click().build()
				.perform();
		hardWait(1);
	}

	public void doubleClick(String element, String fileName) {
		Actions action = new Actions(driver).doubleClick(getElement(element,
				fileName));
		action.build().perform();
	}

	public void pressEnterKey(String elementName, String fileName) {
		getElement(elementName, fileName).sendKeys(Keys.ENTER);

	}

	public void pressEnterKeyWithRobot() {
		Robot okRobot;
		try {
			okRobot = new Robot();
			hardWait(2);
			okRobot.keyPress(KeyEvent.VK_ENTER); // press Enter
			hardWait(1);
			okRobot.keyRelease(KeyEvent.VK_ENTER); // release Enter
			hardWait(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isElementNotEditable(String locator, String fileName) {
		try {
			System.out.println(getElement(locator, fileName).getTagName());
			if (!getElement(locator, fileName).getTagName().equalsIgnoreCase(
					"input")||getElement(locator, fileName).getTagName().equalsIgnoreCase(
							"textarea")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public String getParentWindowHandle() {
		return driver.getWindowHandle();
	}

	public void switchToWindow(String windowHandle) {
		driver.switchTo().window(windowHandle);
		hardWait(2);
	}

	/**
	 * to get unique Id with added prefix
	 * 
	 * @param prefix
	 * @return
	 */
	public static String getToken(String prefix) {
		long current = System.currentTimeMillis();
		String token = prefix + current;
		return token;
	}

	/**
	 * To get future date by adding number of days in current date
	 * 
	 * @param futureDateInDays
	 * @return
	 */
	public static String getDateWithWeekendExclusion(int futureDateInDays) {
		String currentDay = getDayForGivenDate(BasePage
				.getFutureDateByAddingGivenDays(futureDateInDays));
		if (currentDay.equalsIgnoreCase("Fri")
				|| currentDay.equalsIgnoreCase("Sat")
				|| currentDay.equalsIgnoreCase("Sun")) {
			futureDateInDays = futureDateInDays + 3;
		}
		return BasePage.getFutureDateByAddingGivenDays(futureDateInDays);
	}
	
	
 /**
	 * To get future date by adding number of days in current date
	 * 
	 * @param futureDateInDays
	 * @return
	 */
	public static String getFutureDateByAddingGivenDays(int futureDateInDays) {
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, futureDateInDays);
		return sdf.format(cal.getTime()).trim();
	}

	/**
	 * To get current Date Time
	 * 
	 * @param futureDateInDays
	 * @return
	 */
	public static String getCurrentDateTime(String requiredFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(requiredFormat);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		return sdf.format(cal.getTime());
	}

	public void clickWhenClickable(WebElement element, int waitForSecons) {
		boolean flag = true;

		for (int i = 0; i <= waitForSecons; i++) {
			try {
				flag = true;
				element.click();
			} catch (Exception e) {
				System.out.println("not Clicked - " + i + e.getMessage());
				flag = false;
				hardWait(1);
			}
			if (flag) {
				break;
			}
		}
	}

	/**
	 * this methoed is to copy given text into clipboard
	 * 
	 * @param textForCopy
	 */
	public void setClipboardData(String textForCopy) {
		StringSelection stringSelection = new StringSelection(textForCopy);
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(stringSelection, null);
	}

	/**
	 * this method is to paste text (stored in clipboard) in focused text box
	 * and then perform enter on the same
	 * 
	 */
	public void uploadFileWithSystemWindow() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 
	 * 
	 */
	public void SelectMultipleoptionfromMultiplepicklist(String dropdownElement,String fileName) {
		
			Select select = new Select(getElement(dropdownElement, fileName));
			Actions builder = new Actions(driver);
			builder.keyDown(Keys.CONTROL)
			.click(select.getOptions().get(0))
			.click(select.getOptions().get(1))
			.keyUp(Keys.CONTROL);
			builder.build().perform();		
	}
	

	/**
	 * This method is to fetch data from Ui and store fetched data into given
	 * property file
	 * 
	 * @param element
	 * @param propertyKeyword
	 * @param fileName
	 */
	public void storeGivenTextInTempDataFile(String data, String propertyKeyword) {
		DataIO.overwritePropertiesFile(propertyKeyword, data, "TempData");
	}

	/**
	 * this method is get text from each element of list and verify given text
	 * is available or not
	 * 
	 * @param textToVerify
	 */
	public boolean getTextFormElementsListAndMatchGivenString(List<WebElement> elementlist, String textToVerify) {
		for (WebElement elem : elementlist) {
			if (elem.getText().trim().equalsIgnoreCase(textToVerify.trim())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method is for verify the text from list.
	 */
	public boolean verifyTextInGivenList(String text, List<String> listOfText) {
		for (String value : listOfText) {
			if (value.trim().equalsIgnoreCase(text)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * This method is to refresh the page.
	 */
	public void pagerefresh() {
		System.out.println("In page refresh");
		driver.navigate().refresh();
	}

	/**
	 * This method is for verify the values in given list of elements.
	 */
	public boolean verifyGivenValuesInDropdown(List<WebElement> elements,
			List<String> values) {
		ArrayList<String> subStatusDropDownValues = new ArrayList<String>();
		boolean flag = true;
		for (WebElement temp : elements) {
			subStatusDropDownValues.add(temp.getText().trim());
		}

		for (String subStatus : values) {
			if (verifyTextInGivenList(subStatus, subStatusDropDownValues) == false) {
				System.out.println(subStatus
						+ "  Value is not available in dropdown");
				flag = false;
			}
		}
		return flag;
	}

	public void scrollDownPage() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
	}

	public static String getDayForGivenDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		Date date1 = null;
		try {
			date1 = dateFormat.parse(date);
			dateFormat.applyPattern("EEE");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateFormat.format(date1);
	}
	public String get_SelectedOption(WebElement element) {
		Select select = new Select(element);
		WebElement selectedElement = select.getFirstSelectedOption();
		String selectedOption = selectedElement.getText();
	return selectedOption;
	}

	//public boolean matchelement(String locator,String filename,String val2)
	
	//{
	//	if (getElement(locator, filename).getText().trim().equals(val2))
	//	{
	//		return true;
	//	} else 
	//	{
		//	return false;
		//}
	
	public void compareMethod(String elementName1,String fileName1,String elementName2,String fileName2)
	 {
	    WebElement expected = getElement(elementName1, fileName1);
	    WebElement actual = getElement(elementName2, fileName2);
	    if(expected == actual) 
	       {
	         System.out.println("verified");
	       } 
	    else
	       {
	    	System.out.println("not matched");
	       }
	 } 
		
    }
	
