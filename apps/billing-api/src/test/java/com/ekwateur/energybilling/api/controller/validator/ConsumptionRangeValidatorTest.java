package com.ekwateur.energybilling.api.controller.validator;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.MethodSource;

import com.ekwateur.energybilling.api.setup.scenarios.ConsumptionRangeScenario;
import com.ekwateur.energybilling.test.annotation.ScenarioParameterizedTest;

class ConsumptionRangeValidatorTest {

    ConsumptionRangeValidator consumptionRangeValidator;

    @BeforeEach
    void setUp() {

        consumptionRangeValidator = new ConsumptionRangeValidator();
    }

    @ScenarioParameterizedTest
    @MethodSource("com.ekwateur.energybilling.api.setup.methodsources.ConsumptionRangeMethodSources#consumptionRangeScenarios")
    void validate_should_throw_an_exception_when_input_is_invalid(ConsumptionRangeScenario scenario) {

        //when -then
        Assertions.assertThatExceptionOfType(scenario.getExpectedException().getClass())
                  .isThrownBy(() -> consumptionRangeValidator.validate(scenario.getConsumptionRange()))
                  .withMessage(scenario.getExpectedException().getMessage());
    }

}