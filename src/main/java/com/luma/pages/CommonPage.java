package com.luma.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import utils.ReportUtils;

public class CommonPage extends BasePage {

	public CommonPage(WebDriver driver) {
		super(driver);
	}

	private Actions action = new Actions(driver);
	private JavascriptExecutor js = (JavascriptExecutor) driver;
	private Select select;

	private LinkedList<String> addedProductNameList = new LinkedList<>();
	private LinkedList<String> productNamesInCartList = new LinkedList<>();

	private String addedProductName;
	private HashMap<String, Integer> addedProductNamesHashMap = new HashMap<>();

	private By createAnAccountLink = By.xpath("(//a[text()='Create an Account'])[1]");

	private By signInLink = By.xpath("(//a[contains(text(), 'Sign In')])[1]");
	private By userHeadingBtn = By.xpath("(//button[@type='button'])[1]");
	private By signOutLink = By.xpath("(//a[contains(string(),'Sign Out')])[1]");

	private By sortByMenu = By.id("sorter");

	private By productNameLinks = By.xpath("//strong[contains(@class,'product name')]/a[@class='product-item-link']");
	private By productAddedConfirmMsg = By.xpath("//div[@class='messages']/div/div");

	private By cartIcon = By.xpath("//a[contains(@class,'showcart')]");
	private By numberOfItemsInCartText = By.xpath("//div[@class='items-total']//span[@class='count']");
	private By proceedToCheckOutBtn = By.xpath("//button[@id='top-cart-btn-checkout']");
	private By productNamesInCart = By.xpath("//ol[@id='mini-cart']//strong[@class='product-item-name']/a");

	private String itemNumberFromToAndTotalList = "(//p[@id='toolbar-amount'])[1]//span";
	private By pageNumberLinksList = By.xpath("(//div[@class='pages']//ul)[2]//span[2]");
	private By itemsPerPageMenu = By.xpath("(//select[@id='limiter'])[2]");

	private By itemsOnThePageList = By.xpath("//ol[@class='products list items product-items']//li");

	public void goToCreateAnAccountLink() {

		driver.findElement(createAnAccountLink).click();
		ReportUtils.log.info("Clicked on Create An Account Link");

	}

	public void signIn() {

		driver.findElement(signInLink).click();
		ReportUtils.log.info("added on Sign In Link");

	}

	public void goToUserHeadingBtn() {

		driver.findElement(userHeadingBtn).click();
		ReportUtils.log.info("added on User Header Button");

	}

	public void signOut() {

		driver.findElement(signOutLink).click();
		ReportUtils.log.info("Clicked on Sign Out Link");

	}

	public Actions moveToNavigationMenuItem(String menuItemName) {

		By navigationMenu = By.xpath("//nav//li//span[text()='" + menuItemName + "']");
		WebElement menuItemEle = driver.findElement(navigationMenu);
		return action.moveToElement(menuItemEle);

	}

	public CommonPage goToNavigationMenuItem(String menuItemName) {

		moveToNavigationMenuItem(menuItemName).perform();
		ReportUtils.log.info("Moved To " + menuItemName + " Menu");
		return this;

	}

	public CommonPage selectNavigationMenuItem(String menuItemName) {

		moveToNavigationMenuItem(menuItemName).click().perform();
		ReportUtils.log.info("Clicked on " + menuItemName);
		return this;

	}

	public void sortBy(String option) {

		WebElement sortByEle = driver.findElement(sortByMenu);

		select = new Select(sortByEle);
		select.selectByContainsVisibleText(option);

		ReportUtils.log.info("Sorted Products by " + option);

	}

	public void viewAs(String mode) {

		By viewAsLink = By.xpath("(//div[@class='modes']//a[@title='" + mode + "'])[1]");
		driver.findElement(viewAsLink).click();
		ReportUtils.log.info("Switched to " + mode + " View");

	}

	public void selectShowItemsPerPage(int numberOfItemsToDisplayPerPage) {

		WebElement itemsPerPageEle = driver.findElement(itemsPerPageMenu);

		select = new Select(itemsPerPageEle);
		select.selectByVisibleText(String.valueOf(numberOfItemsToDisplayPerPage));

	}

	public int getTotalItems() {

		List<WebElement> fromItemToItemAndTotalItemNumbersList = driver
				.findElements(By.xpath(itemNumberFromToAndTotalList));
		int getTotalItems = Integer.parseInt(fromItemToItemAndTotalItemNumbersList.getLast().getText());

		return getTotalItems;
	}

//	public int getTotalPages(int numberOfItemsToDisplayPerPage) {
//
//		int totalPages;
//
//		int totalItems = getTotalItems();
//		if (totalItems > numberOfItemsToDisplayPerPage) {
//			totalPages = (totalItems + numberOfItemsToDisplayPerPage - 1) / numberOfItemsToDisplayPerPage;
//			return totalPages;
//		}
//		return 0;
//	}

	public int getNumberOfItemsOnPage(int pageNumber, int numberOfItemsToDisplayPerPage) {

		if (getTotalNumberOfPages(numberOfItemsToDisplayPerPage) > 0) {
			goToPageNumber(pageNumber);
		}

		List<WebElement> itemsOnThePageEleList = driver.findElements(itemsOnThePageList);

		return itemsOnThePageEleList.size();
	}

	public LinkedList<WebElement> productNamesEleLinkedList() {

		List<WebElement> productNamesOnPageEle = driver.findElements(productNameLinks);
		LinkedList<WebElement> productNamesEleLinkedList = new LinkedList<>(productNamesOnPageEle);

		return productNamesEleLinkedList;
	}

	public boolean isProductNamesSortedOnPage() {

		LinkedList<String> productNamesLinkedList = new LinkedList<>();

		for (WebElement productNameEle : productNamesEleLinkedList()) {
			String productName = productNameEle.getText();
			productNamesLinkedList.add(productName);
		}

		TreeSet<String> productNamesTreeSet = new TreeSet<>(productNamesLinkedList);

		Iterator<String> productNamesLinkedListIterator = productNamesLinkedList.iterator();
		Iterator<String> treeSetiterator = productNamesTreeSet.iterator();

		while (productNamesLinkedListIterator.hasNext() && (treeSetiterator.hasNext())) {
			if (productNamesLinkedListIterator.next().equals(treeSetiterator.next())) {
				return true;
			}
		}
		return false;
	}

	public void addProductToCart(int productNumber, String size, String color) {

		addedProductName = productNamesEleLinkedList().get(productNumber).getText();

		String addedProductDetails = "//a[contains(text(),'" + addedProductName
				+ "')]/parent :: strong//following-sibling :: div";

		By selectColourLink = By.xpath(addedProductDetails + "//div[@option-label='" + color + "']");

		By selectSizeLink = By.xpath(addedProductDetails + "//div[text()='" + size + "']");

		By addToCartButton = By.xpath(addedProductDetails + "//button[@title='Add to Cart']");

		driver.findElement(selectSizeLink).click();
		driver.findElement(selectColourLink).click();
		driver.findElement(addToCartButton).click();
		ReportUtils.log.info("Added " + addedProductName + " of " + size + " color " + color);

		addedProductNameList.add(addedProductName);

	}

	public int getTotalItemsOnPage(int pageNumber, int numberOfItemsToDisplayPerPage) {

		if (getTotalNumberOfPages(numberOfItemsToDisplayPerPage) > 0) {
			goToPageNumber(pageNumber);

			int totalItemsOnPage = Integer
					.parseInt(driver.findElement(By.xpath(itemNumberFromToAndTotalList + "[2]")).getText())
					- Integer.parseInt(driver.findElement(By.xpath(itemNumberFromToAndTotalList + "[1]")).getText())
					+ 1;

			return totalItemsOnPage;
		}
		return getTotalItems();
	}

	public void goToPageNumber(int pageNumber) {

		driver.findElement(By.xpath("(//div[@class='pages']//ul)[2]//span[2][text()='" + pageNumber + "']")).click();
		ReportUtils.log.info("Went to page: " + pageNumber);

	}

	public void scrollToProduct(int productNumber) {

		WebElement addedProductEle = productNamesEleLinkedList().get(productNumber);

		js.executeScript("arguments[0].scrollIntoView();", addedProductEle);

	}

	public int getTotalNumberOfPages(int numberOfItemsToDisplayPerPage) {

		selectShowItemsPerPage(numberOfItemsToDisplayPerPage);

		List<WebElement> totalPageNumberEle = driver.findElements(pageNumberLinksList);
		return totalPageNumberEle.size() - 1;

		// int totalPages=
		// Integer.parseInt(driver.findElement(By.xpath("(//span[text()='Next'])[2]/parent::a//parent::li/preceding-sibling::li[1]//span[2]")).getText());
		// return totalPages;
	}

	public String getProductAddedToCartConfirmMessage() {

		String productAddedMsgEle = driver.findElement(productAddedConfirmMsg).getText();
		return productAddedMsgEle;

	}

	public void goToCart() {

		driver.findElement(cartIcon).click();
		ReportUtils.log.info("Went to shopping cart");

	}

	public int getTotalNumberOfItemsAddedInCart() {

		int numberOfProductsAddedInCart = 0;

		for (String addedItemName : addedProductNameList) {

			Integer count = addedProductNamesHashMap.get(addedItemName);
			if (count==null) {
				addedProductNamesHashMap.put(addedItemName, 1);
			} else {
				addedProductNamesHashMap.put(addedItemName, ++count);
			}
		}

		for (int value : addedProductNamesHashMap.values()) {
			numberOfProductsAddedInCart += value;
		}

		return numberOfProductsAddedInCart;

	}

	public int getTotalOfItemsInCart() {

		int totalOfItemsInCart = Integer.parseInt(driver.findElement(numberOfItemsInCartText).getText());
		return totalOfItemsInCart;

	}

	public boolean isProductNamesInCartCorrect() {

		List<WebElement> productNamesInCartEleList = driver.findElements(productNamesInCart);
		for (WebElement productNamesInCartEle : productNamesInCartEleList) {
			productNamesInCartList.add(productNamesInCartEle.getText());
		}

		Iterator<String> addedProductNameListIterator = addedProductNameList.iterator();
		Iterator<String> productNamesInCartListIterator = productNamesInCartList.reversed().iterator();

		while (addedProductNameListIterator.hasNext() && productNamesInCartListIterator.hasNext()) {

			if (addedProductNameListIterator.next().equals(productNamesInCartListIterator.next())) {
				return true;
			}
		}
		return false;

	}

	public boolean isQuantityOfTheaddedProductInCartCorrect() {

		for (String getProductName : addedProductNamesHashMap.keySet()) {

			WebElement quantityofTheaddedProductInCartTextEle = driver.findElement(By.xpath("//a[text()='"
					+ getProductName
					+ "']/parent::strong[@class='product-item-name']/following-sibling::div[2]//label[text()='Qty']/following-sibling :: input"));

			int quantityOfTheaddedProductInCart = Integer
					.parseInt(quantityofTheaddedProductInCartTextEle.getDomAttribute("data-item-qty"));

			int getQuantityOfTheProductInCart = addedProductNamesHashMap.get(addedProductName);

			if (getQuantityOfTheProductInCart == quantityOfTheaddedProductInCart) {
				return true;
			}
		}
		return false;
	}

	public void proceedToCheckOut() {

		driver.findElement(proceedToCheckOutBtn).click();
		ReportUtils.log.info("Proceeded to checkout");

	}

}
