package com.ekwateur.api.standards.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.ekwateur.api.standards.errors.handler.StandardExceptionHandler;

@Configuration
@Import({SnakeCaseConfiguration.class,
         CharsetEncodingConfiguration.class})
public class RestApiStandardConfiguration {

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    StandardExceptionHandler standardExceptionHandler() {

        return new StandardExceptionHandler();
    }

}
