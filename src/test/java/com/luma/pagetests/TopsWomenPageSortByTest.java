package com.luma.pagetests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.luma.pages.HoodiesSweatshirtsTopsWomenPage;

import utils.ReportUtils;

public class TopsWomenPageSortByTest extends BaseTest {

	@Test
	public void verifySortBySortsItemsCorrectly() {

		HoodiesSweatshirtsTopsWomenPage hoodiesSweatshirtsPage = new HoodiesSweatshirtsTopsWomenPage(driver);

		hoodiesSweatshirtsPage.goToNavigationMenuItem("Women").goToNavigationMenuItem("Tops")
				.selectNavigationMenuItem("Hoodies & Sweatshirts");

		Assert.assertEquals(hoodiesSweatshirtsPage.getPageTitle(), "Hoodies & Sweatshirts - Tops - Women");

		ReportUtils.log.pass("Test passed: Landed successfully on Hoodies & Sweatshirts - Tops - Women Page");

		hoodiesSweatshirtsPage.viewAs("List");
		hoodiesSweatshirtsPage.sortBy("Product Name");

		Assert.assertTrue(hoodiesSweatshirtsPage.isProductNamesSortedOnPage());
		ReportUtils.log.pass(
				"Test passed: Products are displayed in ascending alphabetical order by 'Product Name' in List View");

		hoodiesSweatshirtsPage.viewAs("Grid");
		hoodiesSweatshirtsPage.sortBy("Product Name");

		Assert.assertTrue(hoodiesSweatshirtsPage.isProductNamesSortedOnPage());
		ReportUtils.log.pass(
				"Test passed: Products are displayed in ascending alphabetical order by 'Product Name' in Grid View");

	}
}
