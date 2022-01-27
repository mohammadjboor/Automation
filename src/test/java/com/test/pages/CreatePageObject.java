package com.test.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;
import com.test.report.ExtentTestManager;

public class CreatePageObject {
	BooksPageObjects booksObject = new BooksPageObjects();

	private static By createBtn = By.xpath("/html/body/div/div/div[2]/div/a");
	private static By bookTitle = By.xpath("//*[@id=\"title\"]");
	private static By bookYear = By.xpath("//*[@id=\"year\"]");
	private static By saveBtn = By.xpath("/html/body/div/div/div[2]/form/div[2]/button[1]");
	private static By errorMessage = By.xpath("/html/body/div/div/div[2]/form/div[1]/div[1]/b");
	private static By clearBtn = By.xpath("//*[@id=\"btn-clear\"]");

	public By getCreateBtn() {
		return createBtn;
	}

	public By getBookTitleTextBox() {
		return bookTitle;
	}

	public By getBookYearTextBox() {
		return bookYear;
	}

	public By getSaveBtn() {
		return saveBtn;
	}
	
	public By getErrorElement() {
		return errorMessage;
	}
	
	public By getClearBtn() {
		return clearBtn;
	}

	public void createBook(WebDriver driver, String newTitle, String newYear) {
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ExtentTestManager.getTest().log(Status.INFO, "Start createBook test case.");
		booksObject.clickBooksBtn(driver);

		clickCreateBtn(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Click create button.");
		if (!newTitle.equals("") && newTitle.length() >= 8) {
			By bookTitleTBX = getBookTitleTextBox();
			wait.until(ExpectedConditions.visibilityOfElementLocated(bookTitleTBX));
			setBook(driver, newTitle, newYear);
			saveBook(driver);

			List<TableObject> listOfBooks = booksObject.listOfBooks(driver);
			boolean isCreated = containsBook(listOfBooks, newTitle);
			if (isCreated) {
				ExtentTestManager.getTest().pass("Book created successfully.");
				softAssert.assertTrue(true);
			} else {
				ExtentTestManager.getTest().fail("Unable to create a new book.");
				softAssert.assertTrue(false);
			}

		} else {
			ExtentTestManager.getTest().pass("Book title is empty or less than 8 characters.");
			softAssert.assertTrue(false, "Book title is empty or less than 8 characters.");
		}
		softAssert.assertAll();
	}
	
	public void createBookWithTitleLessThan8Char(WebDriver driver, String newTitle, String newYear) {
		SoftAssert softAssert = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ExtentTestManager.getTest().log(Status.INFO, "Start createBookWithTitleLessThan8Char test case.");
		booksObject.clickBooksBtn(driver);
		
		clickCreateBtn(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Click create button.");
		if (newTitle.equals("") || newTitle.length() < 8) {
			ExtentTestManager.getTest().log(Status.INFO, "Title less than 8 characters.");
			By bookTitleTBX = getBookTitleTextBox();
			wait.until(ExpectedConditions.visibilityOfElementLocated(bookTitleTBX));
			setBook(driver, newTitle, newYear);
			saveBook(driver);

			wait.until(ExpectedConditions.visibilityOfElementLocated(getErrorElement()));
			if (driver.findElement(getErrorElement()).isDisplayed()) {
				softAssert.assertTrue(true);
			} else {
				ExtentTestManager.getTest().fail("Error message not found.");
				softAssert.assertTrue(false, "Error message not found.");
			}

			softAssert.assertAll();
		}
	}
	
	public void clearTextBoxWhileCreateBook(WebDriver driver) {
		SoftAssert softAssert = new SoftAssert();
		ExtentTestManager.getTest().log(Status.INFO, "Start clearTextBoxWhileCreateBook test case.");
		booksObject.clickBooksBtn(driver);

		clickCreateBtn(driver);
		By bookTitleTBX = getBookTitleTextBox();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(bookTitleTBX));
		setBook(driver, "Book", "2022");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

	public boolean containsBook(final List<TableObject> list, final String title) {
		return list.stream().filter(o -> o.getTitle().equals(title)).findFirst().isPresent();
	}

	public void clickCreateBtn(WebDriver driver) {
		driver.findElement(getCreateBtn()).click();
	}

	public void setBook(WebDriver driver, String title, String year) {
		driver.findElement(getBookTitleTextBox()).sendKeys(title);
		driver.findElement(getBookYearTextBox()).sendKeys(year);
	}

	public void saveBook(WebDriver driver) {
		driver.findElement(getSaveBtn()).click();
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
	
	public void clearFields(WebDriver driver) {
		driver.findElement(getClearBtn()).click();
	}

}
