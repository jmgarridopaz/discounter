package io.github.jmgarridopaz.discounter.outside.foradiscounting.testcases;

import io.github.jmgarridopaz.discounter.outside.Driver;
import org.testng.TestNG;

import java.io.File;
import java.time.Instant;

public class TestRunner implements Driver {

	private static final String OUTPUT_DIRECTORY =	System.getProperty("user.home") + File.separator
													+ ".discounterapp" + File.separator
													+ "test-output" + File.separator
													+ Instant.now();

	private static final Class[] TEST_CLASSES_TO_RUN =	new Class[]{
															GivenNoRateRepository.class,
															GivenStubRateRepository.class
														};

	@Override
	public void run ( String... args ) {
		System.out.println("==========================================================");
		System.out.println("Running test cases driver using TestNG framework");
		System.out.println("HTML reports will be generated to the following directory:");
		System.out.println(OUTPUT_DIRECTORY);
		System.out.println("==========================================================");
		TestNG testNG = new TestNG();
		testNG.setOutputDirectory ( OUTPUT_DIRECTORY );
		testNG.setTestClasses ( TEST_CLASSES_TO_RUN );
		testNG.run();
	}

}
