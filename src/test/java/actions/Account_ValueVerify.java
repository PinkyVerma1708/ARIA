package actions;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import actions.BaseActions;

public class Account_ValueVerify extends BaseActions
{
    public WebDriver driver;
	
    public Account_ValueVerify(WebDriver driver)
    {
		super(driver);
		this.driver = driver;
	}
	
	public void Click_On_Accounts_Tab()
	{
		hardWait(3);
		click("Accounts_tab","HomePage");
		click("First_account","ValueVerification");
	}
	public boolean verify_MRR_for_Aria()
	{
		switchToFrame(getElement("Frame_AccountPlan", "ValueVerification"));
		String val3=getElement("MRR", "ValueVerification").getText();
		
		switchToDefaultContent();
		
        return getElement("Total_MRR", "ValueVerification").getText().trim().contains(val3);
        		
	}
	public boolean verify_TCV_for_Aria()
	{   
		
	   switchToFrame(getElement("Frame_AccountPlan", "ValueVerification"));
	   String val4=getElement("TCV", "ValueVerification").getText();
	
	   switchToDefaultContent();
	   
	   
	   return getElement("Total_TCV", "ValueVerification").getText().trim().equalsIgnoreCase(val4);
		//return getTextAndMatchValue(getElement("Total_TCV", "ValueVerification"), getElement("TCV", "ValueVerification"));
	}
	
	public boolean verify_ACV_for_Aria()
	{
		switchToFrame(getElement("Frame_AccountPlan", "ValueVerification"));
		String val5=getElement("ACV", "ValueVerification").getText();
		
		 switchToDefaultContent();
		return getElement("Total_ACV", "ValueVerification").getText().trim().contains(val5);
		
	}
	public boolean verify_ARR_for_Aria()
	{
		switchToFrame(getElement("Frame_AccountPlan", "ValueVerification"));
		String val6=getElement("MRR", "ValueVerification").getText();
		
		float val7=Float.parseFloat(val6);
		//System.out.println(val7);
	    
	    float val8=val7*12;
	   //System.out.println(val8);
	    
	    //String val9=Float.toString(val8);
	    String val9= String.format("%.2f", val8);
	    
		//System.out.println(val9);
		
		switchToDefaultContent();
		//System.out.println(getElement("Total_ARR", "ValueVerification").getText().trim().replaceAll(",", ""));
		
		return getElement("Total_ARR", "ValueVerification").getText().trim().replaceAll(",", "").equals(val9);
		
	}
		
}
