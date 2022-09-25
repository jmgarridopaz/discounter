package io.github.jmgarridopaz.discounter.outside.foradiscounting.testcases;

import io.github.jmgarridopaz.discounter.application.ForDiscounting;
import io.github.jmgarridopaz.discounter.outside.Driver;
import org.testng.TestNG;
import java.io.File;


public class TestDriver implements Driver {

	private static final String OUTPUT_DIRECTORY = System.getProperty("user.home") + File.separator + ".discounter" + File.separator + "test-output";

	private static ForDiscounting discounterApp;

	public TestDriver(ForDiscounting discounterApp) {
		System.out.println("TestDriver driver created...");
		TestDriver.discounterApp = discounterApp;
	}

	public static ForDiscounting getDiscounterApp() {
		return TestDriver.discounterApp;
	}

	@Override
	public void run ( String... args ) {
		System.out.println("===============================================");
		System.out.println("Running TestDriver driver using TestNG framework");
		System.out.println("HTML reports will be generated to the following directory:");
		System.out.println(OUTPUT_DIRECTORY);
		System.out.println("===============================================");
		TestNG testNG = new TestNG();
		testNG.setOutputDirectory(OUTPUT_DIRECTORY);
		testNG.setTestClasses ( new Class[]{ TestForDiscounting.class } );
		testNG.run();
	}

}
