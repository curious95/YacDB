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

public class YacTo {

	static HashSet<String> urlSet = new HashSet<String>();
	final static int limit = 23; // Static number during the design phase
	
	static String name, type, yac_model, sub_type, builder, naval_architect, exterior_designers, interior_designer,
	year, flag, mca, class_, hull_nb, hull_colour, length_overall, length_at_waterline, beam, draft_min,
	draft_max, gross_tonnage, guests, cabins_total, cabins, crew, hull_configuration, hull_material,
	superstructure, deck_material, decks_nb, quantity, fuel_type, manufacturer, eng_model, power, total_power,
	propulsion, max_speed, cruising_speed, range, fuel_capacity, water_capacity, generator, stabilizers,
	thrusters, amenities;

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

				
				YchCreator ych = new YchCreator();
				
				//System.out.println("https://www.yatco.com" + url);
				ingestor.ingest("https://www.yatco.com" + url);
				String year = ingestor.driver.findElement(By.xpath("//html/body/div[4]/div[2]/div/div[3]/div/div/div/div[1]/div[1]/ul/li[5]/table/tbody/tr/td[2]")).getText();
				//System.out.println(year);
				
				name = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/h1")).getText();
				ych.setName(name);
				
				type = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[1]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
				ych.setType(type);
				
				
				System.out.println(ych.getYchObj().toString());
				
			
			
			
				// System.out.println(ych.getYchObj().toString());

//				File file = new File("jsonfiles/" + "YacTo" + ".json");
//				try {
//					FileUtils.writeStringToFile(file, ych.getYchObj().toString() + "\n", true);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			
//				try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				//break; // break for limiting iterations during testing
			
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
