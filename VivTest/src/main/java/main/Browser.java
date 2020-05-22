package main;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Browser {
	private static WebDriver driver ;
	

	public static WebDriver intialize()
	{
		
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//BrowserDriver//chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
	public static WebDriver getInstance()
	{
		if(driver!=null)
		{
			return driver;
		}
	return null;
	}
	public static void KillBrowser()
	{
		try
		{
			if(driver!=null)
				driver.quit();
			driver=null;
		}
		catch(Exception e)
		{
			System.out.println("KillBrowser exception");

		}
	}
	
}
