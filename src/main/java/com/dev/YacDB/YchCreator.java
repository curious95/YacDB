package com.dev.YacDB;

import com.google.gson.JsonObject;

/**
 * YCH Setter and Getter
 *
 */
public class YchCreator 
{
   
    static String yachtName;
    static String length;
    static String beam;
    static String draft;
    
   
	
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
