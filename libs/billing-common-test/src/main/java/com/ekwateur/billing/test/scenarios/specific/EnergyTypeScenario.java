package com.ekwateur.billing.test.scenarios.specific;

import java.math.BigDecimal;

import com.ekwateur.billing.model.energy.EnergyType;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class EnergyTypeScenario extends BasicBillingScenario {

    private final EnergyType energyType;
    private final BigDecimal expectedAmount;

}
