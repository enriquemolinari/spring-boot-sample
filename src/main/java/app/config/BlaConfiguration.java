package app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import port.UseCase;

@Configuration
@ComponentScan(
        basePackages = "hex.*",
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE, value = UseCase.class
        )
)
public class BlaConfiguration {
	@Bean
    public DecorateUseCases beanProcessor() {
        return new DecorateUseCases();
    }
}
