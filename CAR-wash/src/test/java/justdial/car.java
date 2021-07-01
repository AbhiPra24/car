package justdial;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class car {
	WebDriver driver; 
	@Parameters("browser") 
	@BeforeTest 
	public void setup(String browser) throws Exception{ 

	//Check if parameter passed from TestNG is 'firefox' 
	if(browser.equalsIgnoreCase("firefox")){ 

	//create firefox instance 
		System.setProperty("webdriver.gecko.driver","C:\\Users\\abhin\\Downloads\\Selenium\\geckodriver-v0.29.1-win64\\geckodriver.exe");   
		driver = new FirefoxDriver(); 
		System.out.println("Firefox browser is opened"); 

	   } 

	//Check if parameter passed as 'chrome' 

	else if(browser.equalsIgnoreCase("chrome")){ 

	//set path to chromedriver.exe 

	System.setProperty("webdriver.chrome.driver","C:\\Users\\abhin\\Downloads\\Selenium\\chromedriver_win32\\chromedriver.exe"); 

	//create chrome instance 
	ChromeOptions options = new ChromeOptions();
	
	options.addArguments("--disable-blink-features=AutomationControlled");

	options.addArguments("--disable-notifications");
	driver = new ChromeDriver(options); 

	 System.out.println("chrome browser is opened"); 

	} 

	else{ 

	//If no browser passed throw exception 

	throw new Exception("Browser is not correct"); 

	} 

	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 

	} 
	
	@Test (priority=1)
	public void initiate() {
		
		// set url
		String URL = "https://www.justdial.com/";
	
		
	
		//To maximize the Browser
		driver.manage().window().maximize();
				
		// send url to driver
		driver.get(URL);
		
		//To maximize the Browser
		driver.manage().window().maximize();
		
		 
		   
	}
	
	@Test (priority=2)
	public void search() {
	
		//driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); 

		// Searching for Mobiles 
		driver.findElement(By.xpath("//*[@id=\"srchbx\"]")).sendKeys("car wash in delhi");
			//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); 

		driver.findElement(By.xpath("//*[@id=\"srIconwpr\"]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"distdrop_rat\"]/span")).click();
		
		List<WebElement> elements = driver.findElements(By.className("store-details"));
	//	String[] stringArray = new String[0];
		float rat[]=new float[elements.size()]; 

		for(int i=0;i<elements.size();i++) {
			String temp =elements.get(i).findElement(By.className("green-box")).getText();
			rat[i]= Float.parseFloat(temp);
			String Vote = elements.get(i).findElement(By.xpath("//*[@id=\"bcard"+i+"\"]/div[1]/section/div[1]/p[1]/a/span[3]")).getText();
			String numberOnly= Vote.replaceAll("[^0-9]", "");
			int VoteInteger = Integer.parseInt(numberOnly);
								
				
			if(rat[i]>=4.0 && VoteInteger > 20) {
				System.out.print("Name: ");
				System.out.print(elements.get(i).findElement(By.className("lng_cont_name")).getText());
				System.out.println();
				System.out.print("Address: ");
				System.out.print(elements.get(i).findElement(By.className("cont_sw_addr")).getText());
				System.out.println();
			//	System.out.print("PhoneNumber: ");
			//	System.out.print(stringArray[i]);
				System.out.println();
				System.out.println();
			}}
		
	}
	
/*	@Test (priority=3)
	public void listing() {
	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); 

		driver.findElement(By.xpath("//*[@id=\"bcard0\"]/div[1]/section/div[2]/ul/li/a")).click();
		
		driver.findElement(By.xpath("//*[@id=\"bd_name\"]")).sendKeys("random name");
		driver.findElement(By.xpath("//*[@id=\"bd_mobile\"]")).sendKeys("wrong detail");
		driver.findElement(By.xpath("//*[@id=\"best_deal_div\"]/section/section/section/form/aside/p[4]/button")).click();
		
	
		Alert alert = driver.switchTo().alert();		
        String alertMessage= driver.switchTo().alert().getText();		
        System.out.println(alertMessage);

		}*/
	
/*	@AfterTest 
	public void Closebrowser() { 
		driver.close(); 

	}*/

}
