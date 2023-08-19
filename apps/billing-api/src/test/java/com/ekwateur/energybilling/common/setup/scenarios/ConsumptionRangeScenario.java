package com.ekwateur.energybilling.common.setup.scenarios;

import com.ekwateur.energybilling.common.scenarios.generic.Scenario;
import com.ekwateur.energybilling.controller.params.ConsumptionRange;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ConsumptionRangeScenario extends Scenario {

    private final ConsumptionRange consumptionRange;
    private final Exception expectedException;

}
