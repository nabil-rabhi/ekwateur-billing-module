package com.ekwateur.energybilling.controller.validator;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.MethodSource;

import com.ekwateur.energybilling.common.annotation.ScenarioParameterizedTest;
import com.ekwateur.energybilling.common.setup.scenarios.ConsumptionRangeScenario;

class ConsumptionRangeValidatorTest {

    ConsumptionRangeValidator consumptionRangeValidator;

    @BeforeEach
    void setUp() {

        consumptionRangeValidator = new ConsumptionRangeValidator();
    }

    @ScenarioParameterizedTest
    @MethodSource("com.ekwateur.energybilling.common.setup.methodsources.ConsumptionRangeMethodSources#consumptionRangeScenarios")
    void validate_should_throw_an_exception_when_input_is_invalid(ConsumptionRangeScenario scenario) {

        //when -then
        assertThatExceptionOfType(scenario.getExpectedException().getClass())
                .isThrownBy(() -> consumptionRangeValidator.validate(scenario.getConsumptionRange()))
                .withMessage(scenario.getExpectedException().getMessage());
    }

}