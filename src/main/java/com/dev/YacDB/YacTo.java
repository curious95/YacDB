package com.dev.YacDB;

import java.util.HashSet;

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

	@SuppressWarnings("static-access")
	public static void startProcess() {

		Ingestor ingestor = new Ingestor();
		ingestor.initDriver(); // Driver instance begins here

		for (int i = 0; i < 1; i++) {
			
			urlSet.clear();
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
				
				System.out.println("Yatc0" + url);
				ingestor.ingest("https://www.yatco.com" + url);
				
				try {
					name = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[1]/div/h1")).getText();
					ych.setName(name);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				try {
					type = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[1]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setType(type);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				try {
					yac_model = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[1]/div[1]/ul/li[3]/table/tbody/tr/td[2]")).getText();
					ych.setYac_model(yac_model);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					year = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[1]/div[1]/ul/li[5]/table/tbody/tr/td[2]")).getText();
					ych.setYear(year);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				try {
					flag = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[1]/div[2]/ul/li[1]/table/tbody/tr/td[2]")).getText();
					ych.setFlag(flag);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				try {
					beam = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[4]/table/tbody/tr/td[2]")).getText();
					ych.setBeam(beam);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				try {
					draft_max = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[2]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setDraft_max(draft_max);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					draft_min = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[2]/ul/li[1]/table/tbody/tr/td[2]")).getText();
					ych.setDraft_min(draft_min);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					length_overall = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[1]/table/tbody/tr/td[2]")).getText();
					ych.setLength_overall(length_overall);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					length_at_waterline = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setLength_at_waterline(length_at_waterline);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					cruising_speed = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setCruising_speed(cruising_speed);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					max_speed = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setMax_speed(max_speed);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					range = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setRange(range);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					fuel_capacity = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setFuel_capacity(fuel_capacity);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					fuel_capacity = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setFuel_capacity(fuel_capacity);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					gross_tonnage = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setGross_tonnage(gross_tonnage);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					hull_colour = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setHull_colour(hull_colour);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				try {
					hull_material = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setHull_material(hull_material);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					hull_configuration = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setHull_configuration(hull_configuration);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					hull_nb = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setHull_nb(hull_nb);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				try {
					interior_designer = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setInterior_designer(interior_designer);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					exterior_designers = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setExterior_designers(exterior_designers);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					eng_model = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setEng_model(eng_model);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					fuel_type = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setFuel_type(fuel_type);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				try {
					quantity = ingestor.driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[3]/div/div/div/div[2]/div[1]/ul/li[2]/table/tbody/tr/td[2]")).getText();
					ych.setQuantity(quantity);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
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
