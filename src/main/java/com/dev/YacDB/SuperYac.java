package com.dev.YacDB;

import java.util.HashSet;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SuperYac {

	final static int range = 251; // Static number during the design phase
	static HashSet<String> urlSet = new HashSet<String>();

	public static void startProcess() {

		Ingestor ingestor = new Ingestor();
		ingestor.initDriver(); // Driver instance begins here

		// Looping through pages
		// https://www.superyachts.com/directory/completed_yachts.htm?page=2

		for (int i = 2; i <= 2; i++) {

			urlSet.clear();
			String URL = "https://www.superyachts.com/directory/completed_yachts.htm?page=" + range;
			Document doc = Jsoup.parse(ingestor.ingest(URL));

			Elements listItems = doc.getElementById("completeBoats").select("a");

			for (Element item : listItems) {
				urlSet.add(item.attr("href"));
			}

			for (String url : urlSet) {
				// System.out.println(url.replace(".htm", "-specification.htm"));
				ychParser(Jsoup.parse(
						ingestor.ingest("https://www.superyachts.com" + url.replace(".htm", "-specification.htm"))));
				System.out.println("\n\n\n\n\n ");
				break;
			}

			// System.out.println(doc.text());

		}

		ingestor.closeDriver(); // Driver instances closes here

	}

	public static void ychParser(Document doc) {

		boolean keyVal = false;
		String key = "", val = "";
		// System.out.println("In YCH");
		Elements listItems = doc.getElementsByClass("specifications").select("td");

		for (Element item : listItems) {

			// System.out.println(item.text());

			if (keyVal) {
				val = item.text();
				System.out.println("Key = " + key + "  Val = " + val);
				key = "";
				val = "";
				keyVal = false;

			}

			if (item.text().contains(":")) {
				keyVal = true;
				key = item.text().replace(" (", "_").replace(")", "");
				key = key.replace(" ", "_").toLowerCase();
			}

		}

	}

}
