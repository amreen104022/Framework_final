package Script;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Generic.BaseClass;
import Generic.WebDriverUtilities;
import LoginPage.LoginLogout;
import Picture.ScreenShot;
import PomPages.dailyStockPage;
import PomPages.homePage;

public class dailyStock extends BaseClass{
	
	Date date = new Date();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String d = dateFormat.format(date);

	ScreenShot ss = new ScreenShot();
	dailyStockPage dp = new dailyStockPage(driver);
	homePage hp =new homePage(driver);
	WebDriverUtilities wd = new WebDriverUtilities();
	
	String className = this.getClass().getSimpleName();  
	
	@Test (priority = 1)
	public void login() throws FileNotFoundException, IOException, InterruptedException
	{

		LoginLogout ll = new LoginLogout();
		String uname = p.getPropertyFileData("salesmanUname");
		String pwd = p.getPropertyFileData("salesmanPassword");
		
		ll.LoginPage(driver, uname, pwd);

		Thread.sleep(3000);
		dp.item3.click();
		
		hp.getToggle();

		boolean dashboard = driver.findElement(By.xpath("//span[text()='Dashboard']")).isDisplayed();

		
		System.out.println("Dashboard : "+dashboard);
		Thread.sleep(4000);
		
//		if(dashboard==false)
//		{
		Thread.sleep(2000);
		//}
		
		hp.getDailyStockReport();

		Thread.sleep(3000);

	}
	@Test (priority = 2)
	//@Test (enabled = false)
	public void oppeningStock() throws InterruptedException, IOException
	{
		dp.getAddData();
		Thread.sleep(3000);

		WebElement date1 = dp.getCreatedAt();
		String Date = date1.getAttribute("value");
		if(d.equals(Date))
		{
			System.out.println("Date Pass");
		}
		else 
		{

			System.out.println("Date Fail");
		}

		Thread.sleep(3000);
		WebElement branch = dp.getBranchName();
		String branchName = branch.getText().trim();
		if(branchName.equals("Pune Branch"))
		{
			System.out.println("Branch Name Pass");
		}

		else 
		{

			System.out.println("Branch Name Fail");
		}
		//	System.out.println("Branch Name = "+branchName);

		Thread.sleep(3000);
		WebElement salesId = dp.getSalesmanId();
		String SalesID = salesId.getText().trim();
		if(SalesID.equals("2513-Ramchandra Subhash Khande"))
		{
			System.out.println("Salesman Name Pass");
		}
		else 
		{ 

			System.out.println("Salesman Name Fail");
		}

		//System.out.println("Salesman ID  = "+salesID);

		Thread.sleep(3000);
		WebElement stockStatus = dp.getStockStatus();
		String stock = stockStatus.getAttribute("value");
		System.out.println("Stock Status  = "+stock);

		wd.dropDown(dp.getCounter(), "Bangle Counter PN");

		String error = dp.getErrorMsg().getText();

		if(error.equalsIgnoreCase("Data Already Uploaded !!!"))
		{
			ss.getAshot(driver, currentDir, "./Photo/", className, "AfterCounterSelection");
			Thread.sleep(2000);
		}

		wd.dropDown(dp.getCounter(), "Diamond Counter PN");
		Thread.sleep(2000);
		Random random = new Random();   

		dp.getItem7(random.nextInt(101));
		Thread.sleep(2000);

		dp.getItem10(random.nextInt(101));
		Thread.sleep(2000);

		dp.getItem6(random.nextInt(101));
		Thread.sleep(2000);

		dp.getItem2(random.nextInt(101));
		Thread.sleep(2000);

		dp.getItem8(random.nextInt(101));
		Thread.sleep(2000);

		dp.getItem9(random.nextInt(101));
		Thread.sleep(2000);

		dp.getItem3(random.nextInt(101));
		Thread.sleep(2000);

		dp.getItem4(random.nextInt(101));
		Thread.sleep(2000);

		dp.getItem5(random.nextInt(101));
		Thread.sleep(2000);

		dp.getItem11(random.nextInt(101));
		Thread.sleep(2000);

		dp.submit();

		Thread.sleep(2000);

		ss.getAshot(driver, constant, "./Photo/", className, "OpeningStockAddedd");
	}
	
	@Test (priority = 3)
	public void closingStock()
	{
		dp.closingStock();
		
	}

}
