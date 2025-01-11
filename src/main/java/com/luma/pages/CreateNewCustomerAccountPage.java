package com.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ReportUtils;

public class CreateNewCustomerAccountPage extends CommonPage {

	public CreateNewCustomerAccountPage(WebDriver driver) {
		super(driver);
	}

	private By firstNameText = By.id("firstname");
	private By lastNameText = By.id("lastname");
	private By emailText = By.id("email_address");
	private By passwordText = By.id("password");
	private By confirmPasswordText = By.id("password-confirmation");
	private By createAnAccountBtn = By.xpath("//button[@title='Create an Account']");

	public void createNewCustomerAccountWithInfo(String firstName, String lastName, String email, String password) {

		driver.findElement(firstNameText).sendKeys(firstName);
		ReportUtils.log.info("Enterted firstname: " + firstName);

		driver.findElement(lastNameText).sendKeys(lastName);
		ReportUtils.log.info("Enterted lastName: " + lastName);

		driver.findElement(emailText).sendKeys(email);
		ReportUtils.log.info("Enterted email: " + email);

		driver.findElement(passwordText).sendKeys(password);
		ReportUtils.log.info("Enterted password: " + "*************");

		driver.findElement(confirmPasswordText).sendKeys(password);
		ReportUtils.log.info(" Entered Confirmed password: " + "*************");

		driver.findElement(createAnAccountBtn).click();
		ReportUtils.log.info("Clicked Create An Account Button");

	}
}
