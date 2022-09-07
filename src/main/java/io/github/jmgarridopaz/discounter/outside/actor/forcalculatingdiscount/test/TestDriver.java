package io.github.jmgarridopaz.discounter.outside.actor.forcalculatingdiscount.test;


import io.github.jmgarridopaz.discounter.application.DiscounterApi;

public class TestDriver {

	private final DiscounterApi discounterApplication;

	public TestDriver ( DiscounterApi discounterApplication ) {
		this.discounterApplication = discounterApplication;
	}

	public void run() {

	}

/*
 private static final String OUTPUT_DIRECTORY = System.getProperty("user.home") + File.separator + ".discounter" + File.separator + "test-output";

    public static void main (String[] args ) {
        TestNG testNG = new TestNG();
        testNG.setOutputDirectory(OUTPUT_DIRECTORY);
        testNG.setTestClasses ( new Class[]{ DiscounterAppTest.class } );
        testNG.run();
    }
*/	
	
}
