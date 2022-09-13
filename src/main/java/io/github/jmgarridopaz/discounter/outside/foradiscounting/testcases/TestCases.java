package io.github.jmgarridopaz.discounter.outside.foradiscounting.testcases;

import io.github.jmgarridopaz.discounter.application.ForDiscounting;
import io.github.jmgarridopaz.discounter.outside.Driver;
import org.testng.TestNG;
import java.io.File;


public class TestCases implements Driver {

	private static final String OUTPUT_DIRECTORY = System.getProperty("user.home") + File.separator + ".discounter" + File.separator + "test-output";

	private static ForDiscounting discounterApp;

	public TestCases(ForDiscounting discounterApp) {
		System.out.println("TestCases driver created...");
		TestCases.discounterApp = discounterApp;
	}

	public static ForDiscounting getDiscounterApp() {
		return TestCases.discounterApp;
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
		testNG.setTestClasses ( new Class[]{ TestDiscounterApp.class } );
		testNG.run();
	}

}
