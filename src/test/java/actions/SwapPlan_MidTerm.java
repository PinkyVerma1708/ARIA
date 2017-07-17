package actions;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;

import actions.BaseActions;

public final class SwapPlan_MidTerm extends BaseActions{
public WebDriver driver;
	
    
    
	public SwapPlan_MidTerm(WebDriver driver){
		super(driver);
		this.driver = driver;
	}
	/**
	 * @param args
	 */
	
	public void swapplan_config(String swapplan)
	{

		click("Plan_selection_Midplan","AddPlansOpps");
		hardWait(1);
		switchToDefaultContent();
		selectOptionByVisibleText("config_plan","AddPlansOpps", swapplan);
		switchToFrame(getElement("swap_frame", "AddPlansOpps"));
		hardWait(5);
		click("swap_plan","AddPlansOpps");
		click("swap_done","AddPlansOpps");
		click("swap_confirm","AddPlansOpps");
		hardWait(10);
		switchToDefaultContent();
		click("Plan_Save","AddPlansOpps");   
		 switchToDefaultContent();
		  hardWait(8);
	}
	public void swapplan_commit()
	{
		click("Commit","AddPlansOpps");
		 hardWait(10);
		 System.out.println("Clicking comit Button");
		click("Comit_ok","AddPlansOpps");
		System.out.println("Clicked comit Button");
		hardWait(30);
	}
}
