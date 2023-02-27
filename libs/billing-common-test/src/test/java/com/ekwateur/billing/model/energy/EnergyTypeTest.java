package com.ekwateur.billing.model.energy;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.params.provider.MethodSource;

import com.ekwateur.billing.test.annotation.ScenarioParameterizedTest;
import com.ekwateur.billing.test.scenarios.specific.EnergyTypeScenario;

class EnergyTypeTest {

    @ScenarioParameterizedTest
    @MethodSource("com.ekwateur.billing.model.energy.EnergyTypeMethodSources#amountPerEnergyTypeScenarios")
    void getBillingAmount_should_return_expected_result(EnergyTypeScenario scenario) {

        //given
        EnergyType energyType = scenario.getEnergyType();

        //when
        BigDecimal actual = energyType.getBillingAmount(scenario.getCustomer(),
                                                        scenario.getStartDate(),
                                                        scenario.getEndDate());

        //then
        assertThat(actual).isEqualByComparingTo(scenario.getExpectedAmount());
    }

}