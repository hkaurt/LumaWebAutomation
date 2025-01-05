package com.luma.loginpagetests;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import utils.BaseUtils;

public class BaseTest {

	protected WebDriver driver;

	@BeforeTest
	public void launchSite() throws IOException {

		String browser = BaseUtils.getConfigValue("browser");

		switch (browser.toLowerCase()) {

		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		default:
			System.out.println("Invalid Driver");
			break;

		}

		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.valueOf(BaseUtils.getConfigValue("implicitWait"))));
		driver.manage().window().maximize();
		driver.get(BaseUtils.getConfigValue("url"));

	}

	@AfterTest
	public void tearDown() {

		driver.quit();

	}
}
