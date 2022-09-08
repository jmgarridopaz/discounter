package io.github.jmgarridopaz.discounter.outside.actor.forcalculatingdiscount.test;

import io.github.jmgarridopaz.discounter.application.ForCalculatingDiscount;
import org.springframework.stereotype.Component;
import org.testng.TestNG;
import java.io.File;


public class TestCases implements Driver {

	private static final String OUTPUT_DIRECTORY = System.getProperty("user.home") + File.separator + ".discounter" + File.separator + "test-output";

	private static ForCalculatingDiscount discountCalculator;

	public TestCases(ForCalculatingDiscount discountCalculator ) {
		System.out.println("TestCases driver created.");
		TestCases.discountCalculator = discountCalculator;
	}

	public static ForCalculatingDiscount getDiscountCalculator() {
		return TestCases.discountCalculator;
	}

	@Override
	public void run ( String... args ) {
		System.out.println("===============================================");
		System.out.println("Running TestCases driver using TestNG framework");
		System.out.println("HTML reports will be generated to the following directory:");
		System.out.println(OUTPUT_DIRECTORY);
		System.out.println("===============================================");
		TestNG testNG = new TestNG();
		testNG.setOutputDirectory(OUTPUT_DIRECTORY);
		testNG.setTestClasses ( new Class[]{ TestDiscounter.class } );
		testNG.run();
	}

}
