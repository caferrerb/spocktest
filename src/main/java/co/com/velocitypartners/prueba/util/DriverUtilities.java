package co.com.velocitypartners.prueba.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;

public class DriverUtilities {

	private static WebDriver driver;

	public static WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		System.setProperty("selenide.browser", "chrome");
		// System.setProperty("webdriver.firefox.marionette",
		// "E:/personal/geckodriver");
		// System.setProperty("webdriver.gecko.driver",
		// "/Users/caferrerb/Drive/SWAT/Selenium/geckodriver");
		// WebDriver driver = new ChromeDriver();
		// return driver;
		if (driver == null) {
			driver = WebDriverRunner.getWebDriver();
		}
		return driver;
	}

	public static void cerrarDriver() {
		getDriver().quit();
		driver=null;
	}

}
