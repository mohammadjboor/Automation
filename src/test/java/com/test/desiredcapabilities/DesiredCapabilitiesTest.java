package com.test.desiredcapabilities;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilitiesTest {
	public static WebDriver chromeCapabilities() {
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();

		ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File("chromedriver.exe")).usingAnyFreePort().build();
		ChromeOptions options = new ChromeOptions();
		options.merge(capabilities);
		ChromeDriver driver = new ChromeDriver(service, options);

		driver.get("http://localhost:8080/");
		driver.manage().window().maximize();
		return driver;
	}
}