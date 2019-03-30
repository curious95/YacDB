package com.dev.YacDB;

public class SuperYac {

	
	public static void startProcess() {
	
		Ingestor ingestor = new Ingestor();
		ingestor.initDriver(); // Driver instance begins here
		
		
		
		
		
		ingestor.closeDriver(); // Driver instances closes here
		
		
	}
	
	
}
