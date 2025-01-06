package com.luma.loginpagetests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.luma.pages.CreateNewCustomerAccountPage;
import com.luma.pages.LoginPage;
import com.luma.pages.MyAccountPage;

public class CreateNewCustomerAccountTest extends BaseTest {

	@Test
	public void verifySuccessfulCreationOfNewCustomerAccountTest() throws InterruptedException {

		// click on Create An Account
		CreateNewCustomerAccountPage createAccountPage = new CreateNewCustomerAccountPage(driver);
		createAccountPage.clickCreateAnAccountLink();

		// fill and submit the 'Create An Account' form
		createAccountPage.createNewCustomerAccountWithInfo();

		// verify Account registration message, page title and page heading
		MyAccountPage myAccountpage = new MyAccountPage(driver);
		Assert.assertEquals(myAccountpage.getConfirmationMessage(),
				"Thank you for registering with Main Website Store.");
		Assert.assertEquals(myAccountpage.getPageTitle(), "My Account");
		Assert.assertEquals(myAccountpage.getPageHeader(), "My Account");

		// sign out from My Account
		myAccountpage.clickUserHeaderBtn();
		myAccountpage.clickSignOut();

		// verify sign out message
		Assert.assertEquals(myAccountpage.getPageHeader(), "You are signed out");
		Thread.sleep(5000); // added hard stop of 5 sec so that i can reach Home Page

		// click on Sign In
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickSignInLink();

		// Sign in with email and password
		loginPage.signInWithEmailAndPassword();

		// verify page title, page heading after login
		Assert.assertEquals(loginPage.getPageTitle(), "Home Page");

	}

}
