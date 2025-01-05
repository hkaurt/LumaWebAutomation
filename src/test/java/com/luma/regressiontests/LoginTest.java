package com.luma.regressiontests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.luma.loginpagetests.BaseTest;
import com.luma.pages.HomePage;
import com.luma.pages.LoginPage;

public class LoginTest extends BaseTest {

	@Test (priority=3)
	public void verifyLoginTest() {

		HomePage homePg = new HomePage(driver);
		homePg.clickSignInLink();

		LoginPage loginPg = new LoginPage(driver);
		loginPg.signInWithEmailAndPwd();

		Assert.assertEquals(loginPg.getPageTitle(), "What's New");

	}

}
