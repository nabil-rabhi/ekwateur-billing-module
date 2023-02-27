package com.ekwateur.api.standards.configuration;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * forces request/response to be encoded in UTF-8
 */
@Configuration
public class CharsetEncodingConfiguration {

    @Bean
    CharacterEncodingFilter charsetEncodingFilter() {

        return new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true, true);
    }

}
