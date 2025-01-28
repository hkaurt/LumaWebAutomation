package com.luma.pagetests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.luma.pages.CheckoutPage;
import com.luma.pages.JacketsTopsWomenPage;

import utils.BaseUtils;
import utils.ReportUtils;

public class TopsWomenPageCartTest extends BaseTest {

	@Test
	public void verifyAddToCartAddsItemsCorrectly() throws IOException, InterruptedException {

		String email = BaseUtils.getConfigValue("Email");
		String firstName = BaseUtils.getConfigValue("FirstName");
		String lastName = BaseUtils.getConfigValue("LastName");
		String streetAddress = BaseUtils.getConfigValue("StreetAddress");
		String city = BaseUtils.getConfigValue("City");
		String stateProvince = BaseUtils.getConfigValue("StateProvince");
		String zipPostalCode = BaseUtils.getConfigValue("ZipPostalCode");
		String country = BaseUtils.getConfigValue("Country");
		String phoneNumber = BaseUtils.getConfigValue("PhoneNumber");
		String shippingAddress = firstName + " " + lastName + "\n" + streetAddress + "\n" + city + ", " + stateProvince
				+ " " + zipPostalCode + "\n" + country + "\n" + phoneNumber;

		JacketsTopsWomenPage jacketsTopsWomenPage = new JacketsTopsWomenPage(driver);

		jacketsTopsWomenPage.goToNavigationMenuItem("Women").goToNavigationMenuItem("Tops")
				.selectNavigationMenuItem("Jackets");

		jacketsTopsWomenPage.sortBy("Product Name");
		Assert.assertTrue(jacketsTopsWomenPage.isProductNamesSortedOnPage());
		ReportUtils.log.pass("Test Passed: Products are sorted in Ascending order as per Product Names");

		int lastProductOnPage = jacketsTopsWomenPage.getNumberOfItemsOnPage(1, 12) - 1;
		int firstProductOnPage = 0;

		jacketsTopsWomenPage.scrollToProduct(lastProductOnPage);

		jacketsTopsWomenPage.addProductToCart(lastProductOnPage, "S", "Blue");

		jacketsTopsWomenPage.scrollToProduct(firstProductOnPage);

		jacketsTopsWomenPage.addProductToCart(firstProductOnPage, "S", "Orange");

//		 Assert.assertEquals(jacketsTopsWomenPage.getProductAddedToCartConfirmMessage(),"You added Adrienne Trek Jacket to your shopping cart.");

		jacketsTopsWomenPage.goToCart();

//		Assert.assertEquals(jacketsTopsWomenPage.getTotalOfItemsInCart(),
//				jacketsTopsWomenPage.getTotalNumberOfItemsAddedInCart());

		Assert.assertEquals(jacketsTopsWomenPage.getTotalOfItemsInCart(), 2);
		ReportUtils.log.pass("Test Passed: Total number of items in the cart are verified");

		Assert.assertTrue(jacketsTopsWomenPage.isProductNamesInCartCorrect());
		ReportUtils.log.pass("Test Passed: Product Names In Cart are verified");

		Assert.assertTrue(jacketsTopsWomenPage.isQuantityOfTheaddedProductInCartCorrect());
		ReportUtils.log.pass("Test Passed: Quantity Of Items listed is verified");

		jacketsTopsWomenPage.proceedToCheckOut();

		CheckoutPage checkoutPage = new CheckoutPage(driver);

		checkoutPage.fillShippingForm(email, firstName, lastName, streetAddress, city, stateProvince, zipPostalCode,
				country, phoneNumber);

		checkoutPage.selectShippingMethod(BaseUtils.getConfigValue("ShippingMethod"));

		checkoutPage.proceedToNext();

		Assert.assertEquals(checkoutPage.paymentMethodShippingAddress(), shippingAddress);
		ReportUtils.log.pass("Test Passed: Address under 'Payment Method' is verified");

		Assert.assertEquals(checkoutPage.shipToAddress(), shippingAddress);
		ReportUtils.log.pass("Test Passed: Address under 'Ship To:' is verified");

	}

}
