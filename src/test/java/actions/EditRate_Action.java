package actions;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.DataIO;
//import org.openqa.selenium.WebElement;
import actions.BaseActions;
public class EditRate_Action extends BaseActions{
public WebDriver driver;
	 
    
	public EditRate_Action(WebDriver driver){
		super(driver);
		this.driver = driver;
	}
	/**
	 * @param args
	 */
	public void EditRate_selection()
	{

		List<WebElement> elementsList =getElements("","");
	
		getElementByExactText(elementsList, DataIO.get("",""));
		
		
	}
}
