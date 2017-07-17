package actions;
import java.util.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import actions.BaseActions;

public class MidTermplan_1 extends BaseActions{
	
public WebDriver driver;
	
    
    
	public MidTermplan_1(WebDriver driver){
		super(driver);
		this.driver = driver;
	}
	 
	/** Following 'AddPlan_Opp' TC
	 * Create Mid term plan at Add/Modify plan on Opportunity
	 */
	public void click_midplan_Add_Modify_Button()
	{   hardWait(20);
		refresh();
	    hardWait(3);
	    refresh();
		hardWait(2);
		click("Add_modify_PlanButton", "AddPlansOpps");
	
		switchToFrame(getElement("frame_id", "AddPlansOpps"));
		hardWait(2);
		for(WebElement checkbox : getElements("midplan", "AddPlansOpps"))
	     {
		    hardWait(1);
		    checkbox.click();
	     }
		
	    click("next_midplan","AddPlansOpps");
	    hardWait(10);
	    switchToDefaultContent();	
	}
	public void planSelect_midterm() 
	{
		click("Plan_selection_Midplan","AddPlansOpps");
		hardWait(1);
		switchToDefaultContent();
		//click("next_onplan","AddPlansOpps");
		click("DD_product", "AddPlansOpps");
		click("product_catalog","AddPlansOpps");
		hardWait(3);
		click("first_checkbox","AddPlansOpps");
	
		   /* for(WebElement checkbox : getElements("list_checkbox", "AddPlansOpps"))
		     {
			    hardWait(1);
			    checkbox.click();
		     }*/
		  click("Plan_Save","AddPlansOpps");   
		  switchToDefaultContent();
		  hardWait(2);
	}
	
	public void midtermplan_save() 
	{
		 //writeTextInto("Unit_update","AddPlansOpps",Unit_replaced,Unit_update);
		 //enterafterselect("Unit_update", "AddPlansOpps");
		 hardWait(3);
		 System.out.println("Clicking Save Button");
		 click("updated_plan","AddPlansOpps");
		  System.out.println("Clicked Save Button");
		  hardWait(8);
		  //switchToDefaultContent();
		
		 click("Commit","AddPlansOpps");
		 hardWait(8);
		 System.out.println("Clicking comit Button");
		click("Comit_ok","AddPlansOpps");
		System.out.println("Clicked comit Button");
		hardWait(20);
		
		
		 }
	}
	

