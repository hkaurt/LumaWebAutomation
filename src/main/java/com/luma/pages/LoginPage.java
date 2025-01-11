package com.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ReportUtils;

public class LoginPage extends CommonPage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	private By emailText = By.id("email");
	private By passwordText = By.id("pass");
	private By signInBtn = By.xpath("(//span[text()='Sign In'])[1]");

	public void signInWithEmailAndPassword(String email, String password) {

		driver.findElement(emailText).sendKeys(email);
		ReportUtils.log.info("Enterted email: " + email);

		driver.findElement(passwordText).sendKeys(password);
		ReportUtils.log.info("Enterted password: " + "**********");

		driver.findElement(signInBtn).click();
		ReportUtils.log.info("Clicked sign in button");

	}

}
