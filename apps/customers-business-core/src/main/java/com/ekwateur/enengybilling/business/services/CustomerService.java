package com.ekwateur.enengybilling.business.services;

import com.ekwateur.energybilling.model.customer.Customer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer getCustomer(String customerReference) {

        return customerRepository.get(customerReference);
    }

}
