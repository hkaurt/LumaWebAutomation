package com.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends CommonPage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	private By emailText = By.id("email");
	private By passwordText = By.id("pass");
	private By signInBtn = By.xpath("(//span[text()='Sign In'])[1]");

	public void signInWithEmailAndPassword() {

		driver.findElement(emailText).sendKeys("hkr@gmail.com");
		driver.findElement(passwordText).sendKeys("Test@123");
		driver.findElement(signInBtn).click();

	}

}
