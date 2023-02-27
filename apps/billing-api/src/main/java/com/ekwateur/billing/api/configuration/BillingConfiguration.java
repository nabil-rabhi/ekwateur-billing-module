package com.ekwateur.billing.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.reactive.function.client.WebClient;

import com.ekwateur.billing.api.controller.validator.ConsumptionRangeValidator;
import com.ekwateur.billing.api.services.BillingService;
import com.ekwateur.billing.api.services.CustomerService;

@Configuration
public class BillingConfiguration {

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {

        return new MethodValidationPostProcessor();
    }

    @Bean
    BillingService billingService() {

        return new BillingService();
    }

    @Bean
    CustomerService customerService(WebClient customerWebClient) {

        return new CustomerService(customerWebClient);
    }

    @Bean
    public WebClient customerWebClient(@Value("${customer-service.url}")
                                       String customerServiceUrl) {

        return WebClient.builder()
                        .baseUrl(customerServiceUrl)
                        .build();
    }

    @Bean
    ConsumptionRangeValidator consumptionRangeValidator() {

        return new ConsumptionRangeValidator();
    }

}
