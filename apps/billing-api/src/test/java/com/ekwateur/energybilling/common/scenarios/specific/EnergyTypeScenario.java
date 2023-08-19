package com.ekwateur.energybilling.common.scenarios.specific;

import java.math.BigDecimal;

import com.ekwateur.energybilling.model.energy.enums.EnergyType;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class EnergyTypeScenario extends BasicBillingScenario {

    private final EnergyType energyType;
    private final BigDecimal expectedAmount;

}
