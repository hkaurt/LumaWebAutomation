package com.luma.loginpagetests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.luma.pages.CommonPage;
import com.luma.pages.CreateNewCxAccPg;
import com.luma.pages.LogOutPage;
import com.luma.pages.LoginPage;

public class CreateNewCxAccTest extends BaseTest {

	@Test
	public void VerifyCreateNewCxAccTest() {

		CommonPage commonPg = new CommonPage(driver);
		commonPg.clickCreateAnAccLink();

		CreateNewCxAccPg createAccpg = new CreateNewCxAccPg(driver);
		createAccpg.createNewcxAccWithInfo();

		Assert.assertEquals(createAccpg.getPageTitle(), "My Account");

		commonPg.clickUserHeaderBtn();
		commonPg.clickSignOut();

		LogOutPage logOutPg = new LogOutPage(driver);
		Assert.assertEquals(logOutPg.getSignOutMsg(), "You are signed out");

		commonPg.clickSignInLink();

		LoginPage loginPg = new LoginPage(driver);
		loginPg.signInWithEmailAndPwd();

		Assert.assertEquals(loginPg.getPageTitle(), "My Account");

	}

}
