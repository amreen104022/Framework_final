package Generic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PropertyFile implements AutoConstant{
	public String getPropertyFileData(String key) throws FileNotFoundException, IOException
	
	{
		Properties p= new Properties();
		p.load(new FileInputStream(datafile));
		return p.getProperty(key);
	}
	//fetch the data from Excel.
		public String getdata(int sheet, int row, int cell) throws FileNotFoundException, IOException
		
		{
		XSSFWorkbook wb = new XSSFWorkbook(getPropertyFileData("sheet")); 
		XSSFSheet sh = wb.getSheetAt(sheet);
		
		int noOfRows = sh.getPhysicalNumberOfRows();
		
		//For Numeric value
		try
		{
			int val = (int) sh.getRow(row).getCell(cell).getNumericCellValue();
			String value = Integer.toString(val);
			
			return value;
		}
		//For Value value
		catch (Exception e) {
			
			String value = sh.getRow(row).getCell(cell).getStringCellValue();
			return value;

		}
	}
}
