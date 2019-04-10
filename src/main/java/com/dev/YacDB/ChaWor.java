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
			String URL = "https://www.charterworld.com/index.html?sub=yacht-results&location_filter=&prices_filter=&yachttype_filter=6&sort=&page="+limit;

			Document doc = Jsoup.parse(ingestor.ingest(URL));

			Elements listItems = doc.getElementsByClass("searchImageLink").select("a");

			for (Element item : listItems) {

				// System.out.println(item.attr("href"));

				String tempUrl = item.attr("href");

				if (tempUrl.contains("luxury")) {
					urlSet.add(tempUrl);
				}

			}
			

			for (String url : urlSet) {

				YchCreator ych = new YchCreator();

				System.out.println("YacCharFlee" + url);
				ingestor.ingest("https://www.yachtcharterfleet.com" + url);

				try {
					name = ingestor.driver
							.findElement(By.xpath("/html/body/div[5]/div/div/div/div[3]/div/div/div[2]/div[1]/p[1]/em"))
							.getText();
					ych.setName(name);
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					year = ingestor.driver.findElement(By.xpath(
							"/html/body/div[5]/div/div/div/div[3]/div/div/div[5]/div[2]/div[1]/div[1]/div[1]/div[2]/table/tbody/tr[8]/td[2]"))
							.getText();
					ych.setYear(year);
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					length_overall = ingestor.driver.findElement(By.xpath(
							"/html/body/div[5]/div/div/div/div[3]/div/div/div[5]/div[2]/div[1]/div[1]/div[1]/div[2]/table/tbody/tr[1]/td[2]"))
							.getText();
					ych.setLength_overall(length_overall);
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					beam = ingestor.driver.findElement(By.xpath(
							"/html/body/div[5]/div/div/div/div[3]/div/div/div[5]/div[2]/div[1]/div[1]/div[1]/div[2]/table/tbody/tr[2]/td[2]"))
							.getText();
					ych.setBeam(beam);
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					draft_max = ingestor.driver.findElement(By.xpath(
							"/html/body/div[5]/div/div/div/div[3]/div/div/div[5]/div[2]/div[1]/div[1]/div[1]/div[2]/table/tbody/tr[3]/td[2]"))
							.getText();
					ych.setDraft_max(draft_max);
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					gross_tonnage = ingestor.driver.findElement(By.xpath(
							"/html/body/div[5]/div/div/div/div[3]/div/div/div[5]/div[2]/div[1]/div[1]/div[1]/div[2]/table/tbody/tr[4]/td[2]"))
							.getText();
					ych.setGross_tonnage(gross_tonnage);
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					cruising_speed = ingestor.driver.findElement(By.xpath(
							"/html/body/div[5]/div/div/div/div[3]/div/div/div[5]/div[2]/div[1]/div[1]/div[1]/div[2]/table/tbody/tr[5]/td[2]"))
							.getText();
					ych.setCruising_speed(cruising_speed);
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					builder = ingestor.driver.findElement(By.xpath(
							"/html/body/div[5]/div/div/div/div[3]/div/div/div[5]/div[2]/div[1]/div[1]/div[1]/div[2]/table/tbody/tr[6]/td[2]"))
							.getText();
					ych.setBuilder(builder);
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					yac_model = ingestor.driver.findElement(By.xpath(
							"/html/body/div[5]/div/div/div/div[3]/div/div/div[5]/div[2]/div[1]/div[1]/div[1]/div[2]/table/tbody/tr[7]/td[2]"))
							.getText();
					ych.setYac_model(yac_model);
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					exterior_designers = ingestor.driver.findElement(By.xpath(
							"/html/body/div[5]/div/div/div/div[3]/div/div/div[5]/div[2]/div[1]/div[1]/div[1]/div[2]/table/tbody/tr[9]/td[2]"))
							.getText();
					ych.setExterior_designers(exterior_designers);
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					interior_designer = ingestor.driver.findElement(By.xpath(
							"/html/body/div[5]/div/div/div/div[3]/div/div/div[5]/div[2]/div[1]/div[1]/div[1]/div[2]/table/tbody/tr[10]/td[2]"))
							.getText();
					ych.setInterior_designer(interior_designer);
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					guests = ingestor.driver.findElement(By.xpath(
							"/html/body/div[5]/div/div/div/div[3]/div/div/div[5]/div[2]/div[1]/div[1]/div[1]/div[1]/ul/li[1]/p[2]"))
							.getText();
					ych.setGuests(guests);
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					cabins_total = ingestor.driver.findElement(By.xpath(
							"/html/body/div[5]/div/div/div/div[3]/div/div/div[5]/div[2]/div[1]/div[1]/div[1]/div[1]/ul/li[2]/p[2]"))
							.getText();
					ych.setCabins_total(cabins_total);
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					crew = ingestor.driver.findElement(By.xpath(
							"/html/body/div[5]/div/div/div/div[3]/div/div/div[5]/div[2]/div[1]/div[1]/div[1]/div[1]/ul/li[3]/p[2]"))
							.getText();
					ych.setCrew(crew);
				} catch (Exception e) {
					// TODO: handle exception
				}

				try {
					List<WebElement> elements = ingestor.driver.findElements(By.className("amenitiesList"));

					amenities = "";
					for (WebElement ele : elements) {
						amenities += ele.getText().replace("\n", " ");
					}
					ych.setAmenities(amenities);
				} catch (Exception e) {
					// TODO: handle exception
				}

				// System.out.println(ych.getYchObj().toString());

				File file = new File("jsonfiles/" + "YacChaFle" + ".json");
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
