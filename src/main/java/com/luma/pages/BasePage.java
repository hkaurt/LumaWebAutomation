package com.luma.pages;

import org.openqa.selenium.WebDriver;

public class BasePage {

	WebDriver driver;

	public BasePage(WebDriver driver) {

		this.driver = driver;

	}

	public String getPageTitle() {

		return driver.getTitle();

	}

}