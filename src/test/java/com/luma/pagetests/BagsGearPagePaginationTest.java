package com.luma.pagetests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.luma.pages.CommonPage;

import utils.ReportUtils;

public class BagsGearPagePaginationTest extends BaseTest {

	@Test
	public void verifyNumberOfPagesandItemsAreCorrect() {

		CommonPage commonPage = new CommonPage(driver);

		commonPage.goToNavigationMenuItem("Gear").selectNavigationMenuItem("Bags");

		Assert.assertEquals(commonPage.getTotalItemsOnPage(1,12), 12);
		ReportUtils.log.pass("Test passed: Number Of Products On Given Page verified");

		commonPage.scrollToProduct(commonPage.getNumberOfItemsOnPage(1,12) - 1);
		
		Assert.assertEquals(commonPage.getTotalNumberOfPages(12),12);
		ReportUtils.log.pass("Test passed: Total Number Of Pages Verified");

		commonPage.goToPageNumber(2);

		Assert.assertEquals(commonPage.getTotalItemsOnPage(2,12), 2);
		ReportUtils.log.pass("Test passed: Number Of Products On Given Page verified");

	}

}
