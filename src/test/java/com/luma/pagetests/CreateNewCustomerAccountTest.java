package com.luma.pagetests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.luma.pages.CreateNewCustomerAccountPage;
import com.luma.pages.LoginPage;
import com.luma.pages.MyAccountPage;

import utils.RandomDataGenerator;
import utils.ReportUtils;

/*
 * @author Harpreet
 * TC001
 * Title: Verify Create New Customer Account functionality
 */
public class CreateNewCustomerAccountTest extends BaseTest {

	@Test
	public void verifySuccessfulCreationOfNewCustomerAccountTest() throws InterruptedException {

		// click on Create An Account
		CreateNewCustomerAccountPage createAccountPage = new CreateNewCustomerAccountPage(driver);
		createAccountPage.goToCreateAnAccountLink();

		// Generate random data
		String firstName = RandomDataGenerator.generateRandomName();
		String lastName = RandomDataGenerator.generateRandomName();
		String email = RandomDataGenerator.generateRandomEmail();
		String password = RandomDataGenerator.generateRandomPassword();

		// fill and submit the 'Create An Account' form
		createAccountPage.createNewCustomerAccountWithInfo(firstName, lastName, email, password);

		// verify Account registration message, page title and page heading
		MyAccountPage myAccountpage = new MyAccountPage(driver);
		Assert.assertEquals(myAccountpage.getConfirmationMessage(),
				"Thank you for registering with Main Website Store.");
		ReportUtils.log.pass(
				"Test passed: confirmation message 'Thank you for registering with Main Website Store.' is displayed");

		// Assert.assertEquals(myAccountpage.getPageTitle(), "My Account");
		// Assert.assertEquals(myAccountpage.getPageHeader(), "My Account");

		// sign out from My Account
		myAccountpage.goToUserHeadingBtn();
		myAccountpage.signOut();

		// verify sign out message
		Assert.assertEquals(myAccountpage.getPageHeader(), "You are signed out");
		ReportUtils.log.pass("Test passed: Sign out was successful");

		Thread.sleep(5000); // added hard stop of 5 sec so that i can reach Home Page

		// click on Sign In
		LoginPage loginPage = new LoginPage(driver);
		loginPage.signIn();

		// Sign in with email and password
		loginPage.signInWithEmailAndPassword(email, password);

		// verify page title, page heading after login
		Assert.assertEquals(loginPage.getPageTitle(), "Home Page");
		ReportUtils.log.pass("Test passed: Login was Successful");

	}

}
