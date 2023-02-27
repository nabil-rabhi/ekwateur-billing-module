package com.ekwateur.billing.test.scenarios.specific;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class AmountPerEnergyTypeScenario extends EnergyTypeScenario {

    private final BigDecimal expectedAmount;

}
