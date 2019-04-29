package com.dev.YacDB;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Amzn {

	//Scraping date	Main Catagory	Subcatagory	Brand name	Number of products	Link
	
	
	public static void startProcess() {

		String csvFile = "/Users/devx/Documents/devx/EC_Wspace/YacDB/res/input.txt";
		String line = "";
		String cvsSplitBy = ",";

		Ingestor ingestor = new Ingestor();
		ingestor.initDriver(); // Driver instance begins here
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] col = line.split(cvsSplitBy);

				try {
					if (col[0] != null && col[1] != null && col[2] != null && col[2].contains("http")) {
						System.out.println("Category = " + col[0] + " Sub Category = " + col[1] + " link = "+ col[2]);
					
						Document doc = Jsoup.parse(ingestor.ingest(col[2]));
						Elements listItems = doc.getElementsByClass("a-list-item");
						
						for (Element item : listItems) {
							
							System.out.println(item.text());
							
						}
						
					
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
