package com.dev.YacDB;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;

public class AtlYac {

	static HashSet<String> urlSet = new HashSet<String>();
	final static int limit = 10; // Static number during the design phase

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
			String URL = "https://www.atlanticyachtandship.ru/en/catalog/?search=1&LOARange=80%2C&Measure=feet&includeType%5B0%5D=motor&includeType%5B1%5D=sail&PageIndex"
					+ i;

			Document doc = Jsoup.parse(ingestor.ingest(URL));

			Elements listItems = doc.getElementsByClass("visual").select("a");

			for (Element item : listItems) {

				String tempUrl = item.attr("href");

				urlSet.add(tempUrl);
				// System.out.println(tempUrl);

			}

			// System.exit(1);

			for (String url : urlSet) {

				YchCreator ych = new YchCreator();

				System.out.println("Alt YAC  :  " + url);

				ingestor.ingest(url);
				Document specDocs = Jsoup.parse(ingestor.ingest(url));
				Elements specItems = specDocs.getElementsByClass("specifications-text").select("dl");
				Elements specItemsAdd = specDocs.getElementsByClass("info-columns").select("dl");
				for (Element item : specItems) {
					specItemsAdd.add(item);
				}

				for (Element item : specItemsAdd) {
					// System.out.println(item.text().substring(0,
					// item.text().indexOf(":")).trim());
					String key = item.text().substring(0, item.text().indexOf(":")).trim();
					String val = item.text().replace(key, "").replace(":", "").trim();
					key = key.toLowerCase().replace(" ", "_");

					// System.out.println(key+" "+val);

					switch (key) {

					case "category":
						ych.setType(val);
						break;
					case "yac_model":
						ych.setYac_model(val);
						break;
					case "sub_category":
						ych.setSub_type(val);
						break;
					case "builder":
						ych.setBuilder(val);
						break;
					case "hull_designer":
						ych.setNaval_architect(val);
						break;
					case "exterior_designer":
						ych.setExterior_designers(val);
						break;
					case "interior_designer":
						ych.setInterior_designer(val);
						break;
					case "year_built":
						ych.setYear(val);
						break;
					case "country":
						ych.setFlag(val);
						break;
					case "mca":
						ych.setMca(val);
						break;
					case "class_":
						ych.setClass_(val);
						break;
					case "hull_nb":
						ych.setHull_nb(val);
						break;
					case "hull_color":
						ych.setHull_colour(val);
						break;
					case "loa":
						ych.setLength_overall(val);
						break;
					case "lwl":
						ych.setLength_at_waterline(val);
						break;
					case "beam":
						ych.setBeam(val);
						break;
					case "min_draft":
						ych.setDraft_min(val);
						break;
					case "max_draft":
						ych.setDraft_max(val);
						break;
					case "draft":
						ych.setDraft_max(val);
						break;
					case "gross_tonnage":
						ych.setGross_tonnage(val);
						break;
					case "sleeps":
						ych.setGuests(val);
						break;
					case "cabins_total":
						ych.setCabins_total(val);
						break;
					case "cabins":
						ych.setCabins(val);
						break;
					case "crew_sleeps":
						ych.setCrew(val);
						break;
					case "hull_configuration":
						ych.setHull_configuration(val);
						break;
					case "hull_finish":
						ych.setHull_material(val);
						break;
					case "superstructure":
						ych.setSuperstructure(val);
						break;
					case "deck_material":
						ych.setDeck_material(val);
						break;
					case "decks_nb":
						ych.setDecks_nb(val);
						break;
					case "engines":
						ych.setQuantity(val);
						break;
					case "fuel_type":
						ych.setFuel_type(val);
						break;
					case "manufacturer":
						ych.setManufacturer(val);
						break;
					case "model":
						ych.setEng_model(val);
						break;
					case "power":
						ych.setPower(val);
						break;
					case "total_power":
						ych.setTotal_power(val);
						break;
					case "propulsion":
						ych.setPropulsion(val);
						break;
					case "max_speed":
						ych.setMax_speed(val);
						break;
					case "cruise_speed":
						ych.setCruising_speed(val);
						break;
					case "range":
						ych.setRange(val);
						break;
					case "fuel_capacity":
						ych.setFuel_capacity(val);
						break;
					case "water_capacity":
						ych.setWater_capacity(val);
						break;
					case "generator":
						ych.setGenerator(val);
						break;
					case "stabilizers":
						ych.setStabilizers(val);
						break;
					case "thrusters":
						ych.setThrusters(val);
						break;
					case "amenities":
						ych.setAmenities(val);
						break;
					}

				}

				try {
					name = ingestor.driver
							.findElement(By.xpath(
									"//*[@id=\"main\"]/div[3]/div/div[2]/div[1]/div[2]/div[1]/div[1]/div/strong/span"))
							.getText();
					ych.setName(name);
				} catch (Exception e) {
					// TODO: handle exception
				}

				File file = new File("jsonfiles/" + "AlyYac" + ".json");
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
				
			}

			// sleep function to avoid load
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// System.out.println(ych.getYchObj());
		}

	}

}
