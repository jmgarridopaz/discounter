package io.github.jmgarridopaz.discounter.startup;

import io.github.jmgarridopaz.discounter.outside.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;


@SpringBootApplication
@ComponentScan("io.github.jmgarridopaz.discounter")
public class DiscounterAppEntryPoint implements CommandLineRunner {

	private final Driver driver;

	public static void main (String[] args ) {
		SpringApplication.run(DiscounterAppEntryPoint.class,args);
	}

	@Autowired
	public DiscounterAppEntryPoint ( Driver driver ) {
		this.driver = driver;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running DiscounterAppEntryPoint...");
		this.driver.run();
	}

}
