package com.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	private By accCreationConfMsg = By.xpath("//h1/span");
	
	public String getConfMsg() {

		return driver.findElement(accCreationConfMsg).getText();

	}
}
