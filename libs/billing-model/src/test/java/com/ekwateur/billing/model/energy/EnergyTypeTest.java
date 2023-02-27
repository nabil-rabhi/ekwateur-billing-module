package com.ekwateur.billing.model.energy;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.params.provider.MethodSource;

import com.ekwateur.billing.model.energy.scenario.AmountPerEnergyTypeScenario;
import com.ekwateur.billing.test.scenarios.ScenarioParameterizedTest;

class EnergyTypeTest {

    @ScenarioParameterizedTest
    @MethodSource("com.ekwateur.enengybilling.model.methodsources.EnergyTypeMethodSources#energyTypeScenarios")
    void getBillingAmount_should_return_expected_result(AmountPerEnergyTypeScenario scenario) {

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