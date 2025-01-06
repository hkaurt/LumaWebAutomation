package com.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

	WebDriver driver;

	private By pageHeader = By.xpath("//h1/span");

	public BasePage(WebDriver driver) {

		this.driver = driver;

	}

	public String getPageTitle() {

		return driver.getTitle();

	}

	public String getPageHeader() {

		return driver.findElement(pageHeader).getText();
	}

}