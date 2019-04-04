package com.dev.YacDB;

import com.google.gson.JsonObject;

/**
 * YCH Setter and Getter
 *
 */
public class YchCreator {

	

	static String name, type, yac_model, sub_type, builder, naval_architect, exterior_designers, interior_designer,
			year, flag, mca, class_, hull_nb, hull_colour, length_overall, length_at_waterline, beam, draft_min,
			draft_max, gross_tonnage, guests, cabins_total, cabins, crew, hull_configuration, hull_material,
			superstructure, deck_material, decks_nb, quantity, fuel_type, manufacturer, eng_model, power, total_power,
			propulsion, max_speed, cruising_speed, range, fuel_capacity, water_capacity, generator, stabilizers,
			thrusters, amenities;	
	
	

	public static void setName(String name) {
		YchCreator.name = name;
	}




	public static void setType(String type) {
		YchCreator.type = type;
	}




	public static void setYac_model(String yac_model) {
		YchCreator.yac_model = yac_model;
	}




	public static void setSub_type(String sub_type) {
		YchCreator.sub_type = sub_type;
	}




	public static void setBuilder(String builder) {
		YchCreator.builder = builder;
	}




	public static void setNaval_architect(String naval_architect) {
		YchCreator.naval_architect = naval_architect;
	}




	public static void setExterior_designers(String exterior_designers) {
		YchCreator.exterior_designers = exterior_designers;
	}




	public static void setInterior_designer(String interior_designer) {
		YchCreator.interior_designer = interior_designer;
	}




	public static void setYear(String year) {
		YchCreator.year = year;
	}




	public static void setFlag(String flag) {
		YchCreator.flag = flag;
	}




	public static void setMca(String mca) {
		YchCreator.mca = mca;
	}




	public static void setClass_(String class_) {
		YchCreator.class_ = class_;
	}




	public static void setHull_nb(String hull_nb) {
		YchCreator.hull_nb = hull_nb;
	}




	public static void setHull_colour(String hull_colour) {
		YchCreator.hull_colour = hull_colour;
	}




	public static void setLength_overall(String length_overall) {
		YchCreator.length_overall = length_overall;
	}




	public static void setLength_at_waterline(String length_at_waterline) {
		YchCreator.length_at_waterline = length_at_waterline;
	}




	public static void setBeam(String beam) {
		YchCreator.beam = beam;
	}




	public static void setDraft_min(String draft_min) {
		YchCreator.draft_min = draft_min;
	}




	public static void setDraft_max(String draft_max) {
		YchCreator.draft_max = draft_max;
	}




	public static void setGross_tonnage(String gross_tonnage) {
		YchCreator.gross_tonnage = gross_tonnage;
	}




	public static void setGuests(String guests) {
		YchCreator.guests = guests;
	}




	public static void setCabins_total(String cabins_total) {
		YchCreator.cabins_total = cabins_total;
	}




	public static void setCabins(String cabins) {
		YchCreator.cabins = cabins;
	}




	public static void setCrew(String crew) {
		YchCreator.crew = crew;
	}




	public static void setHull_configuration(String hull_configuration) {
		YchCreator.hull_configuration = hull_configuration;
	}




	public static void setHull_material(String hull_material) {
		YchCreator.hull_material = hull_material;
	}




	public static void setSuperstructure(String superstructure) {
		YchCreator.superstructure = superstructure;
	}




	public static void setDeck_material(String deck_material) {
		YchCreator.deck_material = deck_material;
	}




	public static void setDecks_nb(String decks_nb) {
		YchCreator.decks_nb = decks_nb;
	}




	public static void setQuantity(String quantity) {
		YchCreator.quantity = quantity;
	}




	public static void setFuel_type(String fuel_type) {
		YchCreator.fuel_type = fuel_type;
	}




	public static void setManufacturer(String manufacturer) {
		YchCreator.manufacturer = manufacturer;
	}




	public static void setEng_model(String eng_model) {
		YchCreator.eng_model = eng_model;
	}




	public static void setPower(String power) {
		YchCreator.power = power;
	}




	public static void setTotal_power(String total_power) {
		YchCreator.total_power = total_power;
	}




	public static void setPropulsion(String propulsion) {
		YchCreator.propulsion = propulsion;
	}




	public static void setMax_speed(String max_speed) {
		YchCreator.max_speed = max_speed;
	}




	public static void setCruising_speed(String cruising_speed) {
		YchCreator.cruising_speed = cruising_speed;
	}




	public static void setRange(String range) {
		YchCreator.range = range;
	}




	public static void setFuel_capacity(String fuel_capacity) {
		YchCreator.fuel_capacity = fuel_capacity;
	}




	public static void setWater_capacity(String water_capacity) {
		YchCreator.water_capacity = water_capacity;
	}




	public static void setGenerator(String generator) {
		YchCreator.generator = generator;
	}




	public static void setStabilizers(String stabilizers) {
		YchCreator.stabilizers = stabilizers;
	}




	public static void setThrusters(String thrusters) {
		YchCreator.thrusters = thrusters;
	}




	public static void setAmenities(String amenities) {
		YchCreator.amenities = amenities;
	}




	public static JsonObject getYchObj() {

		JsonObject jo = new JsonObject();
		jo.addProperty("name",YchCreator.name );
		jo.addProperty("type",YchCreator.type);
		jo.addProperty("yac_model",YchCreator.yac_model );
		jo.addProperty("sub_type",YchCreator.sub_type );
		jo.addProperty("builder",YchCreator.builder );
		jo.addProperty("naval_architect",YchCreator.naval_architect );
		jo.addProperty("exterior_designers",YchCreator.exterior_designers );
		jo.addProperty("interior_designer",YchCreator.interior_designer );
		jo.addProperty("year",YchCreator.year );
		jo.addProperty("flag",YchCreator.flag );
		jo.addProperty("mca",YchCreator.mca );
		jo.addProperty("class_",YchCreator.class_ );
		jo.addProperty("hull_nb",YchCreator.hull_nb );
		jo.addProperty("hull_colour",YchCreator.hull_colour );
		jo.addProperty("length_overall",YchCreator.length_overall );
		jo.addProperty("length_at_waterline",YchCreator.length_at_waterline );
		jo.addProperty("beam",YchCreator.beam );
		jo.addProperty("draft_min",YchCreator.draft_min );
		jo.addProperty("draft_max",YchCreator.draft_max );
		jo.addProperty("gross_tonnage",YchCreator.gross_tonnage );
		jo.addProperty("guests",YchCreator.guests );
		jo.addProperty("cabins_total",YchCreator.cabins_total );
		jo.addProperty("cabins",YchCreator.cabins );
		jo.addProperty("crew",YchCreator.crew );
		jo.addProperty("hull_configuration",YchCreator.hull_configuration );
		jo.addProperty("hull_material",YchCreator.hull_material );
		jo.addProperty("superstructure",YchCreator.superstructure );
		jo.addProperty("deck_material",YchCreator.deck_material );
		jo.addProperty("decks_nb",YchCreator.decks_nb );
		jo.addProperty("quantity",YchCreator.quantity );
		jo.addProperty("fuel_type",YchCreator.fuel_type );
		jo.addProperty("manufacturer",YchCreator.manufacturer );
		jo.addProperty("eng_model",YchCreator.eng_model );
		jo.addProperty("power",YchCreator.power );
		jo.addProperty("total_power",YchCreator.total_power );
		jo.addProperty("propulsion",YchCreator.propulsion );
		jo.addProperty("max_speed",YchCreator.max_speed );
		jo.addProperty("cruising_speed",YchCreator.cruising_speed );
		jo.addProperty("range",YchCreator.range );
		jo.addProperty("fuel_capacity",YchCreator.fuel_capacity );
		jo.addProperty("water_capacity",YchCreator.water_capacity );
		jo.addProperty("generator",YchCreator.generator );
		jo.addProperty("stabilizers",YchCreator.stabilizers );
		jo.addProperty("thrusters",YchCreator.thrusters );
		jo.addProperty("amenities",YchCreator.amenities );
		
		return jo;
	}
}
