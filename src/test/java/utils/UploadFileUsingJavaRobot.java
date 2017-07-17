package utils;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
 
public class UploadFileUsingJavaRobot{
  
 public static void setClipboardData(String string) {
    StringSelection stringSelection = new StringSelection(string);
    Toolkit.getDefaultToolkit().getSystemClipboard()
             .setContents(stringSelection, null);
 }
 
 public static void main(String[] args) { 
   
  String filepath = "path of the file to upload";  
  setClipboardData(filepath);
 
  WebDriver driver = new FirefoxDriver();
  WebElement fileInput = driver.findElement(By.name("uploadFileInput"));
  fileInput.click();
 
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
   
  driver.quit();
 }
 
}
