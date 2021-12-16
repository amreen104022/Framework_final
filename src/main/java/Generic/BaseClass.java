package Generic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass implements AutoConstant {

	public PropertyFile p=new PropertyFile();

	public WebDriver driver;

	public WebDriverUtilities utilities=new WebDriverUtilities();


	static ExtentTest test;
	static ExtentReports report;


	@BeforeClass
	public static void startTest()
	{
		report = new ExtentReports(System.getProperty("user.dir")+"./ExtentReportResults.html");
		test = report.startTest("ExtentDemo");
	}


	@BeforeMethod
	public void openApp() throws FileNotFoundException, IOException
	{
		//	WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "./driver.exe");

		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.get(p.getdata(0, 0, 1));
		test.log(LogStatus.PASS,"Test Passed");
		System.out.println(driver.getCurrentUrl());
		if(driver.getCurrentUrl().equalsIgnoreCase("http://10.1.0.31/connectus-dummy/admin/login"))
		{
		test.log(LogStatus.PASS, "Navigated to the specified URL");
		}
		else
		{
		test.log(LogStatus.FAIL, "Test Failed");
		}
	}

	@AfterMethod
	public void closeApp() throws IOException
	{

		driver.close(); 
	}

	@AfterClass
	public static void endTest()
	{
	report.endTest(test);
	report.flush();
	}

	/*
	 * @AfterMethod
	public void closeApp(ITestResult r) throws IOException
	{
		int status = r.getStatus();
		String name = r.getName();

		/*1=pass test case
	 * 2=fail test case
	 * 3=all test case
	 */

	/*	if(status==1) {
			Reporter.log("TestCase : " + name + " is pass", true);

			Photo p=new Photo();
			p.getPhoto(driver, name);
		}
		else
		{
			Reporter.log("TestCase : " + name + " is failed", true);
			Photo p=new Photo();

			p.getPhoto(driver, name);
		}
		driver.close();
	 */
	/*{
			public void closeBrowser(ITestResult itestresult) throws IOException {
				int status = itestresult.getStatus();
				String name = itestresult.getName();
				if (status == 1) {
					Reporter.log("TestCase : " + name + " is pass", true);
				} else {
					ScreenShot.takePic(driver, name + ssExt, screenShotFolder);
					Reporter.log("TestCase : " + name + " is fail", true);
				}

				driver.close();
			}*/

}
