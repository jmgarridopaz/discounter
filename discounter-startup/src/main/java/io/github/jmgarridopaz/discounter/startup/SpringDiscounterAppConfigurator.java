package io.github.jmgarridopaz.discounter.startup;

import io.github.jmgarridopaz.discounter.application.DiscounterApp;
import io.github.jmgarridopaz.discounter.application.ports.driven.ForObtainingRates;
import io.github.jmgarridopaz.discounter.application.ports.driving.ForDiscounting;
import io.github.jmgarridopaz.discounter.outside.Driver;
import io.github.jmgarridopaz.discounter.outside.foradiscounting.cli.CommandLineInterface;
import io.github.jmgarridopaz.discounter.outside.foradiscounting.testcases.TestRunner;
import io.github.jmgarridopaz.discounter.outside.forobtainingrates.file.FileRateAdapter;
import io.github.jmgarridopaz.discounter.outside.forobtainingrates.testdouble.StubRateAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
public class SpringDiscounterAppConfigurator {

	@Autowired
	private Environment environment;

	@Bean
	@ConditionalOnProperty(name = "config.option", havingValue = "test")
	public Driver testCases() {
		return new TestRunner();
	}

	@Bean
	@ConditionalOnExpression(
	"'${config.option}'.equals('cli-none') or '${config.option}'.equals('cli-test') or '${config.option}'.equals('cli-file')"
	)
	public Driver cliDriver ( ForDiscounting discounterApp ) {
		return new CommandLineInterface(discounterApp);
	}

	@Bean
	@ConditionalOnProperty(name = "config.option", havingValue = "cli-none")
	public ForDiscounting discounterAppWithNoRepository() {
		return new DiscounterApp ( null );
	}

	@Bean
	@ConditionalOnExpression ( "'${config.option}'.equals('cli-test') or '${config.option}'.equals('cli-file')" )
	public ForDiscounting discounterApp ( ForObtainingRates rateRepository ) {
		return new DiscounterApp ( rateRepository );
	}

	@Bean
	@ConditionalOnProperty(name = "config.option", havingValue = "cli-test")
	public ForObtainingRates testDoubleRateRepository() {
		return new StubRateAdapter();
	}

	@Bean
	@ConditionalOnProperty(name = "config.option", havingValue = "cli-file")
	public ForObtainingRates fileRateRepository() {
		String rateFileName = this.environment.getProperty("rates.file.name");
		return new FileRateAdapter ( rateFileName );
	}

}
