package com.luma.pagetests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.luma.pages.CheckoutPage;
import com.luma.pages.JacketsTopsWomenPage;

import utils.BaseUtils;
import utils.ReportUtils;

/*
 * @author Harpreet
 * Test Case ID: TC_003

Test Title: To ensure the user can successfully add items to the cart, verify cart details, and proceed to checkout with correct information.

Preconditions:
1.	User is logged in (if required) or ready to use the website without login.
2.	User has access to the URL: https://magento.softwaretestingboard.com/.
3.	Browser is functional and stable.

Test Steps:

Navigate to https://magento.softwaretestingboard.com/.

 */

public class JacketsTopsWomenPageShoppingCartFunctionalityTest extends BaseTest {

	@Test
	public void verifyItemsAreSuccessfullyAddedDisplayedAndCheckoutInShoppingCart()
			throws IOException, InterruptedException {

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

		// 1. Select Women >> Tops >> Jackets.

		jacketsTopsWomenPage.goToNavigationMenuItem("Women");
		jacketsTopsWomenPage.goToNavigationMenuItem("Tops");
		jacketsTopsWomenPage.selectMenuItem("Jackets");

		// 2. Sort the items by Product Name - Items are sorted alphabetically by
		// product name.

		jacketsTopsWomenPage.sortBy("Product Name");
		Assert.assertEquals(jacketsTopsWomenPage.isProductNamesSortedOnPage(), true);
		ReportUtils.log.pass("Test Passed: Products are sorted in Ascending order as per Product Names");

		Assert.assertTrue(jacketsTopsWomenPage.isGridViewActive());

		int lastProductOnPage = jacketsTopsWomenPage.numberOfProductsOnPage() - 1;
		int firstProductOnPage = 0;

		// 3. Scroll down to the last item in the grid.
		jacketsTopsWomenPage.scrollToProduct(lastProductOnPage);

		// 5. Select the last item's Color as Blue and Size as Small. Click Add to Cart.
		jacketsTopsWomenPage.addProductToCart(lastProductOnPage, "S", "Blue");

		// 6. Scroll back to the first item in the grid.
		jacketsTopsWomenPage.scrollToProduct(firstProductOnPage);

		// 7. Select the first item's Color as Orange and Size as Small. Click Add to
		// Cart.
		jacketsTopsWomenPage.addProductToCart(firstProductOnPage, "S", "Orange");
		Thread.sleep(5000);

		// 8. Verify the confirmation message
		Assert.assertTrue(jacketsTopsWomenPage.isCorrectProductAddedToCartMessageDisplayed());

		// 9. Click on Cart icon
		jacketsTopsWomenPage.goToCart();

		// 10. Verify that the total number of items in the cart is correct
		Assert.assertEquals(jacketsTopsWomenPage.totalNumberOfProductsAddedInCart(),
				jacketsTopsWomenPage.totalNumberOfProductsInCart());
		ReportUtils.log.pass("Test Passed: Total number of items in the cart are verified");

		Assert.assertEquals(jacketsTopsWomenPage.totalNumberOfProductsAddedInCart(),
				jacketsTopsWomenPage.counterNumberOnCart());
		ReportUtils.log.pass("Test Passed: Counter Number On Cart is verified");

		// 11. Verify that the name and quantity of the items listed are correct
		Assert.assertTrue(jacketsTopsWomenPage.isProductNamesInCartCorrect());
		ReportUtils.log.pass("Test Passed: Product Names In Cart are verified");

		Assert.assertTrue(jacketsTopsWomenPage.isQuantityOfTheSelectedProductInCartCorrect());
		ReportUtils.log.pass("Test Passed: Quantity Of Items listed is verified");

		// 12. Click on Proceed to Checkout
		jacketsTopsWomenPage.proceedToCheckOut();

		CheckoutPage checkoutPage = new CheckoutPage(driver);

		// 13. Fill the Shipping Address and Select a Shipping method
		checkoutPage.fillShippingForm(email, firstName, lastName, streetAddress, city, stateProvince, zipPostalCode,
				country, phoneNumber);

		checkoutPage.selectShippingMethod(BaseUtils.getConfigValue("ShippingMethod"));

		checkoutPage.proceedToNext();

		// 14. On Payment Method, verify that the address is correct under Payment
		// Method and Ship To section

		Assert.assertEquals(checkoutPage.paymentMethodShippingAddress(), shippingAddress);
		ReportUtils.log.pass("Test Passed: Address under 'Payment Method' is verified");

		Assert.assertEquals(checkoutPage.shipToAddress(), shippingAddress);
		ReportUtils.log.pass("Test Passed: Address under 'Ship To:' is verified");

	}

}
