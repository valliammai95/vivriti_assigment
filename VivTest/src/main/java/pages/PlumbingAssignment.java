package pages;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fasterxml.jackson.databind.ObjectMapper;
import locators.Locator;
import wrappers.Helper;
import datahandler.*;

public class PlumbingAssignment {
	// Variables
	public static String city;
	HashMap<String, String> questionanswers;
	List<String> type;
	// objects
	Helper helper;
	WebDriver local_driver;
	Locator locator;

	public PlumbingAssignment(WebDriver driver) {
		local_driver = driver;
		helper = new Helper(driver);
		locator = new Locator(driver);
	}

	public void getInput() {
		PlumberRequest request = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			String filePath = System.getProperty("user.dir") + "//Data//FindPlumberData.json";
			request = mapper.readValue(new File(filePath), PlumberRequest.class);
			city = request.getCity();
			List<Question> questions = request.getQuestions();
			type = new ArrayList<String>();
			questionanswers = new LinkedHashMap<String, String>();
			for (int i = 0; i < questions.size(); i++) {
				type.add(questions.get(i).getType());
				String key = questions.get(i).getQuestion();
				String value = questions.get(i).getAnswer();
				questionanswers.put(key, value);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean FindAvailablePlumber() {
		boolean res = false;
		try {
			getInput();

			res = helper.ClickClearSendKeys(locator.cityinput, city);
			res = helper.Click(locator.goButton());
			res = helper.Click(locator.nextButton());
			int i = 0;
			for (Map.Entry<String, String> entry : questionanswers.entrySet()) {

				switch (type.get(i)) {
				case "textarea":
					additionalComment(entry);
					break;
				case "calendar":
					res = helper.Se_FindElement(locator.question(entry.getKey()));
					setServiceDate();
					break;
				case "dropdown":
					setServiceTime(entry);
					break;
				case "checkbox":
					setAnswers(entry);
					break;
				case "radiobutton":
					setAnswers(entry);

					break;

				}
				i++;
			}
			res = helper.Se_FindElement(locator.emailInput());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return res;
	}

	public boolean setServiceDate() {
		boolean res = false;
		try {
			List<WebElement> calCurrentMonButtons = locator.calendarDateButtons();
			List<WebElement> calNextMonButtons = locator.calendarNextmonButtons();
			int availDates = calCurrentMonButtons.size();

			if (availDates == 2) {
				res = helper.Click(locator.nextMonthForwardButton());
				res = helper.Click(calNextMonButtons.get(1));
			} else if (availDates == 1) {
				res = helper.Click(locator.nextMonthForwardButton());
				res = helper.Click(calNextMonButtons.get(2));
			} else {
				res = helper.Click(calCurrentMonButtons.get(2));

			}
			res = helper.Click(locator.nextButton());
		} catch (Exception e) {
			return false;
		}
		return res;
	}

	public boolean setAnswers(Map.Entry<String, String> entry) {
		boolean res = false;
		try {
			res = helper.Se_FindElement(locator.question(entry.getKey()));
			res = helper.Click(locator.AnswerOption(entry.getValue()));
			res = helper.Click(locator.nextButton());
		} catch (Exception e) {
			return false;
		}
		return res;
	}

	public boolean additionalComment(Map.Entry<String, String> entry) {
		boolean res = false;
		try {
			res = helper.Se_FindElement(locator.question(entry.getKey()));
			res = helper.Se_SendKeys(locator.answerTextarea(), entry.getValue());
			res = helper.Click(locator.nextButton());
		} catch (Exception e) {
			return false;
		}
		return res;
	}

	public boolean setServiceTime(Map.Entry<String, String> entry) {
		boolean res = false;
		try {
			res = helper.Se_FindElement(locator.question(entry.getKey()));
			res = helper.Select_DropDown(locator.selectTime(), entry.getValue());
			res = helper.Click(locator.nextButton());
		} catch (Exception e) {
			return false;
		}
		return res;
	}
}
