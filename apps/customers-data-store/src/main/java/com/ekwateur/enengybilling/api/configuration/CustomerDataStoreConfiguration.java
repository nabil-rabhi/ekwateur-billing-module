package com.ekwateur.enengybilling.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ekwateur.enengybilling.api.services.CustomerRepository;
import com.ekwateur.enengybilling.api.services.CustomerService;

@Configuration
public class CustomerDataStoreConfiguration {

    @Bean
    CustomerService customerService(CustomerRepository customerRepository) {

        return new CustomerService(customerRepository);
    }

    @Bean
    CustomerRepository customerRepository() {

        return new CustomerRepository();
    }

}
