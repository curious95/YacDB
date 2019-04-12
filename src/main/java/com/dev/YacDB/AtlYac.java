package com.dev.YacDB;

import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;

public class AtlYac {

	static HashSet<String> urlSet = new HashSet<String>();
	final static int limit = 6; // Static number during the design phase

	static String name, type, yac_model, sub_type, builder, naval_architect, exterior_designers, interior_designer,
			year, flag, mca, class_, hull_nb, hull_colour, length_overall, length_at_waterline, beam, draft_min,
			draft_max, gross_tonnage, guests, cabins_total, cabins, crew, hull_configuration, hull_material,
			superstructure, deck_material, decks_nb, quantity, fuel_type, manufacturer, eng_model, power, total_power,
			propulsion, max_speed, cruising_speed, range, fuel_capacity, water_capacity, generator, stabilizers,
			thrusters, amenities;
	//

	@SuppressWarnings("static-access")
	public static void startProcess() {

		Ingestor ingestor = new Ingestor();
		ingestor.initDriver(); // Driver instance begins here

		for (int i = 0; i < limit; i++) {
			urlSet.clear();
			String URL = "https://www.atlanticyachtandship.ru/en/catalog/?search=1&LOARange=100%2C&Measure=feet&includeType%5B0%5D=motor&includeType%5B1%5D=sail&PageIndex"
					+ i;

			Document doc = Jsoup.parse(ingestor.ingest(URL));

			Elements listItems = doc.getElementsByClass("visual").select("a");

			for (Element item : listItems) {

				String tempUrl = item.attr("href");

				urlSet.add(tempUrl);
				//System.out.println(tempUrl);

			}

			// System.exit(1);

			for (String url : urlSet) {

				YchCreator ych = new YchCreator();

				//System.out.println("Alt YAC  :  " + url);

				ingestor.ingest(url);
				Document specDocs = Jsoup.parse(ingestor.ingest(url));
				Elements specItems = specDocs.getElementsByClass("specifications-text").select("dl");
				Elements specItemsAdd = specDocs.getElementsByClass("info-columns").select("dl");
				for (Element item : specItems) {
					specItemsAdd.add(item);
				}
				
				for (Element item : specItemsAdd) {
					//System.out.println(item.text().substring(0, item.text().indexOf(":")).trim());
					String Key = item.text().substring(0, item.text().indexOf(":")).trim();
					String Val = item.text().replace("Key", "").replace(":", "").trim();
				
					System.out.println(Key+"   "+Val);
					
					
					
				}
				
				try {
					name = ingestor.driver.findElement(By.xpath("//*[@id=\"main\"]/div[3]/div/div[2]/div[1]/div[2]/div[1]/div[1]/div/strong/span")).getText();
					ych.setName(name);
				} catch (Exception e) {
					// TODO: handle exception
				}

			
				//System.out.println(ych.getYchObj());
			}
			
			
			
			System.exit(1);

		}
	}

}
