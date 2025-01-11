package com.luma.womenpagetests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.luma.loginpagetests.BaseTest;
import com.luma.pages.HoodiesSweatshirtsTopsWomenPage;

import utils.ReportUtils;

public class SortByFunctionalityTest extends BaseTest {

	@Test
	public void verifySortByFunctionality() {

		HoodiesSweatshirtsTopsWomenPage hoodiesSweatshirtsPage = new HoodiesSweatshirtsTopsWomenPage(driver);
		hoodiesSweatshirtsPage.hoverWomenThenTopsThenClickHoodiesSweatshirts();

		Assert.assertEquals(hoodiesSweatshirtsPage.getPageTitle(), "Hoodies & Sweatshirts - Tops - Women");
		// Assert.assertEquals(hoodiesSweatshirtsPage.getPageHeader(), "Hoodies &
		// Sweatshirts");
		ReportUtils.log.pass("Test passed: Landed successfully on Hoodies & Sweatshirts - Tops - Women Page");

		hoodiesSweatshirtsPage.clickListView();
		hoodiesSweatshirtsPage.sortByProductName();

		Assert.assertEquals(hoodiesSweatshirtsPage.verifyProductNamesAreSorted(), true);
		ReportUtils.log.pass(
				"Test passed: Products are displayed in ascending alphabetical order by 'Product Name' in List View");

		hoodiesSweatshirtsPage.clickGridView();
		hoodiesSweatshirtsPage.sortByProductName();

		Assert.assertEquals(hoodiesSweatshirtsPage.verifyProductNamesAreSorted(), true);
		ReportUtils.log.pass(
				"Test passed: Products are displayed in ascending alphabetical order by 'Product Name' in Grid View");

	}
}
