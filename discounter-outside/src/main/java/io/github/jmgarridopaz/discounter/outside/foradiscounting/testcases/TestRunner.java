package io.github.jmgarridopaz.discounter.outside.foradiscounting.testcases;

import io.github.jmgarridopaz.discounter.outside.Driver;
import org.testng.TestNG;

import java.io.File;
import java.time.Instant;

public class TestRunner implements Driver {

	private static final String OUTPUT_DIRECTORY =	System.getProperty("user.home") + File.separator
													+ ".discounterapp" + File.separator
													+ "test-output" + File.separator
													+ Instant.now().toEpochMilli();

	private final String forObtainingRatesAdapter;

	public TestRunner ( String forObtainingRatesAdapter ) {
		this.forObtainingRatesAdapter = forObtainingRatesAdapter;
	}

	@Override
	public void run ( String... args ) {
		System.out.println("==========================================================");
		System.out.println("Running test cases driver using TestNG framework");
		System.out.println("HTML reports will be generated to the following directory:");
		System.out.println(OUTPUT_DIRECTORY);
		System.out.println("==========================================================");
		TestNG testNG = new TestNG();
		testNG.setOutputDirectory ( OUTPUT_DIRECTORY );
		testNG.setTestClasses ( testClassesToRun() );
		testNG.run();
	}

	private Class[] testClassesToRun() {
		if ( this.forObtainingRatesAdapter==null ) {
			return new Class[] { NoRateRepositoryTests.class };
		}
		if ( this.forObtainingRatesAdapter.trim().isEmpty() ) {
			return new Class[] { NoRateRepositoryTests.class };
		}
		if ( this.forObtainingRatesAdapter.trim().equals("none") ) {
			return new Class[] { NoRateRepositoryTests.class };
		}
		if ( this.forObtainingRatesAdapter.trim().equals("stub") ) {
			return new Class[] { StubRateRepositoryTests.class };
		}
		if ( this.forObtainingRatesAdapter.trim().equals("file") ) {
			return new Class[] { FileRateRepositoryTests.class };
		}
		return new Class[] {};
	}

}
