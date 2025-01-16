package com.luma.pagetests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.luma.pages.CommonPage;

import utils.ReportUtils;

/*
 * @author Harpreet
 * TC_004

Title: Verify that the pagination functionality is correct for a category page

Steps:
1.	Open Google Chrome
2.	Go to Luma Home Page

 */
public class PaginationFunctionalityTest extends BaseTest {

	@Test
	public void verifyPaginationFunctionalityForBagsGearPage() {

		CommonPage commonPage = new CommonPage(driver);

		// 3. Go to Gear>>Bags
		commonPage.goToNavigationMenuItem("Gear");
		commonPage.selectMenuItem("Bags");

		// 4. Capture the total number of bags available (for eg 14)
		int TotalNumberOfItemsAvailable = commonPage.totalItemsAvailable();

		// 5. Verify that the total number of items displayed on page 1 is 12
		Assert.assertEquals(commonPage.totalItemsAvailableOnPage(1), commonPage.numberOfProductsOnPage());
		ReportUtils.log.pass("Test passed: Number Of Products On Given Page verified");

		// 6. Scroll to the bottom of the page
		commonPage.scrollToProduct(commonPage.numberOfProductsOnPage() - 1);
		
		// 7. Verify that the total number of pages is correct -in this case 2 pages.
		Assert.assertEquals(commonPage.totalNumberOfPages(), 2);
		ReportUtils.log.pass("Test passed: Total Number Of Pages Verified");

		commonPage.goToPageNumber(2);

		// 8. Verify that the total number of items on the last page is correct.
		Assert.assertEquals(commonPage.totalItemsAvailableOnPage(2), commonPage.numberOfProductsOnPage());
		ReportUtils.log.pass("Test passed: Number Of Products On Given Page verified");

	}

}
