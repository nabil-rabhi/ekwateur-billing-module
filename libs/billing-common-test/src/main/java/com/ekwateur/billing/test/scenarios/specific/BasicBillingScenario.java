package com.ekwateur.billing.test.scenarios.specific;

import java.time.LocalDate;

import com.ekwateur.billing.model.customer.Customer;
import com.ekwateur.billing.test.scenarios.generic.Scenario;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class BasicBillingScenario extends Scenario {

    private final Customer customer;
    private final LocalDate startDate;
    private final LocalDate endDate;

}