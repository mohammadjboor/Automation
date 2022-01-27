package com.test.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import com.test.report.ExtentTestManager;

public class BooksPageObjects {

	private static By sideBarBooksBtn = By.xpath("/html/body/div/div/div[1]/ul/li[2]/a");
	private static By navigationBarBooksBtn = By.xpath("//*[@id=\"navbar\"]/ul/li[2]/a");
	private static By booksTable = By.xpath("/html/body/div/div/div[2]/table");
	private static By createBtn = By.xpath("html/body/div/div/div[2]/div/a");
	private static By booksListElement = By.xpath("/html/body/div/div/div[2]/table/tbody/tr");
	private static By bookTitleInBooksPage = By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[1]");
	private static By bookYearInBooksPage = By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[2]");
	private static By deleteBtn = By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[4]/a");
	private static By editBtn = By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[3]/a");
	private static By searchBtn = By.xpath("//*[@id=\"searchForm\"]/button");
	private static By searchBar = By.xpath("//*[@id=\"searchID\"]");
	private static By whitelabelErrorMessage = By.xpath("/html/body/h1");

	public By getTooksTable() {
		return booksTable;
	}

	public By getSideBarBooksBtn() {
		return sideBarBooksBtn;
	}

	public By getNavigationBarBooksBtn() {
		return navigationBarBooksBtn;
	}

	public By getBooksListElement() {
		return booksListElement;
	}

	public By getBookTitleInBooksPage() {
		return bookTitleInBooksPage;
	}

	public By getBookYearInBooksPage() {
		return bookYearInBooksPage;
	}

	public By getDeleteBtn() {
		return deleteBtn;
	}

	public By getCreateBtn() {
		return createBtn;
	}

	public By getSearchBtn() {
		return searchBtn;
	}

	public By getSearchBar() {
		return searchBar;
	}

	public By getWhitelabelErrorMessage() {
		return whitelabelErrorMessage;
	}

	public void clickBooksBtn(WebDriver driver) {
		clickSideBarBooksBtn(driver);
		tableIsDisplayed(driver);
	}

	public void verifyBooksByClickingOnSideBarBtn(WebDriver driver) {
		SoftAssert softAssert = new SoftAssert();
		ExtentTestManager.getTest().log(Status.INFO, "Start verifyBooksByClickingOnSideBarBtn test case.");
		clickSideBarBooksBtn(driver);
		boolean tableIsDisplayed = tableIsDisplayed(driver);
		if (tableIsDisplayed) {
			List<TableObject> list2 = listOfBooks(driver);
			if (list2 != null) {
				ExtentTestManager.getTest().pass("Books verified successfully.");
				softAssert.assertTrue(true);
			} else {
				ExtentTestManager.getTest().fail("Error in retrive books.");
				softAssert.assertTrue(false, "Error in retrive books");
			}

		} else {
			ExtentTestManager.getTest().fail("Books table not found.");
			Assert.assertTrue(false, "Books table not found.");
		}

		softAssert.assertAll();
	}

	public void verifyBooksByClickingOnNavigationBarBtn(WebDriver driver) {
		SoftAssert softAssert = new SoftAssert();
		ExtentTestManager.getTest().log(Status.INFO, "Start verifyBooksByClickingOnNavigationBarBtn test case.");
		clickNavigationBarBooksBtn(driver);
		boolean tableIsDisplayed = tableIsDisplayed(driver);
		if (tableIsDisplayed) {
			List<TableObject> list2 = listOfBooks(driver);
			if (list2 != null) {
				ExtentTestManager.getTest().pass("Books verified successfully.");
				softAssert.assertTrue(true);
			} else {
				ExtentTestManager.getTest().fail("Error in retrive books.");
				softAssert.assertTrue(false, "Error in retrive books");
			}

		} else {
			ExtentTestManager.getTest().fail("Books table not found.");
			Assert.assertTrue(false, "Books table not found.");
		}

		softAssert.assertAll();
	}

	public void deleteBook(WebDriver driver) {
		SoftAssert softAssert = new SoftAssert();
		ExtentTestManager.getTest().log(Status.INFO, "Start verifyBooksByClickingOnNavigationBarBtn test case.");
		clickBooksBtn(driver);
		TableObject obj1 = getBook(driver);
		clickDeleteBook(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Click delete button.");
		TableObject obj2 = getBook(driver);

		boolean isDeleted = isDeleted(obj1, obj2);
		if (isDeleted) {
			ExtentTestManager.getTest().pass("Books deleted successfully.");
			softAssert.assertTrue(true);
		} else {
			ExtentTestManager.getTest().fail("Unable to delete book.");
			softAssert.assertTrue(false);
		}
		softAssert.assertAll();
	}

	public void searchBook(WebDriver driver) {
		SoftAssert softAssert = new SoftAssert();
		ExtentTestManager.getTest().log(Status.INFO, "Start searchBook test case.");
		clickSearchBtn(driver);
		if (driver.findElement(getWhitelabelErrorMessage()).isDisplayed()) {
			ExtentTestManager.getTest().fail("Page not found.");
			softAssert.assertTrue(false);
		} else {
			softAssert.assertTrue(true);
		}
		softAssert.assertAll();
	}

	public List<TableObject> listOfBooks(WebDriver driver) {
		List<WebElement> booksListInWebElements = driver.findElements(getBooksListElement());
		int rowCount = booksListInWebElements.size();
		List<TableObject> lisOfBooks = new ArrayList<TableObject>();
		for (int i = 0; i < rowCount; i++) {
			TableObject book = new TableObject();
			int num = i + 1;
			String titleXpath = "/html/body/div/div/div[2]/table/tbody/tr[" + num + "]/td[1]";
			String yearXpath = "/html/body/div/div/div[2]/table/tbody/tr[" + num + "]/td[2]";
			String title = booksListInWebElements.get(i).findElement(By.xpath(titleXpath)).getText();
			String year = booksListInWebElements.get(i).findElement(By.xpath(yearXpath)).getText();
			book.setTitle(title);
			book.setYear(year);
			lisOfBooks.add(book);
		}
		return lisOfBooks;
	}

	public TableObject getBook(WebDriver driver) {
		TableObject tObject = new TableObject();
		String title = driver.findElement(getBookTitleInBooksPage()).getText();
		String year = driver.findElement(getBookYearInBooksPage()).getText();
		tObject.setTitle(title);
		tObject.setYear(year);

		return tObject;
	}

	public void clickEditBtn(WebDriver driver) {
		driver.findElement(editBtn).click();
	}

	public void clickDeleteBook(WebDriver driver) {
		driver.findElement(getDeleteBtn()).click();

	}

	public void clickSideBarBooksBtn(WebDriver driver) {
		driver.findElement(getSideBarBooksBtn()).click();
	}

	public void clickNavigationBarBooksBtn(WebDriver driver) {
		driver.findElement(getNavigationBarBooksBtn()).click();
	}

	public void clickSearchBtn(WebDriver driver) {
		driver.findElement(getSearchBtn()).click();
	}

	public void sendTextToSearchBar(WebDriver driver, String bookTitle) {
		driver.findElement(getSearchBar()).sendKeys(bookTitle);

	}

	public boolean tableIsDisplayed(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		By booksTable = getTooksTable();
		By createBtn = getCreateBtn();
		wait.until(ExpectedConditions.visibilityOfElementLocated(booksTable));
		wait.until(ExpectedConditions.visibilityOfElementLocated(createBtn));
		boolean tableIsDisplayed = driver.findElement(booksTable).isDisplayed();
		if (tableIsDisplayed) {
			Assert.assertTrue(true);
			return true;
		} else {
			Assert.assertTrue(false);
			return false;
		}

	}

	public boolean isDeleted(TableObject obj1, TableObject obj2) {
		if (!(obj1.getTitle().equals(obj2.getTitle())) || !(obj1.getYear().equals(obj2.getYear()))) {
			return true;
		} else {
			return false;
		}
	}

}
