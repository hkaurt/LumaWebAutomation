package com.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends CommonPage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	private By accountCreationConfirmationMessage = By.xpath("//div[@role='alert']/div/div");
	
	public String getConfirmationMessage() {

		return driver.findElement(accountCreationConfirmationMessage).getText();

	}
}
