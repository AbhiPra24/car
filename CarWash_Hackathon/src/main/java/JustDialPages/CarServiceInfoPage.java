package JustDialPages;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;


public class CarServiceInfoPage {
	// This function accepts 3 input parameters that are Webdriver object to interact with browsers and Object of ExtentTest and Extent report
	public Object[][] carServiceInfoPage(WebDriver driver,ExtentTest test,ExtentReports extent) throws IOException {
		
		int flag = 0;
		String b = "";
		int rowCount = 1;
		
		// Creating Excel workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("CarService CenterData");
		
		// Creating Row1 for Excel Data Sheet
		Object[][] obj = new Object[20][3];
		obj[0][0] = "Name";
		obj[0][1] = "Address";
		obj[0][2] = "PhoneNumber";
		
		
		Object[][] output = new Object[10][3];
		int outputrow = 0; 
		
		// In this try Block , Name ,Address, Phone number of the car wash services are stored in the excel file if their votes are >20 and rating is >4.0
		try {
			List<WebElement> elements = driver.findElements(By.className("store-details"));
			String[] phone = null;
			String phoneString = null;
			String[] stringArray = new String[0];
			int j = 0;
			float rat[]=new float[elements.size()]; 
			for(int i=0;i<elements.size();i++) {
				String temp =elements.get(i).findElement(By.className("green-box")).getText();
				rat[i]= Float.parseFloat(temp);
				String Vote = elements.get(i).findElement(By.xpath("//*[@id=\"bcard"+i+"\"]/div[1]/section/div[1]/p[1]/a/span[3]")).getText();
				
				// Java function for extracting digits from String.
				String numberOnly= Vote.replaceAll("[^0-9]", "");
				int VoteInteger = Integer.parseInt(numberOnly);
				List<WebElement> PhoneNumberString =  driver.findElements(By.className("mobilesv"));
				if(flag == 0) {
					for(int k = 0;k<PhoneNumberString.size();k++) {
						
						// Splits the InputString Stream by "-". 
						phoneString = PhoneNumberString.get(k).getAttribute("class").split("-")[1];
						MobileClass r = new MobileClass();
						String temp2 = r.mobileClass(phoneString);
						b += temp2;
					}
					flag = 1;
					while(j<b.length()) {
						// If the phone number starts from "0". This loop will extract 10 digits from string.
						if(b.charAt(j) == '0') {
							int count = 0;  
							String var = "";
							while(j<b.length() && count <= 10) {
								var += b.charAt(j); 
								j++;count++;
							}
							stringArray = Arrays.copyOf(stringArray,stringArray.length+1);
							stringArray[stringArray.length - 1] = var;
						}
						// If the phone number starts from "+". This loop will extract 15 digits from string.
						else if(b.charAt(j) == '+') {
							int count = 0;
							String var = "";
							while(j<b.length() && count <= 15) {
								var += b.charAt(j); 
								j++;count++;
							}
							stringArray = Arrays.copyOf(stringArray,stringArray.length+1);
							stringArray[stringArray.length - 1] = var;
						}
					}
				}
				// Sorting out services according to the conditions provided
				if(rat[i]>=4.0 && VoteInteger > 20) {
					output[outputrow][0] = elements.get(i).findElement(By.className("lng_cont_name")).getText();
					obj[rowCount][0] = elements.get(i).findElement(By.className("lng_cont_name")).getText();
					
					output[outputrow][1] = elements.get(i).findElement(By.className("cont_sw_addr")).getText();
					obj[rowCount][1] = elements.get(i).findElement(By.className("cont_sw_addr")).getText();
					
					output[outputrow][2] = stringArray[i];
					obj[rowCount][2] = stringArray[i];
					rowCount++;
				}
				outputrow++;
			}
			
			//Creating TestCase name for Extent Report
			test = extent.createTest("Car Service Info Searching");
	  		
			// Creating Log,Info and Pass extent. If the follwing test case gets "passed" extent report will be generated
	  		test.log(Status.INFO, "This step shows usage of log,info");
			test.info("This test shows searching car wash service and printing on Console");
			test.pass("Passed",MediaEntityBuilder.createScreenCaptureFromPath("screenshot3.png").build());
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// If Test Case Fails then "failed" extent report will be generated
			test.fail("Failed",MediaEntityBuilder.createScreenCaptureFromPath("screenshot3.png").build());
		}
		
		// Determing "rows" and "Coloums" for creating ExcelData Sheet
		int rows = obj.length;
		int cols = obj[0].length;
		
		//Loop for creating ExcelData Sheet
		for(int k=0;k<rows;k++) {
			XSSFRow row = sheet.createRow(k);
			for(int p=0;p<cols;p++) {
				XSSFCell cell = row.createCell(p);
				Object value = obj[k][p];
				if(value instanceof String) {
					cell.setCellValue((String)value);
				}
				if(value instanceof Integer) {
					cell.setCellValue((Integer)value);
				}
			}
		}
		
		// Setting path value for generating excel sheet data for given test case
		String filePath = ".\\ExcelReport\\JustDialService.xlsx";
		FileOutputStream outstream = new FileOutputStream(filePath);
		workbook.write(outstream);
		outstream.close();
		
		//Sending the output object to testng file for further evaluation
		return output;
	}
}
