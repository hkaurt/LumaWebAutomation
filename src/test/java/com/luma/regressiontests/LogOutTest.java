package com.luma.regressiontests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.luma.loginpagetests.BaseTest;
import com.luma.pages.CommonPage;

public class LogOutTest extends BaseTest {

	@Test(priority = 1)
	public void verifyLogOutTest() {

		CommonPage commonPg = new CommonPage(driver);
		commonPg.clickUserHeader();
		commonPg.clickSignOut();
		Assert.assertEquals(commonPg.getSignOutMsg(), "You are signed out");

	}

}
