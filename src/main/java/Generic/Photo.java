package Generic;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class Photo implements AutoConstant{

	public String getPhoto(WebDriver driver, String name) throws IOException
	{
		Date d=new Date();
		String Date = d.toString().replaceAll("-", "/");
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File(photopath);
		Files.copy(src, dest);
		return Date;
		
	} 
}
