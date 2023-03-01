package com.ekwateur.energybilling.api.setup.scenarios;

import com.ekwateur.energybilling.api.controller.params.ConsumptionRange;
import com.ekwateur.energybilling.test.scenarios.generic.Scenario;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ConsumptionRangeScenario extends Scenario {

    private final ConsumptionRange consumptionRange;
    private final Exception expectedException;

}
