package Generic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;

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
		report = new ExtentReports(System.getProperty("user.dir")+"./FrameworkReportResults.html");
		test = report.startTest("FrameWorkDemo");
	}

	@BeforeTest
	@org.testng.annotations.Parameters("browser")
	public void setup(@Optional("chrome") String browser) throws Exception{

		if(browser.equalsIgnoreCase("firefox")){
			//create firefox instance
			//	System.setProperty("webdriver.gecko.driver", ".\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();
		}

		//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("chrome")){
			//set path to chromedriver.exe
			//System.setProperty("webdriver.chrome.driver",".\\chromedriver.exe");

			WebDriverManager.chromedriver().setup();

			//create chrome instance
			driver = new ChromeDriver();
		}
		//Check if parameter passed as 'Edge'
		else if(browser.equalsIgnoreCase("Edge")){
			//set path to Edge.exe
			WebDriverManager.edgedriver().setup();

			//			System.setProperty("webdriver.edge.driver",".\\MicrosoftWebDriver.exe");
			//create Edge instance
			driver = new EdgeDriver();
		}
		else{

			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
			//WebDriverManager.chromedriver().setup();

		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}


	@BeforeMethod
	public void openApp() throws FileNotFoundException, IOException
	{
		WebDriverManager.chromedriver().setup();
		//		WebDriverManager.firefoxdriver().setup();
		//		WebDriverManager.edgedriver().setup();

		//System.setProperty("webdriver.chrome.driver", "./driver.exe");

		//	driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS); 
		driver.get(p.getPropertyFileData("url"));
		//test.log(LogStatus.PASS,"Test Passed");
		System.out.println(driver.getCurrentUrl());
		if(driver.getCurrentUrl().equalsIgnoreCase("http://10.2.11.23/connectus-dummy/admin/login"))
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
