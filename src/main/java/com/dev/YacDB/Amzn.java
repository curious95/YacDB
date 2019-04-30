package com.dev.YacDB;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.opencsv.CSVWriter;

public class Amzn {

	// Scraping date Main Catagory Subcatagory Brand name Number of products Link

	static String date, main_category, sub_category, brand_name, no_products, link;

	public static void startProcess() throws IOException {

		String[] elems = { "%23", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
				"r", "s", "t", "u", "v", "w", "x", "y", "z" };

		String csvFile = "/Users/devx/Documents/devx/EC_Wspace/YacDB/res/input.txt";
		String line = "";
		String cvsSplitBy = ",";

		date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());

		// date = "26-04-2019";
		// System.out.println(date);

		File file = new File("jsonfiles/" + "Amazon" + ".csv");
		FileWriter outputfile = new FileWriter(file);
		String[] headerString = { "Scraping date", "Main Catagory", "Subcatagory", "Brand name", "Number of products",
				"Link" };
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

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] col = line.split(cvsSplitBy);

				try {
					if (col[0] != null && col[1] != null && col[2] != null && col[2].contains("http")) {
						// System.out.println("Category = " + col[0] + " Sub Category = " + col[1] + "
						// link = "+ col[2]);

						// System.out.println(col[2]);

						for (int i = 0; i < elems.length; i++) {

							try {
								Document doc = Jsoup.parse(ingestor.ingest(col[2] + "&indexField=" + elems[i]));
								Elements listItems = doc.getElementsByClass("a-list-item");

								for (Element item : listItems) {

									// System.out.println(item.text());
									main_category = col[0];
									sub_category = col[1];

									brand_name = item.getElementsByTag("a").attr("title");
									no_products = item.getElementsByClass("narrowValue").get(0).text().replace("(", "")
											.replace(")", "");
									link = "https://www.amazon.com" + item.getElementsByTag("a").attr("href");

									System.out.println(brand_name + "  " + no_products + "   " + link);

									String[] dataStr = { date, main_category, sub_category, brand_name, no_products,
											link };
									try {
										writer.writeNext(dataStr);
										// FileUtils.writeStringToFile(file, dataStr + "\n", true);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									writer.flush();

								}

								try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} catch (Exception e) {
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
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		writer.close();
		ingestor.closeDriver();

	}

}
