package com.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommonPage extends BasePage {
	
	public CommonPage(WebDriver driver) {
		super(driver);
	}

	private By userHeaderBtn=By.xpath("(//button[@type='button'])[1]");
	private By signOutLink = By.xpath("(//a[contains(string(),'Sign Out')])[1]");

	private By createAnAccLink = By.xpath("(//a[text()='Create an Account'])[1]");
	private By signInLink = By.xpath("(//a[contains(text(), 'Sign In')])[1]");

	public void clickCreateAnAccLink() {

		driver.findElement(createAnAccLink).click();

	}

	public void clickSignInLink() {

		driver.findElement(signInLink).click();

	}

	public void clickUserHeaderBtn() {

		driver.findElement(userHeaderBtn).click();

	}

	public void clickSignOut() {

		driver.findElement(signOutLink).click();

	}
	
}
