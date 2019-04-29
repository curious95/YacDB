package com.dev.YacDB;

import java.io.IOException;

public class YchDBInitializer {

	
	public static void main(String args[]) {
	
		SuperYac SY = new SuperYac();
		//SY.startProcess();
		
		YacTo YT = new YacTo();
		//YT.startProcess();
		
		YacChaFle YCF = new YacChaFle();
		//YCF.startProcess();
		
		ChaWor CW = new ChaWor();
		//CW.startProcess();
		
		AtlYac AY = new AtlYac();
		//AY.startProcess();
		
		YacWor YW = new YacWor();
		//YW.startProcess();
		
		Clutch cl = new Clutch();
//		try {
//			cl.startProcess();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	
		Amzn amzn = new Amzn();
		try {
			amzn.startProcess();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
}
