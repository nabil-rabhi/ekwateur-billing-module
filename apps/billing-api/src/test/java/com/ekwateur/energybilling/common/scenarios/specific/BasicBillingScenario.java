package com.ekwateur.energybilling.common.scenarios.specific;

import java.time.LocalDate;

import com.ekwateur.energybilling.common.scenarios.generic.Scenario;
import com.ekwateur.energybilling.model.customer.Customer;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class BasicBillingScenario extends Scenario {

    private final Customer customer;
    private final LocalDate startDate;
    private final LocalDate endDate;

}