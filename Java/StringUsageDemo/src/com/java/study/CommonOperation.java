package com.java.study;

public class CommonOperation {
	
	public void GetSubtring() {
		String s = "this is a test string";
		String sub1 = s.substring(2); 
		System.out.println(sub1);	
		
		sub1 = s.substring(2,4);
		System.out.println(sub1);
	}
	
	public void MergeStringTest() {
		String s1 = "Hello, ";
		String s2 = "William.";
		String s3 = s1 + s2;
		System.out.println(s3);
		
		String s4 = String.join("",s1, s2);
		System.out.println(s4);
	}
}
