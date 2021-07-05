package Utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
	public void screenshot(WebDriver driver) throws IOException {
		// Implementing Screenshot functionality and specifying path according to specific class
		
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		// Screenshot functionality if equals Car Wash Service
		if(driver.getTitle().equalsIgnoreCase("Car Wash Service")) {
			FileUtils.copyFile(screenshotFile,new File(".//ScreenShots//"+"CarWashServicePageInfo"+".png"));
		}
		
		// Screenshot functionality if equals FreeListing
		else if(driver.getTitle().equalsIgnoreCase("FreeListing")) {
			FileUtils.copyFile(screenshotFile,new File(".//ScreenShots//"+"FreeListingPageInfo"+".png"));
		}
		
		// Screenshot functionality if equals Fitness Page
		else if(driver.getTitle().equalsIgnoreCase("Fitness Page")) {
			FileUtils.copyFile(screenshotFile,new File(".//ScreenShots//"+"FitnessPageInfo"+".png"));
		}
		
	}
}
