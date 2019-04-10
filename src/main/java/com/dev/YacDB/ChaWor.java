package com.dev.YacDB;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ChaWor {
	static HashSet<String> urlSet = new HashSet<String>();
	final static int limit = 64; // Static number during the design phase

	static String name, type, yac_model, sub_type, builder, naval_architect, exterior_designers, interior_designer,
			year, flag, mca, class_, hull_nb, hull_colour, length_overall, length_at_waterline, beam, draft_min,
			draft_max, gross_tonnage, guests, cabins_total, cabins, crew, hull_configuration, hull_material,
			superstructure, deck_material, decks_nb, quantity, fuel_type, manufacturer, eng_model, power, total_power,
			propulsion, max_speed, cruising_speed, range, fuel_capacity, water_capacity, generator, stabilizers,
			thrusters, amenities;

	@SuppressWarnings("static-access")
	public static void startProcess() {

		Ingestor ingestor = new Ingestor();
		ingestor.initDriver(); // Driver instance begins here

		for (int i = 1; i < limit; i++) {
			urlSet.clear();
			String URL = "https://www.charterworld.com/index.html?sub=yacht-results&location_filter=&prices_filter=&yachttype_filter=6&sort=&page="
					+ i;

			Document doc = Jsoup.parse(ingestor.ingest(URL));

			Elements listItems = doc.getElementsByClass("caption").select("a");

			for (Element item : listItems) {

				String tempUrl = item.attr("href");

				if (tempUrl.contains("https://www.charterworld.com/index.html?sub=yacht-charter")) {
					// System.out.println(item.attr("href"));
					urlSet.add(tempUrl);
				}

			}

			for (String url : urlSet) {

				YchCreator ych = new YchCreator();

				System.out.println("Charter World  :  " + url);

				
				
				Document doc2 = Jsoup.parse(ingestor.ingest(url));
				// ingestor.ingest(url);

				ych.setName(doc2.getElementById("page-text").getElementsByTag("h1").get(0).getElementsByTag("span").get(0).text().trim());
				
				Elements listItems2 = doc2.getElementsByClass("specifications").select("tr");
				
				for (Element item : listItems2) {

					if (item.text().contains("Type")) {

						try {
							String[] splitStr = item.text().split(":");
							ych.setYear(splitStr[splitStr.length - 1].replaceAll("[A-z]", "").replace("/", "")
									.replace(" ", "").replace("&", "").trim());
							ych.setType(splitStr[splitStr.length - 1].replaceAll("\\d", "").replace("/", "").trim());
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}

					if (item.text().contains("Beam")) {

						try {
							String[] splitStr = item.text().split(":");
							ych.setBeam(splitStr[splitStr.length - 1].trim());
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}
					
					
					if (item.text().contains("L.O.A")) {

						try {
							String[] splitStr = item.text().split(":");
							ych.setLength_overall(splitStr[splitStr.length - 1].trim());
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}
					
					if (item.text().contains("Crew:")) {

						try {
							String[] splitStr = item.text().split(":");
							ych.setCrew(splitStr[splitStr.length - 1].trim());
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}
					
					if (item.text().contains("Cabins:")) {

						try {
							String[] splitStr = item.text().split(":");
							ych.setCabins(splitStr[splitStr.length - 1].trim());
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}
					
					if (item.text().contains("Guests:")) {

						try {
							String[] splitStr = item.text().split(":");
							ych.setGuests(splitStr[splitStr.length - 1].trim());
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}
					
					if (item.text().contains("Builder")) {

						try {
							String[] splitStr = item.text().split(":");
							ych.setBuilder(splitStr[splitStr.length - 1].trim());
							ych.setExterior_designers(splitStr[splitStr.length - 1].trim());
							ych.setInterior_designer(splitStr[splitStr.length - 1].trim());
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}
					
					
					if (item.text().contains("Max Speed")) {

						try {
							String[] splitStr = item.text().split(":");
							ych.setMax_speed(splitStr[splitStr.length - 1].trim());
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}
					
					if (item.text().contains("Cruise Speed")) {

						try {
							String[] splitStr = item.text().split(":");
							ych.setCruising_speed(splitStr[splitStr.length - 1].trim());
							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}
					
					if (item.text().contains("Engines")) {

						try {
							String[] splitStr = item.text().split(":");
							ych.setEng_model(splitStr[splitStr.length - 1].trim());							
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}

					// System.out.println("Item "+item.text());
				}

				System.out.println(ych.getYchObj().toString());

				//System.exit(1);

				File file = new File("jsonfiles/" + "CharWorld" + ".json");
				try {
					FileUtils.writeStringToFile(file, ych.getYchObj().toString() + "\n", true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// break; // break for limiting iterations during testing

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
