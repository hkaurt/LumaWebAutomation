package com.luma.pagetests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.luma.pages.CreateNewCustomerAccountPage;
import com.luma.pages.LoginPage;
import com.luma.pages.MyAccountPage;

import utils.RandomDataGenerator;
import utils.ReportUtils;

public class CreateNewCustomerAccountTest extends BaseTest {

	@Test
	public void verifyCreationOfNewCustomerAccount() throws InterruptedException {

		CreateNewCustomerAccountPage createAccountPage = new CreateNewCustomerAccountPage(driver);
		createAccountPage.goToCreateAnAccountLink();

		String firstName = RandomDataGenerator.generateRandomName();
		String lastName = RandomDataGenerator.generateRandomName();
		String email = RandomDataGenerator.generateRandomEmail();
		String password = RandomDataGenerator.generateRandomPassword();

		createAccountPage.createNewCustomerAccountWithInfo(firstName, lastName, email, password);

		MyAccountPage myAccountpage = new MyAccountPage(driver);
		Assert.assertEquals(myAccountpage.getConfirmationMessage(),
				"Thank you for registering with Main Website Store.");
		ReportUtils.log.pass(
				"Test passed: confirmation message 'Thank you for registering with Main Website Store.' is displayed");

		myAccountpage.goToUserHeadingBtn();
		myAccountpage.signOut();

		Assert.assertEquals(myAccountpage.getPageHeader(), "You are signed out");
		ReportUtils.log.pass("Test passed: Sign out was successful");
		Thread.sleep(5000);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.signIn();

		loginPage.signInWithEmailAndPassword(email, password);

		Assert.assertEquals(loginPage.getPageTitle(), "Home Page");
		ReportUtils.log.pass("Test passed: Login was Successful");

	}

}
