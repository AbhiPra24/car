package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFiles {
	public String properties(String input) throws IOException {
		//This function is created to ensure that whatever input user will give program will correspond to that 
		// particular value
		
		Properties propertiesFile = new Properties();
		FileInputStream inputStream = new FileInputStream(".\\Test-input\\testData.properties");
		propertiesFile.load(inputStream);
		
		// Property for Browser
		if(input.equalsIgnoreCase("getBrowser")) {
			return propertiesFile.getProperty("browser");
		}
		
		//Property for InputField
		else if(input.equalsIgnoreCase("getInputValue")) {
			return propertiesFile.getProperty("TextboxValue");
		}
		
		//Property for WebSite URL
		else if(input.equalsIgnoreCase("getWebsiteURL")) {
			return propertiesFile.getProperty("url");
		}
		
		//Property for InputLocation
		else if(input.equalsIgnoreCase("getInputLocation")) {
			return propertiesFile.getProperty("location");
		}
		
		//Property for DriverSetup
		else if(input.equalsIgnoreCase("getDriverSetup")) {
			return propertiesFile.getProperty("DriverSetup");
		}
		return null;
		
	}
}
