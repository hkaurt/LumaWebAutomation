package com.luma.loginpagetests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.luma.pages.BasePage;
import com.luma.pages.CommonPage;
import com.luma.pages.CreateNewCxAccPg;
import com.luma.pages.HomePage;

public class CreateNewCxAccTest extends BaseTest {

	@Test(priority = 0)
	public void VerifyCreateNewCxAccTest() {

		HomePage hp = new HomePage(driver);
		hp.clickCreateAnAccLink();

		CreateNewCxAccPg createAccpg = new CreateNewCxAccPg(driver);
		createAccpg.createNewcxAccWithInfo();

		Assert.assertEquals(createAccpg.getPageTitle(), "My Account");

//		CommonPage commonPg = new CommonPage(driver);
//		Assert.assertEquals(commonPg.getConfMsg(), "Thank you for registering with Main Website Store.");
//
//		Assert.assertEquals(commonPg.getUserHeader(), "Welcome, ab ab!");

		
	}

}
