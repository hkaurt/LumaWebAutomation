package com.luma.loginpagetests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.MediaEntityBuilder;

import utils.BaseUtils;
import utils.ReportUtils;

public class BaseTest {

	protected static WebDriver driver;

	@BeforeSuite
	protected void init() {
		ReportUtils.initReport();
	}

	@BeforeMethod
	protected void launchSite(Method method) throws IOException {

		ReportUtils.createTest(method.getName());

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
		ReportUtils.log.info(browser + " Browser launched");

		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.valueOf(BaseUtils.getConfigValue("implicitWait"))));
		driver.manage().window().maximize();
		String url = BaseUtils.getConfigValue("url");
		driver.get(url);
		ReportUtils.log.info("url launched : " + url);

	}

	@AfterMethod
	protected void end(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			ReportUtils.log.fail(result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath(BaseUtils.getScreenShotPath(driver,
							result.getInstance().getClass().getSimpleName() + "." + result.getMethod().getMethodName()))
							.build());
		}

		driver.quit();
		ReportUtils.log.info("Browser Closed");
	}

	@AfterSuite
	protected void tearDown() {

		ReportUtils.generateReport();

	}
}
