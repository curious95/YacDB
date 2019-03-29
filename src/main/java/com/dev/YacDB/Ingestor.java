package com.dev.YacDB;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Ingestor {

	
	public static void ingest() {
		
		String chromeDriverPath = "/Users/devx/Documents/devx/EC_Wspace/YacDB/res/chromedriver" ;  
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);  
		ChromeOptions options = new ChromeOptions();  
		options.addArguments("headless");
		options.addArguments ("--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");  
		WebDriver driver = new ChromeDriver(options);  
		driver.get("https://www.superyachts.com/motor-yacht-9161/azzam-specification.htm");
		
	}
	
	
}
	