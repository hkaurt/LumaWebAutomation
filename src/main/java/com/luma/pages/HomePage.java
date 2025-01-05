package com.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {

		super(driver);

	}

	private By createAnAccLink = By.xpath("(//a[text()='Create an Account'])[1]");
	private By signInLink = By.xpath("(//a[contains(text(), 'Sign In')])[1]");

	public void clickCreateAnAccLink() {

		driver.findElement(createAnAccLink).click();

	}

	public void clickSignInLink() {

		driver.findElement(signInLink).click();

	}

}
