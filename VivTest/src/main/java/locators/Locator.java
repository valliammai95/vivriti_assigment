package locators;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import wrappers.Helper;
public class Locator {
	public static WebDriver local_driver ;
	@FindBy(xpath = "//div[@class='hero-container ']//input[@name='postal_code_input']")
	public
	WebElement cityinput;
	Helper selenium_helper;
	public Locator(WebDriver driver)
	{
		local_driver= driver;
		PageFactory.initElements(local_driver, this);
		selenium_helper = new Helper(local_driver);
		
	}
	public WebElement cityInput() {
		return selenium_helper.GetElement("//div[@class='hero-container ']//input[@name='postal_code_input']");
	}
	public WebElement goButton() {
		return selenium_helper.GetElement("//button[normalize-space()='Go']");
	}
	public WebElement popup_parent() {

		return selenium_helper.GetElement("//div[contains(@class,'popup__content')]");
	}
	public WebElement question(String question) {

		return selenium_helper.GetElement("//div[normalize-space()='"+question+"' and (contains(@class,'commonFormTitle') or contains(@class,'titleBase'))]");
	}
	public WebElement AnswerOption(String option) {

		return selenium_helper.GetElement("//div[normalize-space()='"+option+"']//parent::label//div[contains(@class,'Base')]");
	}
	public WebElement answerTextarea() {

		return selenium_helper.GetElement("//div[contains(@class,'commonTextField')]//textarea");
	}
	public WebElement nextButton() {

		return selenium_helper.GetElement("//button[text()='Next']");
	}
	public List<WebElement> calendarDateButtons() {

		return selenium_helper.Se_GetElements("//div[@data-visible='true']/table//td[not(contains(@class,'blocked_out'))]/button[not(contains(@aria-label,'Not available'))]");
	}
	public List<WebElement> calendarNextmonButtons() {

		return selenium_helper.Se_GetElements("//div[@data-visible='true']//parent::div/following-sibling::div//table//td[not(contains(@class,'blocked_out'))]/button");
	}
	
	public WebElement nextMonthForwardButton() {

		return selenium_helper.GetElement("//button[contains(@aria-label,'Move forward')]");
	}
	public WebElement selectTime() {

		return selenium_helper.GetElement("//select[@data-test='step_time']");
	}
	public WebElement emailInput() {

		return selenium_helper.GetElement("//input[@data-test='step_email']");
	}
	
	
}
