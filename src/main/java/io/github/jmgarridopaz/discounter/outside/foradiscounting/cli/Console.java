package io.github.jmgarridopaz.discounter.outside.foradiscounting.cli;

import io.github.jmgarridopaz.discounter.application.Amount;
import io.github.jmgarridopaz.discounter.application.ForDiscounting;
import io.github.jmgarridopaz.discounter.outside.Driver;

import java.math.BigDecimal;
import java.util.Scanner;


public class Console implements Driver {

	private final static String MAX_AMOUNT = Integer.MAX_VALUE + ".00";

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
		String[][] ratesByAmountIntervals = { {"20.01","100.00","0.15"} , {"100.01",MAX_AMOUNT,"0.35"} };
		this.discounterApp.initializeRates ( ratesByAmountIntervals );
		System.out.println("The rates are:");
		System.out.printf("%15s\t%15s\t%10s\n","Min Amount","Max Amount","Rate");
		for ( String[] rateOfInterval : ratesByAmountIntervals ) {
			System.out.printf("%15s\t%15s\t%10s\n",rateOfInterval[0],rateOfInterval[1],rateOfInterval[2]);
		}
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		while (!exit) {
			printAppMenu();
			try {
				String option = scanner.next();
				switch (option){
					case "1":
						option1(scanner);
						break;
					case "2":
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
		String amount = scanner.next();
		System.out.println("The discount is:");
		Amount discount = this.discounterApp.calculateDiscount( Amount.parse(amount) );
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
