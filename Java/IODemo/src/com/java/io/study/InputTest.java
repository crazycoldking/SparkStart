package com.java.io.study;

import java.util.Scanner;

public class InputTest {

	private Scanner scanner;

	public void getInput() {
		System.out.println("Please input some characters.");
		scanner = new Scanner(System.in);
		System.out.println("You have entered: " + scanner.nextLine());
	}
}
