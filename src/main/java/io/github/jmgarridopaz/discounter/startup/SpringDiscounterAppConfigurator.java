package io.github.jmgarridopaz.discounter.startup;

import io.github.jmgarridopaz.discounter.application.DiscounterApp;
import io.github.jmgarridopaz.discounter.application.ForDiscounting;
import io.github.jmgarridopaz.discounter.application.ForObtainingRates;
import io.github.jmgarridopaz.discounter.outside.Driver;
import io.github.jmgarridopaz.discounter.outside.foradiscounting.cli.Console;
import io.github.jmgarridopaz.discounter.outside.foradiscounting.testcases.TestDriver;
import io.github.jmgarridopaz.discounter.outside.forobtainingrates.file.FileRateRepository;
import io.github.jmgarridopaz.discounter.outside.forobtainingrates.testdouble.StubRateRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringDiscounterAppConfigurator {

	@Bean
	@ConditionalOnProperty(name = "fordiscounting", havingValue = "testcases")
	public Driver testCasesDriver ( ForDiscounting discounterApp ) {
		return new TestDriver( discounterApp );
	}

	@Bean
	@ConditionalOnProperty(name = "fordiscounting", havingValue = "cli")
	public Driver cliDriver ( ForDiscounting discounterApp ) {
		return new Console(discounterApp);
	}

	@Bean
	public ForDiscounting discounterApp (ForObtainingRates rateRepository ) {
		return new DiscounterApp ( rateRepository );
	}

	@Bean
	@ConditionalOnProperty(name = "forobtainingrates", havingValue = "testdouble")
	public ForObtainingRates testDoubleRateRepository() {
		return new StubRateRepository();
	}

	@Bean
	@ConditionalOnProperty(name = "forobtainingrates", havingValue = "file")
	public ForObtainingRates fileRateRepository() {
		return new FileRateRepository();
	}

}
