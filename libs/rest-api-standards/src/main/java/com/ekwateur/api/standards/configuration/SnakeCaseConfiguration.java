package com.ekwateur.api.standards.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ekwateur.api.standards.filters.SnakeCaseFilter;

/**
 * Converts fields in equest/response from camelCase to snake_case
 */
@Configuration
public class SnakeCaseConfiguration {

    @Bean
    public OncePerRequestFilter snakeCaseConverterFilter() {

        return new SnakeCaseFilter();

    }

}
