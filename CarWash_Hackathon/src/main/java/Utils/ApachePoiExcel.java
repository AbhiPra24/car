package Utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApachePoiExcel {
	public static void apachePoi(String Page,Object[][] obj) throws IOException {
		
		// Creating Workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(Page + " CenterData");
		
		
		// Determing "rows" and "Coloums" for creating ExcelData Sheet
		int rows = obj.length;
		int cols = obj[0].length;
		
		
		// Creating Rows and columns
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
		// Specifying Path for generating Excelfile Data
		String filePath = ".\\ExcelReport\\"+Page+".xlsx";
		FileOutputStream outstream = new FileOutputStream(filePath);
		workbook.write(outstream);
		outstream.close();
	}
}
