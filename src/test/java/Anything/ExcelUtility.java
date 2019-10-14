package Anything;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static DataFormatter datafor = new DataFormatter();
	public static Object [] [] read_excel_data() throws IOException {
		FileInputStream fis = new FileInputStream("C:\\Users\\pdc4-training.pdc4\\Desktop\\testdata.xlsx");
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet("Sheet1");
		int rowCount = sheet.getPhysicalNumberOfRows();
		row = sheet.getRow(0);
		int colCount = row.getPhysicalNumberOfCells();
		Object[][] exceldata = new Object[rowCount][colCount];
		for(int i=0;i<rowCount;i++) {
			row = sheet.getRow(i);
			for(int j=0;j<colCount;j++) {
				XSSFCell cell = row.getCell(j);
				if(cell!=null) {
					exceldata[i][j]= datafor.formatCellValue(cell);
				}
			}
		
	}
		return exceldata;
	}}
