package JustDialPages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class FreeListingPage {
	// This function accepts 3 input parameters that are Webdriver object to interact with browsers and Object of ExtentTest and Extent report

	public String Message(WebDriver driver,ExtentTest test,ExtentReports extent) throws IOException {
	    // In this function we try to opt for free listing but enter invalid number and check for error
		try {
			driver.findElement(By.id("h_flist")).click();
			driver.findElement(By.id("fcom")).sendKeys("raghav car wash");
			driver.findElement(By.xpath("//*[@id=\"fmb0\"]")).sendKeys("12345");
			driver.findElement(By.xpath("//*[@id=\"add_div0\"]/div[3]/button")).click();
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.pollingEvery(Duration.ofSeconds(5));
			String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"intial_div\"]/span[2]"))).getText();
			
			//Creating TestCase name for Extent Report
			test = extent.createTest("Listing Page");
	  		
			// Creating Log,Info and Pass extent. If the follwing test case gets "passed" extent report will be generated.
	  		test.log(Status.INFO, "This step shows usage of log,info");
			test.info("This test shows Listing Page and entering wrong value and printing on Console");
			test.pass("Passed",MediaEntityBuilder.createScreenCaptureFromPath("screenshot4.png").build());

			// Returning Output Message to Testng file for consoling output.
			return message;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// If Test Case Fails then "failed" extent report will be generated
			test.fail("Failed",MediaEntityBuilder.createScreenCaptureFromPath("screenshot4.png").build());
		}
		return null;
  }
}
