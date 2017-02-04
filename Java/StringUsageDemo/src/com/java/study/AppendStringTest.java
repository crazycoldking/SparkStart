package com.java.study;

import java.util.Date;

public class AppendStringTest {
	
	String[] strArr = new String[10000];
	
	public void StringAppendTest() {
		
		@SuppressWarnings("unused")
		String strResult = "";
		Date startTime = new Date();
		System.out.println("String += Start....");
		for (int i = 0; i < strArr.length; i++) {
			strResult += strArr[i];
		}
		Date stopTime = new Date();
	 	long span = stopTime.getTime()- startTime.getTime() ;
		//System.out.println(strResult);
	 	System.out.println("Completed....");
		System.out.println("Time cost: " + span + " ms");
	}
	
	public AppendStringTest() {
		for (int i = 0; i < strArr.length; i++) {
			strArr[i] = "test" + i + "\t";
		}
	}
	
	public void StringBufferAppendTest() {
		
		StringBuffer strResult = new StringBuffer();
		Date startTime = new Date();
		System.out.println("StringBuffer append Start....");
		for (int i = 0; i < strArr.length; i++) {
			strResult.append(strArr[i]);
		}
		Date stopTime = new Date();
	 	long span = stopTime.getTime()- startTime.getTime() ;
		//System.out.println(strResult);
	 	System.out.println("Completed....");
		System.out.println("Time cost: " + span + " ms");
	}
	
	public void StringBuilderAppendTest() {
		
		StringBuilder strResult = new StringBuilder();
		Date startTime = new Date();
		System.out.println("StringBuilder append Start....");
		for (int i = 0; i < strArr.length; i++) {
			strResult.append(strArr[i]);
		}
		Date stopTime = new Date();
	 	long span = stopTime.getTime()- startTime.getTime() ;
		//System.out.println(strResult);
	 	System.out.println("Completed....");
		System.out.println("Time cost: " + span + " ms");
	}
	
	/*		
	 * Test result:	
	  		String += Start....
			Completed....
			Time cost: 626 ms
			StringBuffer append Start....
			Completed....
			Time cost: 2 ms
			StringBuilder append Start....
			Completed....
			Time cost: 1 ms
	*/
	
}
