package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.test.report.ExtentTestManager;

public class EditBookObjects {
	BooksPageObjects booksObject = new BooksPageObjects();

	private static By editBtn = By.xpath("html/body/div/div/div[2]/table/tbody/tr[1]/td[3]/a");
	private static By saveBtn = By.xpath("//*[@id=\"bookForm\"]/div[2]/button[1]");
	private static By clearBtn = By.id("btn-clear");
	private static By bookTitle = By.id("title");
	private static By bookYear = By.id("year");
	private static By errorSpan = By.xpath("//*[@id=\"bookForm\"]/div[2]/button[1]");

	public By getBookTitleTextBox() {
		return bookTitle;
	}

	public By getBookYearTextBox() {
		return bookYear;
	}

	public By getSaveBtn() {
		return saveBtn;
	}

	public By getClearBtn() {
		return clearBtn;
	}

	public By getEditBtn() {
		return editBtn;
	}

	public By getErrorSpan() {
		return errorSpan;
	}

	public void editBook(WebDriver driver, String newTitle, String newYear) {
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ExtentTestManager.getTest().log(Status.INFO, "Start editBook test case.");
		booksObject.clickBooksBtn(driver);

		clickEditBtn(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Click edit button.");
		if (!newTitle.equals("") && newTitle.length() >= 8) {
			By bookTitleTBX = getBookTitleTextBox();
			wait.until(ExpectedConditions.visibilityOfElementLocated(bookTitleTBX));
			clearTBX(driver);
			setBook(driver, newTitle, newYear);
			saveBook(driver);

			TableObject editedBook = booksObject.getBook(driver);
			softAssert.assertEquals(editedBook.getTitle(), newTitle);
			softAssert.assertEquals(editedBook.getYear(), newYear);
			ExtentTestManager.getTest().pass("Title updated successfully.");
		} else {
			ExtentTestManager.getTest().fail("Title is empty or less than 8 characters.");
			softAssert.assertTrue(false, "Title is empty or less than 8 characters.");
		}
		softAssert.assertAll();
	}

	public void editBookWithTitleLessThan8Char(WebDriver driver, String newTitle, String newYear) {
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ExtentTestManager.getTest().log(Status.INFO, "Start editBookWithTitleLessThan8Char test case.");
		booksObject.clickBooksBtn(driver);
		
		clickEditBtn(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Click edit button.");
		if (newTitle.equals("") || newTitle.length() < 8) {
			ExtentTestManager.getTest().log(Status.INFO, "Title less than 8 characters.");
			By bookTitleTBX = getBookTitleTextBox();
			wait.until(ExpectedConditions.visibilityOfElementLocated(bookTitleTBX));
			clearTBX(driver);
			setBook(driver, newTitle, newYear);
			saveBook(driver);

			wait.until(ExpectedConditions.visibilityOfElementLocated(getErrorSpan()));
			if (driver.findElement(getErrorSpan()).isDisplayed()) {
				softAssert.assertTrue(true);
			} else {
				ExtentTestManager.getTest().fail("Error message not found.");
				softAssert.assertTrue(false, "Error message not found.");
			}

			softAssert.assertAll();
		}
	}

	public void clearTextBoxWhileEditBook(WebDriver driver) {
		SoftAssert softAssert = new SoftAssert();
		ExtentTestManager.getTest().log(Status.INFO, "Start clearTextBoxWhileEditBook test case.");
		booksObject.clickBooksBtn(driver);

		clickEditBtn(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Click edit button.");
		By bookTitleTBX = getBookTitleTextBox();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(bookTitleTBX));

		clearFields(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Click clear button.");
		boolean isEmpty = isEmpty(driver);
		if (isEmpty) {
			ExtentTestManager.getTest().pass("Text boxes cleared.");
			softAssert.assertTrue(true);
		} else {
			ExtentTestManager.getTest().fail("Unable to clear text boxes.");
			softAssert.assertTrue(false, "Unable to clear text boxes.");
		}

		softAssert.assertAll();
	}

	public void clickEditBtn(WebDriver driver) {
		driver.findElement(getEditBtn()).click();
	}

	public void setBook(WebDriver driver, String title, String year) {
		driver.findElement(getBookTitleTextBox()).sendKeys(title);
		driver.findElement(getBookYearTextBox()).sendKeys(year);
	}

	public void saveBook(WebDriver driver) {
		driver.findElement(getSaveBtn()).click();
	}

	public void clearTBX(WebDriver driver) {
		driver.findElement(getBookTitleTextBox()).clear();
		driver.findElement(getBookYearTextBox()).clear();
	}

	public void clearFields(WebDriver driver) {
		driver.findElement(getClearBtn()).click();
	}

	public boolean isEmpty(WebDriver driver) {
		String titleTextBox = driver.findElement(getBookTitleTextBox()).getAttribute("value");
		String yearTextBox = driver.findElement(getBookYearTextBox()).getAttribute("value");
		if (titleTextBox.equals("") && yearTextBox.equals("")) {
			return true;
		} else {
			return false;
		}
	}
	/*
	 * public TableObject getNewBook(WebDriver driver) { TableObject tObject = new
	 * TableObject(); String title = driver.findElement(book_title).getText();
	 * String year = driver.findElement(book_year).getText();
	 * tObject.setTitle(title); tObject.setYear(year);
	 * 
	 * return tObject; }
	 */

}
