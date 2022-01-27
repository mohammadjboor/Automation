package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.test.report.ExtentTestManager;

public class AuthorsPageObject {
	private static By sideBarAuthorsBtn = By.xpath("/html/body/div/div/div[1]/ul/li[3]/a");
	private static By navigationBarAuthorsBtn = By.xpath("//*[@id=\"navbar\"]/ul/li[3]/a");
	private static By whitelabelErrorMessage = By.xpath("/html/body/h1");
	
	public By getSideBarAuthorsBtn() {
		return sideBarAuthorsBtn;
	}
	
	public By getNavigationBarAuthorsBtn() {
		return navigationBarAuthorsBtn;
	}
	
	public By getWhitelabelErrorMessage() {
		return whitelabelErrorMessage;
	}
	
	public void verifyAuthorsByClickingOnSideBarBtn(WebDriver driver) {
		SoftAssert softAssert = new SoftAssert();
		ExtentTestManager.getTest().log(Status.INFO, "Start verifyAuthorsByClickingOnSideBarBtn test case.");
		clickSideBarAuthorsBtn(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Click side bar Authors button.");
		if (driver.findElement(getWhitelabelErrorMessage()).isDisplayed()) {
			ExtentTestManager.getTest().fail("Authors page not found.");
			softAssert.assertTrue(false);
		} else {
			softAssert.assertTrue(true);
		}
		softAssert.assertAll();
	}
	
	public void verifyAuthorsByClickingOnNavigationBarBtn(WebDriver driver) {
		SoftAssert softAssert = new SoftAssert();
		ExtentTestManager.getTest().log(Status.INFO, "Start verifyAuthorsByClickingOnNavigationBarBtn test case.");
		clickNavigationBarAuthorsBtn(driver);
		ExtentTestManager.getTest().log(Status.INFO, "Click navigation bar Authors button.");
		if (driver.findElement(getWhitelabelErrorMessage()).isDisplayed()) {
			ExtentTestManager.getTest().fail("Authors page not found.");
			softAssert.assertTrue(false);
		} else {
			softAssert.assertTrue(true);
		}
		softAssert.assertAll();
	}
	
	public void clickSideBarAuthorsBtn(WebDriver driver) {
		driver.findElement(getSideBarAuthorsBtn()).click();
	}

	public void clickNavigationBarAuthorsBtn(WebDriver driver) {
		driver.findElement(getNavigationBarAuthorsBtn()).click();
	}

}
