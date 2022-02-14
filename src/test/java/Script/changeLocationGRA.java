package Script;

import java.io.FileNotFoundException;
import java.io.IOException;

//import org.testng.ITestResult;
import org.testng.annotations.Test;

import Generic.BaseClass;
//import Generic.Photo;
import Generic.WebDriverUtilities;
import LoginPage.LoginLogout;
import Picture.ScreenShot;
//import LoginPage.LoginLogout;
//import PomPages.LoginPage;
import PomPages.homePage;
import PomPages.profilePage;
//import SS.ScreenShot;
//import screenShot.TakeScreenShot;

//import generic.screenShot;

public class changeLocationGRA extends BaseClass
{

	@Test
	public void Login123() throws InterruptedException, FileNotFoundException, IOException
	{
		
		//CULogin cu = new CULogin();
		String uname= p.getdata(0, 1, 1);
		
		
		String password = p.getdata(0, 2, 1); //Get data (0 sheet, 2 row, 1 cell)
		
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		System.out.println(methodName);
		
		LoginLogout ll = new LoginLogout();
		
		ll.LoginPage(driver, uname, password);		
		
		String className = this.getClass().getSimpleName(); 
		
		Thread.sleep(3000);
		
	//	ScreenShot ss = new ScreenShot();
		System.out.println(currentDir);
		//ss.getAshot(driver, currentDir, "./Photo/", methodName, className);
		
		ScreenShot ss = new ScreenShot();
		ss.getAshot(driver, currentDir, "./Photo/", className, "HomePage");
		ss.getAshot(driver, password, methodName, className, className)
		
		//click on user profile
		Thread.sleep(3000);
		ss.takeScreenShot(driver, password, methodName, className, className)
		homePage hp = new homePage(driver);
		hp.getUserProfile();
		Thread.sleep(3000);
		
		//click on profile
		hp.getProfileOption();
		
		profilePage pp= new profilePage(driver);
		WebDriverUtilities wd= new WebDriverUtilities();
		Thread.sleep(3000);
		//wd.takeScreenShot(driver, className);
		
		ss.getAshot(driver, currentDir, "./Photo/", className, "BeforeChange");
		
		Thread.sleep(3000);		
		//select location in current location GRA
		wd.dropDown(pp.getLocationGRA(), "Karad Branch - Tops/ Small Ornament Counter KRD");
		//	wd.dropDown(pp.getLocationGRA(), "14");
		
		Thread.sleep(3000);
		
		ss.getAshot(driver, currentDir, "./Photo/", className, "AfterChange");
		
		Thread.sleep(3000);
		//wd.takeScreenShot(driver, className);
		
		//click on save changes
		pp.getSaveChanges();
		Thread.sleep(3000);
		
		//ss.takeScreenShot(driver, constant, photo, className, methodName);
		ll.LogoutPage(driver);
		
	}
}