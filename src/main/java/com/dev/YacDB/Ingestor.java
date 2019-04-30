package com.dev.YacDB;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Ingestor {

	public static WebDriver driver;
	
	public static void initDriver() {
		
		String chromeDriverPath = "/Users/devx/Documents/devx/EC_Wspace/YacDB/res/chromedriverr" ;  
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);  
		ChromeOptions options = new ChromeOptions();  
		options.addArguments("headless");
		options.addArguments ("--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");  
		driver = new ChromeDriver(options);  
		
	}
	
	public static void closeDriver() {
		
		driver.close();
		
	}
	
	public static String ingest(String URL) {
		
		// Ingestion Code Here
		
		driver.get(URL);
		
		return driver.getPageSource();

		
	}
	
	
}
	