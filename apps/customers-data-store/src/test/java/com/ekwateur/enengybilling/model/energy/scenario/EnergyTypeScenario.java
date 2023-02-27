package com.ekwateur.enengybilling.model.energy.scenario;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ekwateur.enengybilling.model.customer.Customer;
import com.ekwateur.enengybilling.model.energy.EnergyType;
import com.ekwateur.enengybilling.test.scenarios.Scenario;

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
