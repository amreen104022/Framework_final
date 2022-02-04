package Script;

import java.io.FileNotFoundException;
import java.io.IOException;

//import org.testng.ITestResult;
import org.testng.annotations.Test;

import Generic.BaseClass;
//import Generic.Photo;
import Generic.WebDriverUtilities;
import LoginPage.LoginLogout;
//import LoginPage.LoginLogout;
//import PomPages.LoginPage;
import PomPages.homePage;
import PomPages.profilePage;
import SS.ScreenShot;
//import screenShot.TakeScreenShot;

//import generic.screenShot;

public class changeLocationGRA extends BaseClass
{
	
	@Test
	public void Login123() throws InterruptedException, FileNotFoundException, IOException
	{
		//CULogin cu = new CULogin();
		String uname= p.getdata(0, 1, 1);
		
		String password = p.getdata(0, 2, 1);
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        
        System.out.println(methodName);
        
        LoginLogout ll = new LoginLogout();
		
		ll.LoginPage(driver, uname, password);		
		 
        String className = this.getClass().getSimpleName(); 
        
        Thread.sleep(3000);
        
        ScreenShot ss = new ScreenShot();
        System.out.println(currentDir);
        ss.getAshot(driver, currentDir, "./Photo/", methodName, className);
        
		//click on user profile
        Thread.sleep(3000);

        homePage hp = new homePage(driver);
		hp.getUserProfile();
		Thread.sleep(3000);
		
		//click on profile
		hp.getProfileOption();
		
		profilePage pp= new profilePage(driver);
		WebDriverUtilities wd= new WebDriverUtilities();
		Thread.sleep(3000);
		//wd.takeScreenShot(driver, className);
		
		//select location in current location GRA
		wd.dropDown(pp.getLocationGRA(), "Karad Branch - Tops/ Small Ornament Counter KRD");
		Thread.sleep(3000);
		
		//wd.takeScreenShot(driver, className);
		
		//click on save changes
		pp.getSaveChanges();
		Thread.sleep(3000);
		
		ss.takeScreenShot(driver, constant, photo, className, methodName);
		ll.LogoutPage(driver);
		
	}
} 