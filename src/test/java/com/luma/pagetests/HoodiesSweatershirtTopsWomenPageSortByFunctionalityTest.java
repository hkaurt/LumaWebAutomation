package com.luma.pagetests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.luma.pages.HoodiesSweatshirtsTopsWomenPage;

import utils.ReportUtils;
/*
 * @author Harpreet
 * TC002

Title: To verify that the "Sort By" functionality correctly displays products in ascending alphabetical order by "Product Name" in both List View and Grid View.
                   
Steps to Execute:
1.	Open the browser and navigate to https://magento.softwaretestingboard.com/.

 */
public class HoodiesSweatershirtTopsWomenPageSortByFunctionalityTest extends BaseTest {

	@Test
	public void verifySortByFunctionality() {

		HoodiesSweatshirtsTopsWomenPage hoodiesSweatshirtsPage = new HoodiesSweatshirtsTopsWomenPage(driver);
		
		//2.	From the navigation menu, hover over "Women," then select "Tops", followed by "Hoodies & Sweatshirts".
		
		hoodiesSweatshirtsPage.goToNavigationMenuItem("Women");
		hoodiesSweatshirtsPage.goToNavigationMenuItem("Tops");
		hoodiesSweatshirtsPage.selectMenuItem("Hoodies & Sweatshirts");

		//3.	Verify that the category landing page loads successfully.
		
		Assert.assertEquals(hoodiesSweatshirtsPage.getPageTitle(), "Hoodies & Sweatshirts - Tops - Women");
		// Assert.assertEquals(hoodiesSweatshirtsPage.getPageHeader(), "Hoodies &
		// Sweatshirts");
		ReportUtils.log.pass("Test passed: Landed successfully on Hoodies & Sweatshirts - Tops - Women Page");

		//4. In List View: From the "Sort By" dropdown, select "Product Name."
		//Observe the product order and verify that products are sorted in ascending alphabetical order.

		hoodiesSweatshirtsPage.viewAs("List");
		hoodiesSweatshirtsPage.sortBy("Product Name");

		Assert.assertEquals(hoodiesSweatshirtsPage.isProductNamesSortedOnPage(), true);
		ReportUtils.log.pass(
				"Test passed: Products are displayed in ascending alphabetical order by 'Product Name' in List View");

        //5.Switch to Grid View:From the "Sort By" dropdown, select "Product Name."
		//Observe the product order and verify that products are sorted in ascending alphabetical order.

		hoodiesSweatshirtsPage.viewAs("Grid");
		hoodiesSweatshirtsPage.sortBy("Product Name");

		Assert.assertEquals(hoodiesSweatshirtsPage.isProductNamesSortedOnPage(), true);
		ReportUtils.log.pass(
				"Test passed: Products are displayed in ascending alphabetical order by 'Product Name' in Grid View");

	}
}
