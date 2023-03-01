package com.ekwateur.enengybilling.api.services;

import java.util.Optional;

import com.ekwateur.energybilling.model.customer.Customer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Optional<Customer> getCustomer(String customerReference) {

        return customerRepository.get(customerReference);
    }

}
