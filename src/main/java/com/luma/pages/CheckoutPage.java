package com.luma.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utils.ReportUtils;

public class CheckoutPage extends CommonPage {

	public CheckoutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	private By emailAddressText = By.xpath("//div[@id='checkout-step-shipping']//input[@type='email']");
	private By firstNameText = By.name("firstname");
	private By lastNameText = By.name("lastname");
	private By streetAddressText = By.xpath("(//input[contains(@name,'street')])[1]");
	private By cityText = By.name("city");
	private By stateProvinceDropDown = By.xpath("//select[@name='region_id']");
	private By zipPostalCodeText = By.name("postcode");
	private By countryDropDown = By.name("country_id");
	private By phoneNumberText = By.name("telephone");

	private By nextButton = By.xpath("//button/span[text()='Next']");
	private By paymentMethodBillingAddress = By.className("billing-address-details");
	private By ShipToAddress = By.className("shipping-information-content");

	String shippingAddress;

	public void fillShippingForm(String email, String firstName, String lastName, String streetAddress, String city,
			String stateProvince, String zipPostalCode, String country, String phoneNumber)
			throws InterruptedException {

		driver.findElement(emailAddressText).sendKeys(email);
		driver.findElement(firstNameText).sendKeys(firstName);
		driver.findElement(lastNameText).sendKeys(lastName);
		driver.findElement(streetAddressText).sendKeys(streetAddress);
		driver.findElement(zipPostalCodeText).sendKeys(zipPostalCode);
		driver.findElement(cityText).sendKeys(city);
		driver.findElement(phoneNumberText).sendKeys(phoneNumber);

		WebElement stateProvinceDropDownEle = driver.findElement(stateProvinceDropDown);

		new Select(stateProvinceDropDownEle).selectByVisibleText(stateProvince);

		WebElement countryDropDownEle = driver.findElement(countryDropDown);
		new Select(countryDropDownEle).selectByVisibleText(country);

		ReportUtils.log.info("Shipping Address form is filled");

	}

	public void selectShippingMethod(String shippingMethod) {

		By shippingMethodsRadioButton = By.xpath("//div[contains(@id,'shipping-method')]//td[text()='" + shippingMethod
				+ "']/preceding-sibling :: td//input[@type='radio']");

		driver.findElement(shippingMethodsRadioButton).click();
		ReportUtils.log.info("Shipping Method with " + shippingMethod + " is selected");

	}

	public void proceedToNext() {
		
		driver.findElement(nextButton).click();
		ReportUtils.log.info("Proceeded Next");

	}

	public String paymentMethodShippingAddress() {

		String shippingAddressText = driver.findElement(paymentMethodBillingAddress).getText();
		return shippingAddressText;

	}

	public String shipToAddress() {

		String shipToAddressText = driver.findElement(ShipToAddress).getText();
		return shipToAddressText;

	}

}
