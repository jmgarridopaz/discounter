package io.github.jmgarridopaz.discounter.outside.startup;

import io.github.jmgarridopaz.discounter.outside.actor.forcalculatingdiscount.test.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("io.github.jmgarridopaz.discounter")
public class DiscounterEntryPoint implements CommandLineRunner {

	private final Driver driver;

	public static void main ( String[] args ) {
		SpringApplication.run(DiscounterEntryPoint.class,args);
	}

	@Autowired
	public DiscounterEntryPoint ( Driver driver ) {
		System.out.println("DiscounterEntryPoint created...");
		this.driver = driver;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running DiscounterEntryPoint...");
		this.driver.run();
	}

}
