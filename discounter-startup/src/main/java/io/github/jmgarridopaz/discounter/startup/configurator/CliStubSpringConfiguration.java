package io.github.jmgarridopaz.discounter.startup.configurator;

import io.github.jmgarridopaz.discounter.application.DiscounterApp;
import io.github.jmgarridopaz.discounter.application.ports.driven.ForObtainingRates;
import io.github.jmgarridopaz.discounter.application.ports.driving.ForDiscounting;
import io.github.jmgarridopaz.discounter.outside.Driver;
import io.github.jmgarridopaz.discounter.outside.foradiscounting.cli.CommandLineInterface;
import io.github.jmgarridopaz.discounter.outside.forobtainingrates.testdouble.StubRateAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnProperty ( name = "config", havingValue = "cli-stub" )
public class CliStubSpringConfiguration {

	@Bean
	public Driver cli ( ForDiscounting discounterApp ) {
		return new CommandLineInterface(discounterApp);
	}

	@Bean
	public ForDiscounting discounterApp ( ForObtainingRates rateRepository ) {
		return new DiscounterApp ( rateRepository );
	}

	@Bean
	public ForObtainingRates stubRateRepository() {
		return new StubRateAdapter();
	}

}
