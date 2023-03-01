package com.ekwateur.enengybilling.api.services;

import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.INDIVIDUAL_CUSTOMER;
import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION;
import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.PRO_CUSTOMER_WITH_REVENUE_LESSER_THAN_ONE_MILLION;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.ekwateur.energybilling.model.customer.Customer;

import jakarta.annotation.PostConstruct;

public class CustomerRepository {

    private final Map<String, Customer> customers = new ConcurrentHashMap<>();

    @PostConstruct
    void init() {

        customers.put(INDIVIDUAL_CUSTOMER.getReference(), INDIVIDUAL_CUSTOMER);
        customers.put(PRO_CUSTOMER_WITH_REVENUE_LESSER_THAN_ONE_MILLION.getReference(), PRO_CUSTOMER_WITH_REVENUE_LESSER_THAN_ONE_MILLION);
        customers.put(PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION.getReference(), PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION);
    }

    public Optional<Customer> get(String customerReference) {

        return Optional.ofNullable(customers.get(customerReference));
    }

}
