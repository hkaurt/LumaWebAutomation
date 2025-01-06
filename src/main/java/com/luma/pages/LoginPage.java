package com.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	private By emailTxt = By.id("email");
	private By pwdTxt = By.id("pass");
	private By signInBtn = By.xpath("(//span[text()='Sign In'])[1]");

	public void signInWithEmailAndPwd() {

		driver.findElement(emailTxt).sendKeys("hkkr@gmail.com");
		driver.findElement(pwdTxt).sendKeys("Test@123");
		driver.findElement(signInBtn).click();

	}

}
