package com.luma.pages;

import java.util.ArrayList;
import java.util.HashMap;
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

	Actions action = new Actions(driver);
	JavascriptExecutor js = (JavascriptExecutor) driver;

	private By userHeadingBtn = By.xpath("(//button[@type='button'])[1]");
	private By signOutLink = By.xpath("(//a[contains(string(),'Sign Out')])[1]");

	private By createAnAccountLink = By.xpath("(//a[text()='Create an Account'])[1]");
	private By signInLink = By.xpath("(//a[contains(text(), 'Sign In')])[1]");

	private By gridView = By.xpath("(//div[@class='modes']//strong[@data-value='grid'])[1]");

	private By sortByOption = By.id("sorter");

	private By cartIcon = By.xpath("//a[contains(@class,'showcart')]");
	private By counterNumberOnCart = By.xpath("//span[@class='counter-number']");
	private By numberOfItemsInCart = By.xpath("//div[@class='items-total']//span[@class='count']");

	private By proceedToCheckOutBtn = By.xpath("//button[@id='top-cart-btn-checkout']");

	private By totalItems = By.xpath("//p[contains(text(),'  Items ')]//span[3]");

	public void goToCreateAnAccountLink() {

		driver.findElement(createAnAccountLink).click();
		ReportUtils.log.info("Clicked on Create An Account Link");

	}

	public void signIn() {

		driver.findElement(signInLink).click();
		ReportUtils.log.info("selected on Sign In Link");

	}

	public void goToUserHeadingBtn() {

		driver.findElement(userHeadingBtn).click();
		ReportUtils.log.info("selected on User Header Button");

	}

	public void signOut() {

		driver.findElement(signOutLink).click();
		ReportUtils.log.info("Clicked on Sign Out Link");

	}

	public void goToNavigationMenuItem(String menuItemName) {
		By navigationMenu = By.xpath("//nav//li//span[text()='" + menuItemName + "']");

		WebElement menuItemEle = driver.findElement(navigationMenu);

		action.moveToElement(menuItemEle).perform();
		ReportUtils.log.info("Moved To " + menuItemName + " Menu");

	}

	public void selectMenuItem(String menuItemName) {

		By navigationMenu = By.xpath("//nav//li//span[text()='" + menuItemName + "']");

		WebElement menuItemEle = driver.findElement(navigationMenu);

		action.moveToElement(menuItemEle).click().perform();
		ReportUtils.log.info("Clicked on " + menuItemName);

	}

	public void sortBy(String option) {
		WebElement sortByEle = driver.findElement(sortByOption);

		Select select = new Select(sortByEle);
		select.selectByContainsVisibleText(option);

		ReportUtils.log.info("Sorted Products by " + option);

	}

	public void viewAs(String mode) {
		By viewAsLink = By.xpath("(//div[@class='modes']//a[@title='" + mode + "'])[1]");
		driver.findElement(viewAsLink).click();
		ReportUtils.log.info("Switched to " + mode + " View");
	}

	public boolean isGridViewActive() {
		String ele = driver.findElement(gridView).getDomAttribute("class");
		if (ele.contains("active")) {
			return true;
		}
		return false;
	}
	
	List<WebElement> productNamesOnPage;
	String selectedProductName;
	HashMap<String, Integer> selectedProductsHashMap = new HashMap<>();
	
	public int numberOfProductsOnPage() {

		By productNameLinks = By.xpath("//strong[contains(@class,'product name')]/a[@class='product-item-link']");
		productNamesOnPage = driver.findElements(productNameLinks);
		return productNamesOnPage.size();

	}

//	public int numberOfProductsOnPage(int pageNumber) {
//
//		By productNameLinks = By.xpath("//span[text()='" + pageNumber
//				+ "']/parent:: strong/parent::li/parent::ul/parent::div[@class='pages']//parent::div//preceding-sibling::div//strong[contains(@class,'product name')]/a[@class='product-item-link']");
//		productNamesOnPage = driver.findElements(productNameLinks);
//		return productNamesOnPage.size();
//
//	}

	public boolean isProductNamesSortedOnPage() {

		numberOfProductsOnPage();

		ArrayList<String> productNamesArrayList = new ArrayList<>();
		TreeSet<String> productNamesTreeSet = new TreeSet<>();

		for (WebElement productNameEle : productNamesOnPage) {
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
	}
//		ArrayList<String> productNamesArrayList = new ArrayList<>();
//		for (WebElement productNameEle : productNamesEle) {
//			String productName=productNameEle.getText();
//			productNamesArrayList.add(productName);  
//		}
//		TreeSet<String> productNamesTreeSet = new TreeSet<>(productNamesArrayList);
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
//	}

	public void scrollToProduct(int productNumber) {

		WebElement selectedProductEle = productNamesOnPage.get(productNumber);

		js.executeScript("arguments[0].scrollIntoView();", selectedProductEle);

	}

	LinkedList<String> selectedProductNameList = new LinkedList<>();

	public void addProductToCart(int productNumber, String size, String color) {

		selectedProductName = productNamesOnPage.get(productNumber).getText();

		String selectedProductDetails = "//a[contains(text(),'" + selectedProductName
				+ "')]/parent :: strong//following-sibling :: div";

		By selectColourLink = By.xpath(selectedProductDetails + "//div[@option-label='" + color + "']");

		By selectSizeLink = By.xpath(selectedProductDetails + "//div[text()='" + size + "']");

		By addToCartButton = By.xpath(selectedProductDetails + "//button[@title='Add to Cart']");

		driver.findElement(selectSizeLink).click();
		driver.findElement(selectColourLink).click();
		driver.findElement(addToCartButton).click();
		ReportUtils.log.info("Added " + selectedProductName + " of " + size + " color " + color);

		selectedProductNameList.add(selectedProductName);
	}

	public boolean isCorrectProductAddedToCartMessageDisplayed() {
		// String shoppingCartLinkText =
		// driver.findElement(By.xpath("//div[@class='messages']//a")).getText();
		By productAddedConfirmMsg = By.xpath("//div[@class='messages']/div/div");

		String productAddedMsgEle = driver.findElement(productAddedConfirmMsg).getText();
		// if (productAddedMsgEle.contains(" You added " + selectedProductName + " to
		// your " + shoppingCartLinkText + ".")) {
		if (productAddedMsgEle.contains(" You added " + selectedProductName + " to your ")) {
			return true;
		}
		return false;
	}

	public void goToCart() {

		driver.findElement(cartIcon).click();
		ReportUtils.log.info("Went to shopping cart");

	}

	public int totalNumberOfProductsAddedInCart() {

		int numberOfProductsAddedInCart = 0;

		for (String selectedProductName : selectedProductNameList) {

			Integer count = selectedProductsHashMap.get(selectedProductName);

			if (count == null) {
				selectedProductsHashMap.put(selectedProductName, 1);
			} else {
				selectedProductsHashMap.put(selectedProductName, count++);
			}
		}

		for (int value : selectedProductsHashMap.values()) {
			numberOfProductsAddedInCart += value;
		}

		return numberOfProductsAddedInCart;

	}

	public int totalNumberOfProductsInCart() {
		int totalNumberOfItemsInCartText = Integer.parseInt(driver.findElement(numberOfItemsInCart).getText());
		return totalNumberOfItemsInCartText;
	}

	public int counterNumberOnCart() {

		int counterNumberOnCartText = Integer.parseInt(driver.findElement(counterNumberOnCart).getText());
		return counterNumberOnCartText;
	}

	public boolean isProductNamesInCartCorrect() {

		for (String getProductName : selectedProductsHashMap.keySet()) {
			if (getProductName.equals(selectedProductName)) {
				return true;
			}
		}
		return false;
	}

	public boolean isQuantityOfTheSelectedProductInCartCorrect() {

		for (String getProductName : selectedProductsHashMap.keySet()) {

			WebElement quantityofTheSelectedProductInCartTextEle = driver.findElement(By.xpath("//a[text()='"
					+ getProductName
					+ "']/parent::strong[@class='product-item-name']/following-sibling::div[2]//label[text()='Qty']/following-sibling :: input"));

			int quantityOfTheSelectedProductInCart = Integer
					.parseInt(quantityofTheSelectedProductInCartTextEle.getDomAttribute("data-item-qty"));

			int getQuantityOfTheProductInCart = selectedProductsHashMap.get(selectedProductName);

			if (getQuantityOfTheProductInCart == quantityOfTheSelectedProductInCart) {
				return true;
			}
		}
		return false;

	}

	public void proceedToCheckOut() {
		driver.findElement(proceedToCheckOutBtn).click();
		ReportUtils.log.info("Proceeded to checkout");

	}

	public int totalItemsAvailable() {
		int totalNumberOfItems = Integer.parseInt(driver.findElement(totalItems).getText());
		return totalNumberOfItems;
	}

	public int totalItemsAvailableOnPage(int pageNumber) {
		numberOfProductsOnPage();
		int totalNumberOfItemsOnPage = Integer.parseInt(driver.findElement(By.xpath("//span[text()='" + pageNumber
				+ "']/parent:: strong/parent::li/parent::ul/parent::div[@class='pages']//preceding-sibling::p[contains(text(),'  Items ')]//span[2]"))
				.getText())
				- Integer.parseInt(driver.findElement(By.xpath("//span[text()='" + pageNumber
						+ "']/parent:: strong/parent::li/parent::ul/parent::div[@class='pages']//preceding-sibling::p[contains(text(),'  Items ')]//span[1]"))
						.getText())
				+ 1;
//		int totalNumberOfItemsOnPage = Integer
//				.parseInt(driver.findElement(By.xpath("//p[contains(text(),'  Items ')]//span[2]")).getText())
//				- Integer.parseInt(driver.findElement(By.xpath("//p[contains(text(),'  Items ')]//span[1]")).getText())
//				+ 1;
		return totalNumberOfItemsOnPage;
	}

	public int totalNumberOfPages() {
		List<WebElement> totalPageNumberEle = driver.findElements(By.xpath("(//div[@class='pages']//ul)[2]//span[2]"));
		return totalPageNumberEle.size() - 1;

		// int totalPages=
		// Integer.parseInt(driver.findElement(By.xpath("//span[text()='Next'])[1]/parent::a//parent::li/preceding-sibling::li[1]//span[2]")).getText());
		// return totalPages;
	}

	public void goToPageNumber(int pageNumber) {

		driver.findElement(By.xpath("(//div[@class='pages']//ul)[2]//span[2][text()='" + pageNumber + "']")).click();
		ReportUtils.log.info("Went to page: " + pageNumber);

	}
}
