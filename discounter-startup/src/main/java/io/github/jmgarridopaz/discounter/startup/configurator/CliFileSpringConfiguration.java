package io.github.jmgarridopaz.discounter.startup.configurator;

import io.github.jmgarridopaz.discounter.application.DiscounterApp;
import io.github.jmgarridopaz.discounter.application.ports.driven.ForObtainingRates;
import io.github.jmgarridopaz.discounter.application.ports.driving.ForDiscounting;
import io.github.jmgarridopaz.discounter.outside.Driver;
import io.github.jmgarridopaz.discounter.outside.foradiscounting.cli.CommandLineInterface;
import io.github.jmgarridopaz.discounter.outside.forobtainingrates.file.FileRateAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
@ConditionalOnProperty ( name = "config", havingValue = "cli-file" )
public class CliFileSpringConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public Driver cli ( ForDiscounting discounterApp ) {
		return new CommandLineInterface(discounterApp);
	}

	@Bean
	public ForDiscounting discounterApp ( ForObtainingRates rateRepository ) {
		return new DiscounterApp ( rateRepository );
	}

	@Bean
	public ForObtainingRates fileRateRepository() {
		String rateFileName = this.environment.getProperty("rate.file");
		return new FileRateAdapter( rateFileName );
	}

}
