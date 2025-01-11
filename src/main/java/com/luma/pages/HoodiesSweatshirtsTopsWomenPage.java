package com.luma.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import utils.ReportUtils;

public class HoodiesSweatshirtsTopsWomenPage extends CommonPage {

	public HoodiesSweatshirtsTopsWomenPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	By listViewLink = By.xpath("(//div[@class='modes']//a[@title='List'])[1]");
	By gridViewLink = By.xpath("(//div[@class='modes']//a[@title='Grid'])[1]");

	// By listViewLink =
	// By.xpath("(//div[@class='modes']//span[text()='List'])[1]");
	By sortByOption = By.id("sorter");
	By productNameLinks = By.xpath("(//strong[contains(@class,'product name')]/a)[1]");

	public void clickListView() {
		driver.findElement(listViewLink).click();
		ReportUtils.log.info("Clicked List View");
	}

	public void clickGridView() {
		driver.findElement(gridViewLink).click();
		ReportUtils.log.info("Clicked Grid View");

	}

	public void sortByProductName() {
		WebElement sortByEle = driver.findElement(sortByOption);

		Select select = new Select(sortByEle);
		select.selectByContainsVisibleText("Product Name");

		ReportUtils.log.info("Sorted Products by Product name");

	}

	public boolean verifyProductNamesAreSorted() {

		List<WebElement> productNamesEle = driver.findElements(productNameLinks);

		ArrayList<String> productNamesArrayList = new ArrayList<>();
		Set<String> productNamesTreeSet = new TreeSet<>();

		for (WebElement productNameEle : productNamesEle) {
			String productName = productNameEle.getText();
			productNamesArrayList.add(productName); // add to ArrayList - follows insertion order as displayed on
													// application page

			productNamesTreeSet.add(productName); // add to TreeSet- sort product names in asc order

		}

		ArrayList<String> productNamesSortedArrayList = new ArrayList<>(productNamesTreeSet);
		if (productNamesArrayList.equals(productNamesSortedArrayList)) {
			return true;
		}
		return false;

//		ArrayList<String> productNamesArrayList = new ArrayList<>();
//		for (WebElement productNameEle : productNamesEle) {
//			String productName=productNameEle.getText();
//			productNamesArrayList.add(productName);  
//		}
//		Set<String> productNamesTreeSet = new TreeSet<>(productNamesArrayList);
//		Iterator<String> iterator = productNamesArrayList.iterator();
//		Iterator<String> iteratorTreeSet = productNamesTreeSet.iterator();
//
//		while (iterator.hasNext() && (iteratorTreeSet.hasNext())) {
//			if (iterator.next().equals(iteratorTreeSet.next())) {
//				return true;
//			}
//
//		}
//		return false;
	}

}
