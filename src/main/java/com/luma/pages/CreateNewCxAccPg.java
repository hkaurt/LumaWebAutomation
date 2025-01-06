package com.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CreateNewCxAccPg extends BasePage {

	public CreateNewCxAccPg(WebDriver driver) {
		super(driver);
	}

	private By firstNameText = By.id("firstname");
	private By lastNameText = By.id("lastname");
	private By emailText = By.id("email_address");
	private By passwordText = By.id("password");
	private By confirmPasswordText = By.id("password-confirmation");
	private By createAnAccountBtn = By.xpath("//button[@title='Create an Account']");

	public void createNewcxAccWithInfo() {

		driver.findElement(firstNameText).sendKeys("Hk");
		driver.findElement(lastNameText).sendKeys("Kr");
		driver.findElement(emailText).sendKeys("hkkr@gmail.com");
		driver.findElement(passwordText).sendKeys("Test@123");
		driver.findElement(confirmPasswordText).sendKeys("Test@123");
		driver.findElement(createAnAccountBtn).click();

	}
}
