package com.test.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.test.desiredcapabilities.DesiredCapabilitiesTest;
import com.test.pages.AuthorsPageObject;
import com.test.pages.BooksPageObjects;
import com.test.pages.CreatePageObject;
import com.test.pages.EditBookObjects;
import com.test.pages.HomePageObjects;

public class TestCases {
	EditBookObjects editBook = new EditBookObjects();
	BooksPageObjects booksObjects = new BooksPageObjects();
	HomePageObjects homePageObjects = new HomePageObjects();
	CreatePageObject createBook = new CreatePageObject();
	AuthorsPageObject authorsObject = new AuthorsPageObject();

	static WebDriver driver = null;
	SoftAssert softAssert = new SoftAssert();

	@BeforeMethod
	public static WebDriver setDriver() {
		System.out.println("Before Starting Test...");
		return driver = DesiredCapabilitiesTest.chromeCapabilities();
	}

	@Test
	public void verifyHomePageByClickingSideBarBtn() {
		homePageObjects.verifyHomePageByClickingSideBarBtn(driver);
	}

	@Test
	public void verifyHomePageByClickingOnNavigationBarBtn() {
		homePageObjects.verifyHomePageByClickingSideBarBtn(driver);
	}

	@Test
	public void verifyHomePageByClickingOnProjectName() {
		homePageObjects.verifyHomePageByClickingSideBarBtn(driver);
	}

	@Test
	public void verifyBooksByClickingOnSideBarBtn() {
		booksObjects.verifyBooksByClickingOnSideBarBtn(driver);
	}

	@Test
	public void verifyBooksByClickingOnNavigationBarBtn() {
		booksObjects.verifyBooksByClickingOnNavigationBarBtn(driver);
	}

	@Test
	public void searchBook() {
		booksObjects.searchBook(driver);
	}

	@Test
	public void createBook() {
		String title = "This is created";
		String year = "2022";
		createBook.createBook(driver, title, year);
	}

	@Test
	public void createBookWithTitleLessThan8Char() {
		String title = "Book";
		String year = "2022";
		createBook.createBookWithTitleLessThan8Char(driver, title, year);
	}

	@Test
	public void clearTextBoxWhileCreateBook() {
		createBook.clearTextBoxWhileCreateBook(driver);
	}

	@Test
	public void editBook() {
		String title = "Edited Book";
		String year = "2022";
		editBook.editBook(driver, title, year);
	}

	@Test
	public void editBookWithTitleLessThan8Char() {
		String title = "Book";
		String year = "2022";
		editBook.editBookWithTitleLessThan8Char(driver, title, year);
	}

	@Test
	public void deleteBook() {
		booksObjects.deleteBook(driver);
	}

	@Test
	public void clearTextBoxWhileEditBook() {
		editBook.clearTextBoxWhileEditBook(driver);
	}
	
	@Test
	public void verifyAuthorsByClickingOnSideBarBtn() {
		authorsObject.verifyAuthorsByClickingOnSideBarBtn(driver);
	}
	
	@Test
	public void verifyAuthorsByClickingOnNavigationBarBtn() {
		authorsObject.verifyAuthorsByClickingOnNavigationBarBtn(driver);
	}

	// @Test
	public void bookList() {
		booksObjects.clickBooksBtn(driver);
		booksObjects.listOfBooks(driver);
	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}

}
