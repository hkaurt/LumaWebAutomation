package com.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utils.ReportUtils;

public class CommonPage extends BasePage {

	public CommonPage(WebDriver driver) {
		super(driver);
	}

	private By userHeaderBtn = By.xpath("(//button[@type='button'])[1]");
	private By signOutLink = By.xpath("(//a[contains(string(),'Sign Out')])[1]");

	private By createAnAccountLink = By.xpath("(//a[text()='Create an Account'])[1]");
	private By signInLink = By.xpath("(//a[contains(text(), 'Sign In')])[1]");

	private By womenNavigationMenu = By.xpath("//a[@role='menuitem']//span[text()='Women']");
	private By topsOption = By.xpath("//a[@role='menuitem']//span[text()='Tops']");
	private By hoodiesSweatshirtsOption = By.xpath("//a[@role='menuitem']//span[text()='Hoodies & Sweatshirts']");

	public void clickCreateAnAccountLink() {

		driver.findElement(createAnAccountLink).click();
		ReportUtils.log.info("Clicked on Create An Account Link");

	}

	public void clickSignInLink() {

		driver.findElement(signInLink).click();
		ReportUtils.log.info("Clicked on Sign In Link");

	}

	public void clickUserHeaderBtn() {

		driver.findElement(userHeaderBtn).click();
		ReportUtils.log.info("Clicked on User Header Button");

	}

	public void clickSignOut() {

		driver.findElement(signOutLink).click();
		ReportUtils.log.info("Clicked on Sign Out Link");

	}

	public void hoverWomenThenTopsThenClickHoodiesSweatshirts() {

		WebElement womenMenuItemEle = driver.findElement(womenNavigationMenu);
		WebElement topsEle = driver.findElement(topsOption);
		WebElement hoodiesSweatshirtsEle = driver.findElement(hoodiesSweatshirtsOption);

		Actions action = new Actions(driver);

		action.moveToElement(womenMenuItemEle).moveToElement(topsEle).click(hoodiesSweatshirtsEle).perform();
		ReportUtils.log.info("Hovered to Women then hovered to tops and clicked Hoodies & Sweatshirts");

	}

}
