package com.ekwateur.billing.model.energy.scenario;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ekwateur.billing.model.customer.Customer;
import com.ekwateur.billing.model.energy.EnergyType;
import com.ekwateur.billing.test.scenarios.generic.Scenario;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class EnergyTypeScenario extends Scenario {

    private final EnergyType energyType;
    private final Customer customer;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final BigDecimal expectedAmount;

}
