package actions;

//import org.openqa.selenium.By;
//import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import actions.BaseActions;
public class AddPlan_Opp extends BaseActions {

    public WebDriver driver;
	
    
    
	public AddPlan_Opp(WebDriver driver){
		super(driver);
		this.driver = driver;
	}
	/**
	 * click_on_Add/Modify plan on Opportunity
	 */
	public void click_on_Add_Modify_Button(String Frame_Status) 
	{
		//WebElement Frame= driver.findElement(By.id("OverlayDialog"));
		click("Add_modify_PlanButton", "AddPlansOpps");
		hardWait(10);
		switchToFrame(getElement("frame_id", "AddPlansOpps"));
		
		//switchToFramebyIndex(0);
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		selectOptionByVisibleText("Frame1_plan", "AddPlansOpps", Frame_Status);
		click("Submit_frame","AddPlansOpps");
		switchToDefaultContent();		
	}
	
	public boolean verify_addmodify_page(String verification){
		return getTextAndMatchString("Add_modify_PageText", "AddPlansOpps", verification );
	}
	
	public boolean verify_Billingplan(String verification){
		return getTextAndMatchString("Add_modify_PageText", "AddPlansOpps", verification );
	}
	
	public void Billing_Groups(String Billing_Group,String Client_ID) 
	{
		click("Billing_groups_click","AddPlansOpps");
		switchToDefaultContent();	
		writeTextInto("Billing_groups_Input", "AddPlansOpps", Billing_Group);
		//writeTextInto("Client_Defined_IdentifierInput", "AddPlansOpps", Client_ID);
		click("Billing_Save","AddPlansOpps");
		switchToDefaultContent();
		
	}
	public boolean verify_planselection(String verification){
		return getTextAndMatchString("PlanSelection_page Text", "AddPlansOpps", verification );
	}
	//public void plan_selection(String CurrencyOption) 
	public void plan_selection() 
	{
		click("PlanSelection_click","AddPlansOpps");
		hardWait(1);
		switchToDefaultContent();
		//click("next_onplan","AddPlansOpps");
		hardWait(1);
		//selectOptionByVisibleText("CurrencyOption", "AddPlansOpps", CurrencyOption);
		//pressenter();
		click("first_checkbox","AddPlansOpps");
		
	
		/*    for(WebElement checkbox : getElements("list_checkbox", "AddPlansOpps"))
		     {
			    hardWait(1);
			    checkbox.click();
		     }*/
		  click("Plan_Save","AddPlansOpps");   
		  switchToDefaultContent();
		  hardWait(2);
		  
	}
	public void plan_save() 
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

