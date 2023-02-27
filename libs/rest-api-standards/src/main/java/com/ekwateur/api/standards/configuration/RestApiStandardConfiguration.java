/*
 *
 * Copyright (c) 2020 - Crédit Agricole Leasing & Factoring - All rights reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * This file can not be copied and/or distributed without the express permission of Crédit Agricole Leasing & Factoring
 *
 */

package com.ekwateur.api.standards.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.ekwateur.api.standards.errors.handler.StandardExceptionHandler;

@Configuration
@Import({SnakeCaseConfiguration.class,
         CharsetEncodingConfiguration.class})
public class RestApiStandardConfiguration {

    @Bean
    @ConditionalOnMissingBean(annotation = ControllerAdvice.class)
    StandardExceptionHandler exceptionHandler() {

        return new StandardExceptionHandler();
    }

}
