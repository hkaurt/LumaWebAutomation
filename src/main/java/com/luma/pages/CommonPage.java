package com.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonPage extends BasePage {
	
	public CommonPage(WebDriver driver) {
		super(driver);
	}

	By accCreationConfMsg = By.xpath("//div[contains(text(),'Thank you for registering']");
	By userHeader = By.xpath("(//span[contains(text(),'Welcome')])[1]");
	By signOutLink = By.xpath("(//a[contains(string(),'Sign Out')])[1]");
	By signOutConfMsg = By.xpath("//span[text()='You are signed out']");

	public String getConfMsg() {

		return driver.findElement(accCreationConfMsg).getText();

	}

	public String getUserHeader() {

		return driver.findElement(userHeader).getText();
	}

	public void clickUserHeader() {

		driver.findElement(userHeader).click();

	}

	public void clickSignOut() {

		driver.findElement(signOutLink).click();

	}

	public String getSignOutMsg() {

		return driver.findElement(signOutConfMsg).getText();

	}

}
