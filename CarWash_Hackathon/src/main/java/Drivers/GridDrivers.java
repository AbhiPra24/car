package Drivers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridDrivers {
	// This function accepts "browser" parameter from properties file and run the project from the browser specified in the Selenium Grid

public WebDriver getGridDriver(String browser) throws MalformedURLException {
		if(browser.equalsIgnoreCase("chrome")) {
			// DesiredCapabilities for Chrome
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			
			// RemoteUrl for Selenium Grid
			String remoteUrl = "http://172.19.168.101:4444/wd/hub";
			
			// Creating Remote WebDriver
			RemoteWebDriver driver = new RemoteWebDriver(new URL(remoteUrl),caps);
			
			// Creating Options and merging with DesiredCapabilities
			ChromeOptions options = new ChromeOptions();
    		options.addArguments("start-maximized");
    		options.addArguments("--disable-blink-features=AutomationControlled");
    		options.addArguments("--disable-notifications");
    		
    		// Creating Remote WebDriver for Chrome
    		driver = new ChromeDriver(options);
			return driver;
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			// DesiredCapabilities for Firefox
			DesiredCapabilities caps = DesiredCapabilities.firefox();
			
			// RemoteUrl for Selenium Grid
			String remoteUrl = "http://172.19.168.101:4444/wd/hub";
			
			// Creating Remote WebDriver
			RemoteWebDriver driver = new RemoteWebDriver(new URL(remoteUrl),caps);
			
			// Creating Options and merging with DesiredCapabilities
			FirefoxOptions options = new FirefoxOptions();
    		options.addArguments("start-maximized");
    		options.addArguments("--disable-blink-features=AutomationControlled");
    		options.addArguments("--disable-notifications");
    		
    		// Creating Remote WebDriver for Firefox
    		driver = new FirefoxDriver(options);
			return driver;
		}
		return null;
	}
}
