package com.ekwateur.enengybilling.api.services;

import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.INDIVIDUAL_CUSTOMER;
import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION;
import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.PRO_CUSTOMER_WITH_REVENUE_LOWER_THAN_ONE_MILLION;

import java.util.HashMap;
import java.util.Map;

import com.ekwateur.energybilling.model.customer.Customer;

import jakarta.annotation.PostConstruct;

public class CustomerRepository {

    private final Map<String, Customer> customers = new HashMap<>();

    @PostConstruct
    void init() {

        customers.put(INDIVIDUAL_CUSTOMER.getReference(), INDIVIDUAL_CUSTOMER);
        customers.put(PRO_CUSTOMER_WITH_REVENUE_LOWER_THAN_ONE_MILLION.getReference(), PRO_CUSTOMER_WITH_REVENUE_LOWER_THAN_ONE_MILLION);
        customers.put(PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION.getReference(), PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION);
    }

    public Customer get(String customerReference) {

        return customers.get(customerReference);
    }

}
