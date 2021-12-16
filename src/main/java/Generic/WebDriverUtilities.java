package Generic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.google.common.io.Files;

public class WebDriverUtilities implements AutoConstant{
	
	public void dropDown(WebElement ele, String text)
	{
		Select s=new Select(ele);
		s.selectByVisibleText(text);
	}
	public void deselectAll(WebElement ele)
	{
		Select s=new Select(ele);
		s.deselectAll();
	}
	public void mouseHover(WebDriver driver, WebElement target)
	{ 
		Actions a =new Actions(driver);
		a.moveToElement(target);
	}
	
	public void frame(WebDriver driver, int fameNo)
	{
		driver.switchTo().frame(fameNo);
	}
	
	public void switchBackframe(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	
	public void alertPopupAccept(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	public void alertPopupdismiss(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	public void switchTabs(WebDriver driver)
	{
		String parent = driver.getWindowHandle();
		Set<String> child = driver.getWindowHandles();
		child.remove(parent);
		for(String b:child)
		{
			driver.switchTo().window(b);
		}
	}
	
	public void doubleClickm(WebDriver driver, WebElement target)
	{
		Actions a = new Actions(driver);
		a.doubleClick(target).perform();
	}
	
	public void scrollBar(WebDriver driver, int x, int y)
	{
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("windows.scrollBy("+x+","+y+")");
	}
	

	public void compareWith(String actual,String expected) {
		Assert.assertEquals(actual, expected);
	}
	
	public void dragDropbtn(WebDriver driver,WebElement source,WebElement target) {
		Actions a=new Actions(driver);
		a.dragAndDrop(source, target).perform();
	}
	
	public void takeScreenShot(WebDriver driver,String className) throws IOException
	{
		String timestamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		//String time = timestamp.replace("_", ":");
		//Copy the screenshot on the desire location with different name using current date and time
		//FileUtils.copyFile(scrFile, new File("C:/shots/" + fileName+" "+timestamp+extension));
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		//File dest=new File("./Photo/"+""+className+"_"+timestamp+".png");
		File dest=new File(constant+""+photo+"_"+className+"_"+timestamp+".png");

		Files.copy(src, dest); 
	}
}
