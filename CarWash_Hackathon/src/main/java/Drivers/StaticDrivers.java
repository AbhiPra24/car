package Drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StaticDrivers {
	// This function accepts "browser" parameter from properties file and run the project from the browser specified in the local driver
	public WebDriver getDriver(String browser) {
    	WebDriver driver = null;
    	if(browser.equalsIgnoreCase("chrome")) {
    		
    		// Setting up WebDriver for Chrome
    		WebDriverManager.chromedriver().setup();
    		
    		// Creating Options for chrome driver
    		ChromeOptions options = new ChromeOptions();
    		options.addArguments("start-maximized");
    		options.addArguments("--disable-blink-features=AutomationControlled");
    		options.addArguments("--disable-notifications");
    		driver = new ChromeDriver(options);	
    	}
    	else if(browser.equalsIgnoreCase("firefox")) {
    		
    		// Setting up WebDriver for Firefox
    		WebDriverManager.firefoxdriver().setup();
    		
    		// Creating Options for firefox driver
    		FirefoxOptions options = new FirefoxOptions();
    		options.addArguments("start-maximized");
    		options.addArguments("--disable-blink-features=AutomationControlled");
    		options.addArguments("--disable-notifications");
    		driver = new FirefoxDriver(options);
    	}
    	return driver;
	}
}
