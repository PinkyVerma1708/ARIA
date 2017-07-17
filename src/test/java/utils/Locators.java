package utils;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author neerajagarwal
 *
 */
public class Locators extends LogsProgrammatic{

	public WebDriver driver;
	public static SeleniumWait wait;
	private ArrayList<String> locatorValues = null;
	public Locators(WebDriver driver){
		this.driver = driver;
		wait = new SeleniumWait(driver, 60);
	}

	/**
	 * Find element BY using object type and value
	 * @param objectName
	 * @param objectType
	 * @return
	 * @throws Exception
	 */
	private By getObject(String objectName, String fileName, String replacement) throws Exception{
		locatorValues = DataIO.getValueFromExcelFile(objectName, fileName);
		if(locatorValues.get(1).trim().equalsIgnoreCase("XPATH")){
			return By.xpath(locatorValues.get(2).trim().replaceAll("textToReplace", replacement));
		}
		//find by class
		else if(locatorValues.get(1).trim().equalsIgnoreCase("CLASSNAME")){
			return By.className(locatorValues.get(2).trim().replaceAll("textToReplace", replacement));

		}
		//find by ID
		else if(locatorValues.get(1).trim().equalsIgnoreCase("ID")){
			return By.id(locatorValues.get(2).trim().replaceAll("textToReplace", replacement));

		}
		//find by name
		else if(locatorValues.get(1).trim().equalsIgnoreCase("NAME")){
			return By.name(locatorValues.get(2).trim().replaceAll("textToReplace", replacement));

		}
		//Find by css
		else if(locatorValues.get(1).trim().equalsIgnoreCase("CSS")){
			return By.cssSelector(locatorValues.get(2).trim().replaceAll("textToReplace", replacement));

		}
		//find by link
		else if(locatorValues.get(1).trim().equalsIgnoreCase("LINK")){
			return By.linkText(locatorValues.get(2).trim().replaceAll("textToReplace", replacement));

		}
		//find by partial link
		else if(locatorValues.get(1).trim().equalsIgnoreCase("PARTIALLINK")){
			return By.partialLinkText(locatorValues.get(2).trim().replaceAll("textToReplace", replacement));       
		}
		else if(locatorValues.get(1).trim().equalsIgnoreCase("LINKTEXT")){
			return By.linkText(locatorValues.get(2).trim().replaceAll("textToReplace", replacement));

		}
		else
		{
			throw new Exception("Wrong object type");
		}
	}

	/**
	 * To get the webElement from excel file
	 * @param elementName - name of locator/element
	 * @param fileName - file name in which locator saved
	 * @return - Web element
	 * @throws Exception
	 */
	public WebElement getElement(String elementName, String fileName){
		waitForLoad(driver);
		WebElement ele = null;
		try
		{
			ele = wait.waitForElementToBeVisible(driver.findElement(getObject(elementName, fileName, ""))); 	
			ele = wait.waitForElementToBeClickable(driver.findElement(getObject(elementName, fileName, ""))); 	

		}catch(Exception e){
			e.printStackTrace();
		}
		return ele;
	}
	
	
	/**
	 * To get the webElement from excel file
	 * @param elementName - name of locator/element
	 * @param fileName - file name in which locator saved
	 * @return - Web element
	 * @throws Exception
	 */
	public WebElement getElementWithoutWait(String elementName, String fileName){
		waitForLoad(driver);

		WebElement ele = null;
		try
		{
			ele = driver.findElement(getObject(elementName, fileName, "")); 	
		}catch(Exception e){
		}
		
	return ele;
	}
	
	/**
	 * To get the webElement from excel file
	 * @param elementName - name of locator/element
	 * @param fileName - file name in which locator saved
	 * @return - Web element
	 * @throws Exception
	 */
	public WebElement getElementWithoutWait(String elementName, String fileName, String textToReplace){
		waitForLoad(driver);

		WebElement ele = null;
		try
		{
			ele = driver.findElement(getObject(elementName, fileName, textToReplace)); 	
		}catch(Exception e){
		}
		return ele;
	}

	/**
	 * To get the webElement from excel file
	 * @param elementName - name of locator/element
	 * @param fileName - file name in which locator saved
	 * @return - Web element
	 * @throws Exception
	 */
	public List<WebElement> getElementsWithoutWait(String elementToken, String fileName){
		List<WebElement> elements = null;
		try {
			elements = driver.findElements(getObject(elementToken, fileName, ""));
			return elements;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return elements;
	}

	/**
	 * To get the webElement from excel file
	 * @param elementName - name of locator/element
	 * @param fileName - file name in which locator saved
	 * @return - Web element
	 * @throws Exception
	 */
	public List<WebElement> getElementsWithoutWait(String elementToken, String fileName, String textToReplace){
		waitForLoad(driver);

		List<WebElement> elements = null;
		try {
			elements = driver.findElements(getObject(elementToken, fileName, textToReplace));
			return elements;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return elements;
	}
	
	/**
	 * To get the dynamic webElement from excel file
	 * @param elementName - name of locator/element
	 * @param fileName - file name in which locator saved
	 * @param replacement - this is for dynamic xpath
	 * @return - web element
	 * @throws Exception
	 */
	public WebElement getElement(String elementName, String fileName, String replacement){
		waitForLoad(driver);

		WebElement element = null;
		try
		{
			element = wait.waitForElementToBeAppear(driver.findElement(getObject(elementName, fileName, replacement))); 	
		}catch(Exception e){
			e.printStackTrace();
		}
		return element;
	}

	/**
	 * @param elementToken
	 * @param fileName
	 * @return
	 */
	protected List<WebElement> getElements(String elementToken, String fileName) {
		waitForLoad(driver);

		List<WebElement> elements = null;
		try {
			elements = wait.waitForElementsToBeVisible(driver.findElements(getObject(elementToken, fileName, "")));
			return elements;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return elements;
	}
	
	public void waitForLoad(WebDriver driver) {
  //new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
	          //  ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
	}
	
}

