package com.ekwateur.enengybilling.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.ekwateur.enengybilling.api.services.BillingService;
import com.ekwateur.enengybilling.api.services.CustomerService;

@Configuration
public class BillingConfiguration {

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

}
