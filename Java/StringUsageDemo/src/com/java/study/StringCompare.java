package com.java.study;

public class StringCompare {
	String str1 = "abcd";
	String str2 = "ABCD";
	
	public void TestStringCompare() {
		String result = str1.equals(str2)? "Equal" : "Not equal";
		System.out.println(result);
		
		result = str1.compareTo(str2) > 0 ? "Equal" : "Not equal";
		System.out.println(result);
	}
}
