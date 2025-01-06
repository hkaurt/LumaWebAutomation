package com.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogOutPage extends BasePage{

	public LogOutPage(WebDriver driver) {
		super(driver);
	}
	
	private By signOutConfMsg = By.xpath("//h1/span");
	
	public String getSignOutMsg() {

		return driver.findElement(signOutConfMsg).getText();

	}


}
