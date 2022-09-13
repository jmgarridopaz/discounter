package io.github.jmgarridopaz.discounter.outside.startup;

import io.github.jmgarridopaz.discounter.application.DiscounterApp;
import io.github.jmgarridopaz.discounter.application.ForDiscounting;
import io.github.jmgarridopaz.discounter.application.ForObtainingRates;
import io.github.jmgarridopaz.discounter.outside.Driver;
import io.github.jmgarridopaz.discounter.outside.foradiscounting.cli.Console;
import io.github.jmgarridopaz.discounter.outside.foradiscounting.testcases.TestCases;
import io.github.jmgarridopaz.discounter.outside.forobtainingrates.testdouble.StubRateRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringDiscounterAppConfigurator {

	@Bean
	@ConditionalOnProperty(name = "for-managing-discounts", havingValue = "test-cases")
	public Driver testCasesDriver ( ForDiscounting discounterApp ) {
		return new TestCases( discounterApp );
	}

	@Bean
	@ConditionalOnProperty(name = "for-managing-discounts", havingValue = "cli")
	public Driver cliDriver ( ForDiscounting discounterApp ) {
		return new Console(discounterApp);
	}

	@Bean
	public ForDiscounting discounterApp (ForObtainingRates rateRepository ) {
		return new DiscounterApp ( rateRepository );
	}

	@Bean
	@ConditionalOnProperty(name = "for-obtaining-rates", havingValue = "test-double")
	public ForObtainingRates testDoubleRateRepository() {
		return new StubRateRepository();
	}

	@Bean
	@ConditionalOnProperty(name = "for-obtaining-rates", havingValue = "file")
	public ForObtainingRates fileRateRepository() {
		return null;
	}

}
