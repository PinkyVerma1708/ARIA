package utils;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Jrani
 *
 */
public class SeleniumWait {
	
	public WebDriverWait wait;
	protected WebDriver driver;
	public int timeout;
	
	public SeleniumWait(WebDriver driver, int timeout){
		this.driver = driver;
		this.wait = new WebDriverWait(driver, timeout);
	}
	
	public WebElement waitForElementToBeAppear(WebElement element){
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	 public WebElement getWhenVisible(By locator) {
	        WebElement element;
	        element = wait.until(ExpectedConditions
	                .visibilityOfElementLocated(locator));
	        return element;
	    }
	    
	    public WebElement getWhenClickable(By locator) {
	        WebElement element;
	        element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	        return element;
	    }
	    
	    
	    public boolean waitForPageTitleToBeExact(String expectedPagetitle) {
	        return wait.until(ExpectedConditions.titleIs(expectedPagetitle));
	    }
	    
	    public boolean waitForPageTitleToContain(String expectedPagetitle) {
	        return wait.until(ExpectedConditions.titleContains(expectedPagetitle));
	    }
	    
	    public WebElement waitForElementToBeVisible(WebElement element) {
	        return wait.until(ExpectedConditions.visibilityOf(element));
	    }
	    
	    public void waitForFrameToBeAvailableAndSwitchToIt(By locator) {
	        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	    }
	    
	    public List<WebElement> waitForElementsToBeVisible(List<WebElement> elements) {
	        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	    }
	    
	    public boolean waitForElementToBeInVisible(By locator) {
	        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	    }
	    
	    public WebElement waitForElementToBeClickable(WebElement element) {
	        return wait.until(ExpectedConditions.elementToBeClickable(element));
	    }
	    
	     public void clickWhenReady(By locator) {
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	        element.click();
	    }


	    public void waitForMsgToastToDisappear() {
	        int i = 0;
	        resetImplicitTimeout(1);
	        try {
	            while (driver.findElement(By.className("toast-message")).isDisplayed() && i <= timeout) {
	                hardWait(1);                
	                i++;
	                System.out.println("i==="+ i);
	            }
	        } catch (Exception e) {
	        }
	        resetImplicitTimeout(timeout);        
	    }
	    
	    public void waitForElementToDisappear(WebElement element) {
	        int i = 0;
	        resetImplicitTimeout(2);
	        try {
	            while (element.isDisplayed() && i <= timeout) {
	                hardWait(1);                
	                i++;
	            }
	            System.out.println("[Info:] Element "+element+" disappeared in "+ i +" Seconds");
	        } catch (Exception e) {
	        }
	        resetImplicitTimeout(timeout);        
	    }
	    
	    public void waitForElementToAppear(WebElement element) {
	        int i = 0;
	        resetImplicitTimeout(2);
	        try {
	            while (!(element.isDisplayed()) && i <= timeout) {
	                hardWait(1);                
	                i++;
	            }
	            System.out.println("[Info:] Element "+element+" appeared in "+ i +" Seconds");
	        } catch (Exception e) {
	        }
	        resetImplicitTimeout(timeout);        
	    }
	    
	    public void waitForExactValueOfElement(WebElement element, String ExpectedText) {
	        int i = 0;
	        resetImplicitTimeout(1);
	        try {
	            while (!((element.getText()).equals(ExpectedText)) && i <= timeout) {
	                hardWait(1);                
	                i++;
	               // System.out.println(i + " Seconds");
	            }
	         
	        } catch (Exception e) {
	        	System.out.println();
	        }
	        resetImplicitTimeout(timeout);        
	    }
	    
	    public void resetImplicitTimeout(int newTimeOut) {
	        try {
	            driver.manage().timeouts().implicitlyWait(newTimeOut, TimeUnit.SECONDS);
	        } catch (Exception e) {	
	        }
	    }

	    // TODO Implement Wait for page load for page synchronizations
	    public void waitForPageToLoadCompletely() {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*")));
	    }

	    public void hardWait(int seconds) {
	        try {
	            Thread.sleep(seconds * 1000);
	        } catch (InterruptedException ex) {
	            ex.printStackTrace();
	        }
	    }
	    
	    
	    
	    
}