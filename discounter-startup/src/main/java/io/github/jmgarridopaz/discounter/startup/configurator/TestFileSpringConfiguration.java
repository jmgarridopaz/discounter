package io.github.jmgarridopaz.discounter.startup.configurator;

import io.github.jmgarridopaz.discounter.outside.Driver;
import io.github.jmgarridopaz.discounter.outside.foradiscounting.testcases.TestRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnProperty ( name = "config", havingValue = "test-file" )
public class TestFileSpringConfiguration {

	@Bean
	public Driver testCases() {
		return new TestRunner("file");
	}

}
