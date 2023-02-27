package com.ekwateur.enengybilling.api.services;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.ekwateur.enengybilling.model.customer.Customer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerService {

    private final WebClient customerWebClient;

    public Optional<Customer> getCustomer(String customerReference) {

        Customer customer = customerWebClient.get()
                                             .uri("/customers", customerReference)
                                             .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                             .retrieve()
                                             .bodyToMono(Customer.class)
                                             .block();
        return Optional.ofNullable(customer);
    }

}
