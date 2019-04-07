package com.dev.YacDB;

import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;

public class YacChaFle {

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

		for (int i = 1; i < 3; i++) {
			String URL = "https://www.yachtcharterfleet.com/yacht-charter.htm?price_from=0&price_to=100000000&currency_code_id=3&length_from=100&length_to=328&length_id=2&guests_from=0&guests_to=40&vessel_name=&vessel_name_id=&corporate_charter=&vessel_type_id=&vessel_builder_id_list=&vessel_model_id=&season_id_list=&vessel_builder_id=&extended_name=&search=&view=&length_to_max=100&price_to_max=1000000&year_to_max=2019&display_resource_type=&per_page=12&order_by=&licence_id_list=&no_licence=&mode=&id=&page="+i;

			Document doc = Jsoup.parse(ingestor.ingest(URL));

			Elements listItems = doc.getElementsByClass("searchImageLink").select("a");

			for (Element item : listItems) {

				//System.out.println(item.attr("href"));
				urlSet.add(item.attr("href"));
			}

			for (String url : urlSet) {

				YchCreator ych = new YchCreator();

				// System.out.println("https://www.yatco.com" + url);
				ingestor.ingest("https://www.yachtcharterfleet.com" + url);
				
				name = ingestor.driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div[3]/div/div/div[2]/div[1]/p[1]/em")).getText();
				ych.setName(name);

				type = ingestor.driver.findElement(By.xpath(
						"/html/body/div[5]/div/div/div/div[3]/div/div/div[5]/div[2]/div[1]/div[1]/div[1]/div[2]/table/tbody/tr[8]/td[2]"))
						.getText();
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
