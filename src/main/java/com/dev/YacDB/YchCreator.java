package com.dev.YacDB;

import com.google.gson.JsonObject;

/**
 * YCH Setter and Getter
 *
 */
public class YchCreator {

	static String yachtName;
	static String length;
	static String beam;
	static String draft;

//	static String Name,Type,Model,Sub Type,Builder,Naval Architect,Exterior Designers,Interior Designer,Year,Flag,MCA,Class,Hull NB,
//	Hull Colour, Length Overall,Length at Waterline,Beam,Draft-min,Draft-max,Gross Tonnage,Guests,Cabins Total,Cabins,Crew,Hull Configuration,Hull Material,Superstructure,Deck Material,Decks NB,Quantity,Fuel Type,Manufacturer,Model,Power,Total Power,Propulsion,Max Speed,Cruising Speed,Range,Fuel Capacity,Water Capacity,Generator,Stabilizers,Thrusters,Amenities;

	public static void setLength(String length) {
		YchCreator.length = length;
	}

	public static void setBeam(String beam) {
		YchCreator.beam = beam;
	}

	public static void setDraft(String draft) {
		YchCreator.draft = draft;
	}

	public static void setYachtName(String yachtName) {
		YchCreator.yachtName = yachtName;
	}

	public static JsonObject getYchObj() {

		return null;
	}
}
