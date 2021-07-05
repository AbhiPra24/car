package JustDialPages;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


import Drivers.GridDrivers;
import Drivers.StaticDrivers;
import Utils.PropertiesFiles;
import Utils.ScreenShot;

public class Main {
 
  WebDriver driver;
  String getBrowser;
  String getWebsiteURL;
  String getInputLocation;
  String getInputValue;
  String getDriverSetup;
  ExtentHtmlReporter htmlReporter;
  ExtentReports extent;
  ExtentTest test;
  @BeforeSuite
	public void setup() throws InterruptedException, IOException {
	  	
	    //Creating extent report 
	    htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/ExtentReport/extenttt.html");
	    extent = new ExtentReports();
	    extent.attachReporter(htmlReporter);
	    
	    // Setting up basic configuration for extent report
	    htmlReporter.config().setDocumentTitle("Automation Report");
	    htmlReporter.config().setReportName("Functional Report");
	    
	    
	    //Creating TestCase name for Extent Report
	    test = extent.createTest("SettingUp Drivers");
	    
	    // Data is extracted from Properties File
	    try {
			PropertiesFiles file = new PropertiesFiles();
			getBrowser = file.properties("getBrowser");
			getWebsiteURL = file.properties("getWebsiteURL");
			getInputLocation = file.properties("getInputLocation");
			getInputValue = file.properties("getInputValue");
			getDriverSetup = file.properties("getDriverSetup");
			
			// Local Driver
			if(getDriverSetup.equalsIgnoreCase("NormalDriverSetup")) {
				StaticDrivers getWebDriver = new StaticDrivers();
				driver = getWebDriver.getDriver(getBrowser);
			}
			// Selenium Grid
			else if(getDriverSetup.equalsIgnoreCase("GridDriverSetup")) {
				GridDrivers getGridDriver = new GridDrivers();
				driver = getGridDriver.getGridDriver(getBrowser);
			}
			
			driver.get(getWebsiteURL);
			driver.manage().window().maximize();
			
			// Creating Log,Info and Pass extent. If the follwing test case gets "passed" extent report will be generated
			test.log(Status.INFO, "This step shows usage of log,info");
			test.info("This test shows setting up drivers and getting URL from user");
			test.pass("Passed",MediaEntityBuilder.createScreenCaptureFromPath("screenshot1.png").build());
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			// If Test Case Fails then "failed" extent report will be generated
			test.fail("Failed",MediaEntityBuilder.createScreenCaptureFromPath("screenshot1.png").build());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			// If Test Case Fails then "failed" extent report will be generated
			test.fail("Failed",MediaEntityBuilder.createScreenCaptureFromPath("screenshot1.png").build());
			e.printStackTrace();
		}
		
	}
  	@Test(priority = 0)
  	public void searchTextBoxAndRemoveAd() throws IOException {
  		Actions actions = new Actions(driver);
		
  		//Creating TestCase name for Extent Report
  		test = extent.createTest("Search TextBox and RemoveAd");
  		
  		
		try {
			driver.findElement(By.id("city")).sendKeys(getInputLocation);
			driver.findElement(By.id("srchbx")).sendKeys(getInputValue, Keys.ENTER);
			
			// Explicit Wait to avoid "ElementNotTracable" Error
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.pollingEvery(Duration.ofSeconds(5));
			WebElement cross = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"best_deal_div\"]/section/span")));
			actions.moveToElement(cross).click().perform();
			driver.navigate().refresh();

			
			// Function for performing Screenshot
			ScreenShot screenShotPage = new ScreenShot();
			screenShotPage.screenshot(driver);
			
			// Creating Log,Info and Pass extent. If the follwing test case gets "passed" extent report will be generated
			test.log(Status.INFO, "This step shows usage of log,info");
			test.info("This test shows searching textbox and removind Ad");
			test.pass("Passed",MediaEntityBuilder.createScreenCaptureFromPath("screenshot2.png").build());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			// If Test Case Fails then "failed" extent report will be generated
			test.fail("Failed",MediaEntityBuilder.createScreenCaptureFromPath("screenshot2.png").build());
		}
		
  	}
  // Task 1 :The data Extracted from the website and Stored in Excel is printed
  	@Test(priority = 1)
  	public void CarServiceInfoSearching() throws IOException {
  		
  		//Creating object of CarServiceInfoPage class.
  		CarServiceInfoPage helper = new CarServiceInfoPage();
  		
  		// Creating object for consoling the output
  		Object[][] output = new Object[10][3];
  		
  		output = helper.carServiceInfoPage(driver,test,extent);
  		
  		int row = output.length;
  		
  		// Consoling the output
  		for(int i=0;i<row;i++) {
  					if(output[i][0] != null) {
  			    	System.out.print("Name: "+output[i][0]);
  	  				System.out.println();
  	  				System.out.print("Address: "+output[i][1]);
  	  				System.out.println();
  	  				System.out.print("PhoneNumber: "+output[i][2]);
  	  				
  	  				System.out.println();
  	  				System.out.println();
  				}
  		}
  		
  	}
  	//Task 2 :Function shows Error message in free Listing 
  	@Test(priority = 2)
	public void ListingPage() throws IOException{
  		// Creating object of FreeListingPage class.
  		FreeListingPage freeListingJava = new FreeListingPage();
  		
  		// Getting err Message and consoling it.
		String ErrMessage = freeListingJava.Message(driver,test,extent);
		System.out.println("Error Message After entering wrong value in Input Field: " + ErrMessage);
		System.out.println();
		
		
		// Function for performing Screenshot		
		ScreenShot screenShotPage = new ScreenShot();
		screenShotPage.screenshot(driver);
		
		driver.navigate().back();
	}
  	// Task 3 : Sub Categories of GYM 
  	@SuppressWarnings("static-access")
	@Test(priority = 3)
	public void FitnessPage() throws IOException {
		
		driver.navigate().back();
		
		// Creating object of FitnessPage class. 
		FitnessPage fitnessJava = new FitnessPage();
		
		//Creating object for consoling output
		Object[] output = new Object[20];
		
		//Getting object and copying into local object for consoling output
		output  = fitnessJava.fitness(driver,test,extent);
		
		
		// Loop for Consoling output
		for(int i=0;i<output.length;i++) {
			if(output[i] != null) {
				System.out.println(output[i]);
			}
		}
		
		// Function for performing Screenshot
		ScreenShot screenShotPage = new ScreenShot();
		screenShotPage.screenshot(driver);
  	}
  	@AfterSuite
	public void closeBrowser() throws IOException {
  		
  		//Creating TestCase name for Extent Report
  		test = extent.createTest("Closing Down Browser");
  		
  		// Creating Log,Info and Pass extent. If the follwing test case gets "passed" extent report will be generated
  		test.info("This test shows performs closing the browser");
		test.pass("Passed",MediaEntityBuilder.createScreenCaptureFromPath("screenshot6.png").build());
  		
		// Flusing the extent generated
  		extent.flush();
		driver.close();	
		driver.quit();
	}
}
