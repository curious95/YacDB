package com.dev.YacDB;

import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class YacWor {
	static HashSet<String> urlSet = new HashSet<String>();
	final static int limit = 568; // Static number during the design phase

	static String name, type, yac_model, sub_type, builder, naval_architect, exterior_designers, interior_designer,
			year, flag, mca, class_, hull_nb, hull_colour, length_overall, length_at_waterline, beam, draft_min,
			draft_max, gross_tonnage, guests, cabins_total, cabins, crew, hull_configuration, hull_material,
			superstructure, deck_material, decks_nb, quantity, fuel_type, manufacturer, eng_model, power, total_power,
			propulsion, max_speed, cruising_speed, range, fuel_capacity, water_capacity, generator, stabilizers,
			thrusters, amenities;
	static int ctr = 0;

	@SuppressWarnings("static-access")
	public static void startProcess() {

		Ingestor ingestor = new Ingestor();
		ingestor.initDriver(); // Driver instance begins here

		for (int i = 1; i < limit; i += 50) {
			urlSet.clear();
			String URL = "https://www.yachtworld.co.uk/core/listing/cache/searchResults.jsp?slim=quick&sm=3&currencyid=1005&searchtype=homepage&Ntk=boatsUK&luom=126&toLength=9999&N=4002&cit=true&fromLength=100&type=%28Power%29&No="
					+ i;

			Document doc = Jsoup.parse(ingestor.ingest(URL));

			Elements listItems = doc.getElementsByClass("make-model").select("a");

			for (Element item : listItems) {

				// System.out.println(item.attr("href"));

				String tempUrl = item.attr("href");
				urlSet.add(tempUrl);
			}

			// System.exit(1);

			for (String url : urlSet) {

				try {

					YchCreator ych = new YchCreator();

					System.out.println(ctr + "   YacWor" + url);
					ingestor.ingest(url);

					// ingestor.driver.findElements(By.className("firstColumn"));

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// System.out.println(specDoc_1.getElementsByClass("firstColumn").get(0));

					for (WebElement item : ingestor.driver.findElements(By.className("firstColumn"))) {
						//System.out.println(item.getText());
					}
					for (WebElement item : ingestor.driver.findElements(By.className("secondColumn"))) {
						//System.out.println(item.getText());
					}

					try {

						///////////////////////////////////// clicker code
						ingestor.driver
								.findElement(By.className("fullspecstab"))
								.click();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

					try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					for (WebElement item : ingestor.driver.findElements(By.className("fullspecs"))) {
						System.out.println(item.getText());
					}

					// fullspecs

					// firstColumn

//				File file = new File("jsonfiles/" + "YacChaFle" + ".json");
//				try {
//					FileUtils.writeStringToFile(file, ych.getYchObj().toString() + "\n", true);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//				try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

					// break; // break for limiting iterations during testing

				} catch (Exception e) {
					// TODO: handle exception
				}
			}

			// sleep function to avoid load
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ingestor.closeDriver(); // Driver closes here

	}
}
