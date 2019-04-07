package com.dev.YacDB;

import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;

public class YacTo {

	static HashSet<String> urlSet = new HashSet<String>();
	final static int range = 23; // Static number during the design phase

	public static void startProcess() {

		Ingestor ingestor = new Ingestor();
		ingestor.initDriver(); // Driver instance begins here

		for (int i = 0; i < 1; i++) {
			String URL = "https://www.yatco.com/search?pg=" + i
					+ "&builder=&loa1=100&loa2=&lengthUnit=1&countryID=0&price1=&price2=&currency=USD&year1=&year2=&typeID=0&vesselName=&NR=3";

			Document doc = Jsoup.parse(ingestor.ingest(URL));

			Elements listItems = doc.getElementsByClass("vessel-details-link").select("a");

			for (Element item : listItems) {

				// System.out.println(item.attr("href"));
				urlSet.add(item.attr("href"));
			}

			for (String url : urlSet) {

				// System.out.println("https://www.yatco.com" + url);
				ingestor.ingest("https://www.superyachts.com" + url);
				String year = ingestor.driver.findElement(By.cssSelector(
						"body > div:nth-child(7) > div.profile-de > div > div.detail-content > div > div > div > div:nth-child(2) > div:nth-child(1) > ul > li:nth-child(5) > table > tbody > tr > td:nth-child(2)"))
						.getText();

			}

			// sleep function to avoid load
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ingestor.closeDriver(); // Driver closes here

	}
}
