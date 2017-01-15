package com.java.study;

public class StringEqualsTest {
	public void TestMethodOne() {
		String s1 = "abc";
		String s2 = s1;
		String s5 = "abc";
		String s3 = new String("abc");
		String s4 = new String("abc");
		
		System.out.println("==comparsion: " + (s1 == s5));	//true ???
		
		System.out.println("==comparsion: " + (s1 == s2));	//true
		System.out.println("==comparsion: " + (s3 == s4));	//false
		System.out.println("equals comparsion£º" + s1.equals(s5));	//true
		System.out.println("equals comparsion£º" + s1.equals(s2));	//true
		System.out.println("equals comparsion£º" + s3.equals(s4));	//true
		System.out.println("equals comparsion£º" + "Hello".equals("Hello"));	//true
		System.out.println("equals comparsion£º" + new String("Hello").equals("Hello"));	//true
		System.out.println("equals comparsion£º" + (new String("Hello")) == "Hello");	//true
		
		s1="bcd";
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s5);
	}
}
