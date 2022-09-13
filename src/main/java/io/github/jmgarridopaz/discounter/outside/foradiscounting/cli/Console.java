package io.github.jmgarridopaz.discounter.outside.foradiscounting.cli;

import io.github.jmgarridopaz.discounter.application.ForDiscounting;
import io.github.jmgarridopaz.discounter.outside.Driver;
import io.github.jmgarridopaz.discounter.outside.foradiscounting.testcases.TestCases;

import java.math.BigDecimal;
import java.util.Scanner;


public class Console implements Driver {

	private final ForDiscounting discounterApp;

	public Console(ForDiscounting discounterApp) {
		System.out.println("CLI driver created...");
		this.discounterApp = discounterApp;
	}

	@Override
	public void run ( String... args ) {
		System.out.println("===============================================");
		System.out.println("Running CLI driver");
		System.out.println("===============================================");
		String[][] ratesByAmountIntervals = { {"20.01","100.00","0.15"} , {"100.01","","0.35"} };
		this.discounterApp.initializeRates ( ratesByAmountIntervals );
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		while (!exit) {
			printAppMenu();
			try {
				int option = scanner.nextInt();
				switch (option){
					case 1:
						option1(scanner);
						break;
					case 2:
						exit = true;
						break;
					default:
						System.out.println("Invalid option");
						break;
				}
			} catch (Exception e) {
				System.out.println("Unexpected error");
				e.printStackTrace();
			}
		}
	}

	private void option1 ( Scanner scanner ) {
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("Enter an amount:");
		String amountAsString = scanner.next();
		System.out.println("The discount is:");
		BigDecimal discount = this.discounterApp.calculateDiscount( new BigDecimal(amountAsString) );
		System.out.println(discount.toString());
	}

	private void printAppMenu() {
		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("-------------------");
		System.out.println("DISCOUNTER APP MENU");
		System.out.println("-------------------");
		System.out.println("(1) Calculate discount.");
		System.out.println("(2) Exit.");
		System.out.println("Choose an option:");
	}

}
