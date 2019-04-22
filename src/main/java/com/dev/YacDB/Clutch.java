package com.dev.YacDB;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;

import com.opencsv.CSVWriter;

public class Clutch {

	static HashSet<String> urlSet = new HashSet<String>();
	final static int limit = 131; // Static number during the design phase

	static String name, description, star, no_reviews, website, last_review, service_focus, price, avarage_est,
			location, link, email, phone_number;

	@SuppressWarnings("static-access")
	public static void startProcess() throws IOException {

		File file = new File("jsonfiles/" + "Clutch" + ".csv");
		FileWriter outputfile = new FileWriter(file);
		String[] headerString = { "Name", "Description", "Star", "No. Of reviews", "Website", "Last review",
				"Service focus", "Price", "Avarage	Est.", "Location", "Link", "Email", "Phone number" };
		CSVWriter writer = new CSVWriter(outputfile);
		writer.writeNext(headerString);
		try {
			FileUtils.writeStringToFile(file, headerString + "\n", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Ingestor ingestor = new Ingestor();
		ingestor.initDriver(); // Driver instance begins here

		for (int i = 0; i < limit; i++) {

			urlSet.clear();
			String URL = "https://clutch.co/developers/eastern-europe?page=" + i;

			Document doc = Jsoup.parse(ingestor.ingest(URL));

			Elements listItems = doc.getElementsByClass("provider-row");

			for (Element item : listItems) {

				// System.out.println(item.attr("href"));
				// urlSet.add(item.attr("href"));

				name = item.getElementsByClass("company-name").get(0).text();
				System.out.println(name + " " + "  " + i);
				try {
					description = item.getElementsByClass("tagline").get(0).text();
				} catch (Exception e9) {
					// TODO Auto-generated catch block
					// e9.printStackTrace();
				}

				try {
					star = item.getElementsByClass("rating").get(0).text();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
				try {
					no_reviews = item.getElementsByClass("reviews-count").get(0).text();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
				try {
					website = item.getElementsByClass("website-link").get(0).getElementsByTag("a").get(0).attr("href");
				} catch (Exception e8) {
					// TODO Auto-generated catch block
					// e8.printStackTrace();
				}
				try {
					last_review = item.getElementsByClass("blockquote-in-module").get(0).text();
				} catch (Exception e7) {
					// TODO Auto-generated catch block
					// e7.printStackTrace();
				}

				try {
					Elements services = item.getElementsByClass("chartAreaContainer").get(0).getElementsByClass("grid");

					service_focus = "";
					for (Element sitem : services) {
						// System.out.println(sitem.attr("data-content"));
						service_focus += sitem.attr("data-content").replaceAll("<[^>]*>", "").replace("%", "%  ")
								+ "\n";
					}
				} catch (Exception e6) {
					// TODO Auto-generated catch block
					// e6.printStackTrace();
				}
				// System.out.println(service_focus);

				try {
					price = item.getElementsByClass("list-item").get(0).text();
				} catch (Exception e5) {
					// TODO Auto-generated catch block
					// e5.printStackTrace();
				}
				try {
					avarage_est = item.getElementsByClass("list-item").get(1).text();
				} catch (Exception e4) {
					// TODO Auto-generated catch block
					// e4.printStackTrace();
				}
				try {
					location = item.getElementsByClass("list-item").get(3).text();
				} catch (Exception e3) {
					// TODO Auto-generated catch block
					// e3.printStackTrace();
				}
				try {
					link = "https://clutch.co"
							+ item.getElementsByClass("company-name").get(0).getElementsByTag("a").get(0).attr("href");
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					// e2.printStackTrace();
				}
				try {
					email = item.getElementsByAttributeValueStarting("href", "mailto").get(0).text();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				}
				try {
					phone_number = item.getElementsByClass("item __color6a").get(0).text();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}

				// System.out.println(email+" "+phone_number);
				// System.out.println(service_focus);
			}

			// sleep function to avoid load
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

			// File file = new File("jsonfiles/" + "Clutch" + ".csv");
			// String headerString = "Name;Description;Star;Nr. Of reviews;Website;Last
			// review;Service focus;Price;Avarage Est.;Locatiom;Link;Email;Phone number";
			String[] dataStr = { name, description, star, no_reviews, website, last_review, service_focus, price,
					avarage_est, location, link, email, phone_number };
			try {
				writer.writeNext(dataStr);
				// FileUtils.writeStringToFile(file, dataStr + "\n", true);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			writer.flush();
			// sleep function to avoid load
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		writer.close();
		ingestor.closeDriver(); // Driver closes here

	}

}
