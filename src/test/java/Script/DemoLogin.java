package Script;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Generic.BaseClass;
import Generic.WebDriverUtilities;
//import Generic.BaseClass;
import LoginPage.LoginLogout;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoLogin extends BaseClass
{

	@Test
	public static void main(String[] args) throws Exception {
		
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.get("http://10.1.0.31/connectus-dummy/admin/login");
//		driver.manage().window().maximize();
		LoginLogout ll = new LoginLogout();
		ll.LoginPage(driver, uname, password);		
		WebDriverUtilities wd = new WebDriverUtilities();
		wd.takeScreenShot(driver, "demo");
		Thread.sleep(10000);
		ll.LogoutPage(driver);

	}

}
