package main;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.PlumbingAssignment;

public class driver {
	
		public static void main(String[] args) {
		final WebDriver driver;
		String URL = "https://www.starofservice.in/dir/telangana/hyderabad/hyderabad/plumbing#/";
		driver = Browser.intialize();
		driver.get(URL);
		PlumbingAssignment customer=new PlumbingAssignment(driver);
		boolean result = customer.FindAvailablePlumber();
		Assert.assertEquals(result, true, "Plumber is available");
		if(result)System.out.println("Plumaber has been assigned");
		else System.out.println("Test Failed");
		Browser.KillBrowser();
	}

}
