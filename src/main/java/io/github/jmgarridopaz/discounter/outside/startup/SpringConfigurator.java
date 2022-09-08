package io.github.jmgarridopaz.discounter.outside.startup;

import io.github.jmgarridopaz.discounter.application.Discounter;
import io.github.jmgarridopaz.discounter.application.ForCalculatingDiscount;
import io.github.jmgarridopaz.discounter.outside.actor.forcalculatingdiscount.test.Driver;
import io.github.jmgarridopaz.discounter.outside.actor.forcalculatingdiscount.test.TestCases;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringConfigurator {

	@Bean
	public ForCalculatingDiscount discountCalculator() {
		return new Discounter();
	}

	@Bean
	public Driver testCases() {
		return new TestCases ( discountCalculator() );
	}

}
