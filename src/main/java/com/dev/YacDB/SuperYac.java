package com.dev.YacDB;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SuperYac {

	final static int range = 251; // Static number during the design phase
	static HashSet<String> urlSet = new HashSet<String>();
	static Map<String, String> objMap = new HashMap<String, String>();

	@SuppressWarnings("static-access")
	public static void startProcess() {

		Ingestor ingestor = new Ingestor();
		ingestor.initDriver(); // Driver instance begins here

		// Looping through pages
		// https://www.superyachts.com/directory/completed_yachts.htm?page=2

		for (int i = 1; i <= range; i++) {

			urlSet.clear();
			String URL = "https://www.superyachts.com/directory/completed_yachts.htm?page=" + i;
			Document doc = Jsoup.parse(ingestor.ingest(URL));

			Elements listItems = doc.getElementById("completeBoats").select("a");

			for (Element item : listItems) {
				urlSet.add(item.attr("href"));
			}

			for (String url : urlSet) {

				System.out.println("SuperYachts  :  " + url);
				// Clearing the old object map
				objMap.clear();

				// Yacht creator class instance
				YchCreator ych = new YchCreator();

				// System.out.println(url.replace(".htm", "-specification.htm"));
				ychParser(Jsoup.parse(
						ingestor.ingest("https://www.superyachts.com" + url.replace(".htm", "-specification.htm"))));

				// System.out.println("\n\n\n\n\n ");

				String fileName = "";
				for (Map.Entry<String, String> entry : objMap.entrySet()) {
					// System.out.println(entry.getKey() + "/" + entry.getValue());

					// Switch case to initialize setters
					switch (entry.getKey()) {

					case "name":
						// System.out.println(entry.getValue());
						fileName = entry.getValue();
						ych.setName(entry.getValue());
						break;
					case "type":
						ych.setType(entry.getValue());
						break;
					case "yac_model":
						ych.setYac_model(entry.getValue());
						break;
					case "sub_type":
						ych.setSub_type(entry.getValue());
						break;
					case "builder":
						ych.setBuilder(entry.getValue());
						break;
					case "naval_architect":
						ych.setNaval_architect(entry.getValue());
						break;
					case "exterior_designers":
						ych.setExterior_designers(entry.getValue());
						break;
					case "interior_designer":
						ych.setInterior_designer(entry.getValue());
						break;
					case "year":
						ych.setYear(entry.getValue());
						break;
					case "flag":
						ych.setFlag(entry.getValue());
						break;
					case "mca":
						ych.setMca(entry.getValue());
						break;
					case "class_":
						ych.setClass_(entry.getValue());
						break;
					case "hull_nb":
						ych.setHull_nb(entry.getValue());
						break;
					case "hull_colour":
						ych.setHull_colour(entry.getValue());
						break;
					case "length_overall":
						ych.setLength_overall(entry.getValue());
						break;
					case "length_at_waterline":
						ych.setLength_at_waterline(entry.getValue());
						break;
					case "beam":
						ych.setBeam(entry.getValue());
						break;
					case "draft_min":
						ych.setDraft_min(entry.getValue());
						break;
					case "draft_max":
						ych.setDraft_max(entry.getValue());
						break;
					case "gross_tonnage":
						ych.setGross_tonnage(entry.getValue());
						break;
					case "guests":
						ych.setGuests(entry.getValue());
						break;
					case "cabins_total":
						ych.setCabins_total(entry.getValue());
						break;
					case "cabins":
						ych.setCabins(entry.getValue());
						break;
					case "crew":
						ych.setCrew(entry.getValue());
						break;
					case "hull_configuration":
						ych.setHull_configuration(entry.getValue());
						break;
					case "hull_material":
						ych.setHull_material(entry.getValue());
						break;
					case "superstructure":
						ych.setSuperstructure(entry.getValue());
						break;
					case "deck_material":
						ych.setDeck_material(entry.getValue());
						break;
					case "decks_nb":
						ych.setDecks_nb(entry.getValue());
						break;
					case "quantity":
						ych.setQuantity(entry.getValue());
						break;
					case "fuel_type":
						ych.setFuel_type(entry.getValue());
						break;
					case "manufacturer":
						ych.setManufacturer(entry.getValue());
						break;
					case "eng_model":
						ych.setEng_model(entry.getValue());
						break;
					case "power":
						ych.setPower(entry.getValue());
						break;
					case "total_power":
						ych.setTotal_power(entry.getValue());
						break;
					case "propulsion":
						ych.setPropulsion(entry.getValue());
						break;
					case "max_speed":
						ych.setMax_speed(entry.getValue());
						break;
					case "cruising_speed":
						ych.setCruising_speed(entry.getValue());
						break;
					case "range":
						ych.setRange(entry.getValue());
						break;
					case "fuel_capacity":
						ych.setFuel_capacity(entry.getValue());
						break;
					case "water_capacity":
						ych.setWater_capacity(entry.getValue());
						break;
					case "generator":
						ych.setGenerator(entry.getValue());
						break;
					case "stabilizers":
						ych.setStabilizers(entry.getValue());
						break;
					case "thrusters":
						ych.setThrusters(entry.getValue());
						break;
					case "amenities":
						ych.setAmenities(entry.getValue());
						break;
					}

				}

				// System.out.println(ych.getYchObj().toString());

				File file = new File("jsonfiles/" + "supYac" + ".json");
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
				
				//break; // break for limiting iterations during testing
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		ingestor.closeDriver(); // Driver instances closes here

	}

	public static void ychParser(Document doc) {

		boolean keyVal = false;
		String key = "", val = "";
		// System.out.println("In YCH");
		Elements listItems = doc.getElementsByClass("specifications").select("td");

		int ctr = 0;

		for (Element item : listItems) {

			// System.out.println(item.text());

			if (keyVal) {
				val = item.text();
				if (val.equals("-")) {
					val = "";
				}
				// System.out.println("Key = " + key + " Val = " + val);
				objMap.put(key, val);
				key = "";
				val = "";
				keyVal = false;

			}

			if (item.text().contains(":")) {
				keyVal = true;
				key = item.text().replace(" (", "_").replace(")", "").replace(":", "");
				key = key.replace(" ", "_").toLowerCase();
				if (key.equals("model") && ctr == 0) {
					key = "yac_model";
					ctr++;
				}

				if (key.equals("model") && ctr != 0) {
					key = "eng_model";
				}

				if (key.equals("class")) {
					key = "class_";
				}
			}

		}

	}

}
