package wrappers;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
	public static WebDriver local_driver;

	public Helper(WebDriver driver) {
		local_driver = driver;
	}

	public boolean Click(WebElement elm) {
		boolean res = false;
		try {
			elm.click();
			res = true;
			return res;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return res;
	}

	public WebElement GetElement(String XPath) {
		WebElement elm = null;
		try {
			WebDriverWait wait = new WebDriverWait(local_driver, 10000);
			elm = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath)));
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			elm = local_driver.findElement(By.xpath(XPath));
		} catch (Exception e) {
			return null;
		}
		return elm;
	}

	public WebElement Se_GetElement(WebElement Parent, String XPath) {
		WebElement res = null;
		try {
			if (Se_FindElement(Parent)) {
				res = Parent.findElement(By.xpath(XPath));
			}
		} catch (org.openqa.selenium.StaleElementReferenceException ex) {
			res = Parent.findElement(By.xpath(XPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean Se_MoveToElement(WebElement elm) {
		boolean res = false;
		Actions action = new Actions(local_driver);
		try {
			action.moveToElement(elm);
			action.perform();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public List<WebElement> Se_GetElements(String XPath) {
		List<WebElement> res = null;
		try {
			res = local_driver.findElements(By.xpath(XPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public void untill() throws InterruptedException {
		Thread.sleep(3000);
	}

	public boolean Se_SendKeys(WebElement elm, String Value) {
		boolean res = false;
		try {
			if (Value == "" || Value == null || Value.isEmpty()) {

				return true;
			}

			else {
				elm.sendKeys(Value);
				res = true;
				return res;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return res;
	}

	public boolean ClickClearSendKeys(WebElement elm, String Value) {
		boolean res = false;
		try {
			elm.click();
			elm.clear();
			elm.sendKeys(Value);
			res = true;
			return res;
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public boolean Se_FindElement(WebElement elm) {
		boolean res = false;
		try {
			for (long milsec = 0; milsec <= 60 * 1000; milsec += 1000) {

				if (elm.isDisplayed() || elm.getSize().getHeight() > 0) {
					res = true;
					return res;
				}
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			return false;
		}
		return res;
	}

	public boolean Select_DropDown(WebElement SelectParent, String Select_option) {
		boolean res = false;
		try {
			if (Se_FindElement(SelectParent)) {
				Click(SelectParent);
				String Option_Xp = ".//option[normalize-space()='" + Select_option + "']";
				WebElement Option = Se_GetElement(SelectParent, Option_Xp);
				res = Click(Option);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
