package com.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.test.report.ExtentTestManager;

public class HomePageObjects {

	private static By homePageTitle = By.xpath("/html/body/div/div/div[2]");
	private static By projectNameBtn = By.xpath("/html/body/nav/div/div[1]/a");
	private static By navigationBarHomeBtn = By.xpath("//*[@id=\"navbar\"]/ul/li[1]/a");
	private static By sideBarHomeBtn = By.xpath("/html/body/div/div/div[1]/ul/li[1]/a");

	public By getHomePageTitle() {
		return homePageTitle;
	}
	
	public By getNavigationBarHomeBtn() {
		return navigationBarHomeBtn;
	}

	public By getSideBarHomeBtn() {
		return sideBarHomeBtn;
	}

	public By getProjectNameBtn() {
		return projectNameBtn;
	}

	public void verifyHomePageByClickingSideBarBtn(WebDriver driver) {
		ExtentTestManager.getTest().log(Status.INFO, "Start verifyHomePageByClickingSideBarBtn test case.");
		clickSideBarHomeBtn(driver);
		verifyHomePageTitle(driver);
	}

	public void verifyHomePageByClickingOnNavigationBarBtn(WebDriver driver) {
		ExtentTestManager.getTest().log(Status.INFO, "Start verifyHomePageByClickingOnNavigationBarBtn test case.");
		clickNavigationBarHomeBtn(driver);
		verifyHomePageTitle(driver);
	}

	public void verifyHomePageByClickingOnProjectName(WebDriver driver) {
		ExtentTestManager.getTest().log(Status.INFO, "Start verifyHomePageByClickingOnProjectName test case.");
		clickProjectNameBtn(driver);
		verifyHomePageTitle(driver);
	}

	public void verifyHomePageTitle(WebDriver driver) {
		SoftAssert softAssert = new SoftAssert();
		ExtentTestManager.getTest().log(Status.INFO, "Open home page.");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(getHomePageTitle()));
		if (driver.findElement(getHomePageTitle()).isDisplayed()) {
			ExtentTestManager.getTest().pass("Verify home page.");
			softAssert.assertTrue(true);
		} else {
			ExtentTestManager.getTest().fail("Element not found.");
			softAssert.assertTrue(false, "Element not found.");
		}
		softAssert.assertAll();
	}

	public void clickSideBarHomeBtn(WebDriver driver) {
		driver.findElement(getSideBarHomeBtn()).click();
	}

	public void clickNavigationBarHomeBtn(WebDriver driver) {
		driver.findElement(getNavigationBarHomeBtn()).click();
	}

	public void clickProjectNameBtn(WebDriver driver) {
		driver.findElement(getProjectNameBtn()).click();
	}

}
