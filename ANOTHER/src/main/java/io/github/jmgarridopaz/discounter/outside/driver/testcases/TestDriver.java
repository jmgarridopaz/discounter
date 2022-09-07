package io.github.jmgarridopaz.discounter.outside.driver.testcases;


import org.testng.TestNG;

import java.io.File;

public class TestDriver {

    private static final String OUTPUT_DIRECTORY = System.getProperty("user.home") + File.separator + ".discounter" + File.separator + "test-output";

    public static void main (String[] args ) {
        TestNG testNG = new TestNG();
        testNG.setOutputDirectory(OUTPUT_DIRECTORY);
        testNG.setTestClasses ( new Class[]{ DiscounterAppTest.class } );
        testNG.run();
    }

}
