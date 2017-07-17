/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setup;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import utils.BasePage;

/**
 * @author JRani
 *
 */
public class Screenshot {

	WebDriver driver;
	String testname=BasePage.getToken("test");
	String screenshotPath = "Screenshots";

	public Screenshot(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This method is to take sceen shot if test case got failed And it store
	 * captured screen shot file in Screenshots folder within framework
	 * 
	 * @param result
	 */
	public void screenShotOnTestFail(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			File file = new File(System.getProperty("user.dir")+ File.separator + screenshotPath);
			if (!file.isDirectory()){
				file.mkdir();
			}
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String saveImgFile = System.getProperty("user.dir")+ File.separator + screenshotPath + File.separator
					+ BasePage.getCurrentDateTime("dd_MMM_yyyy_hh_mm_ssaa")+"@"+ result.getMethod().getMethodName()
					+ ".jpg";
			try {
				FileUtils.copyFile(scrFile, new File(saveImgFile));
				System.out.println("ScreenShotTaken");
			} catch (IOException e) {
				System.out.println("Error in saving file");
				e.printStackTrace();
			}
		}

	}

	public void takeScreenshot() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_a");
		Date date = new Date();
		String date_time = dateFormat.format(date);
		File file = new File(System.getProperty("user.dir") + File.separator
				+ screenshotPath + File.separator + this.testname
				+ File.separator + date_time);
		boolean exists = file.exists();
		if (!exists) {
			new File(System.getProperty("user.dir") + File.separator
					+ screenshotPath + File.separator + this.testname
					+ File.separator + date_time).mkdir();
		}

		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			String saveImgFile = System.getProperty("user.dir")
					+ File.separator + screenshotPath + File.separator
					+ this.testname + File.separator + date_time
					+ File.separator + "screenshot.png";
			System.out.println("Save Image File Path : " + saveImgFile);
			FileUtils.copyFile(scrFile, new File(saveImgFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
