package com.ekwateur.energybilling.api.setup.methodsources;

import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.END_DATE;
import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.START_DATE;

import java.util.List;

import com.ekwateur.energybilling.api.controller.params.ConsumptionRange;
import com.ekwateur.energybilling.api.errors.InvalidDateRangeException;
import com.ekwateur.energybilling.api.errors.MandatoryParamMissingException;
import com.ekwateur.energybilling.api.setup.scenarios.ConsumptionRangeScenario;

import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("unused")
public class ConsumptionRangeMethodSources {

    public static List<ConsumptionRangeScenario> consumptionRangeScenarios() {

        return List.of(
                ConsumptionRangeScenario.builder()
                                        .description("cas où [startDate] est null && [endDate] est null")
                                        .consumptionRange(ConsumptionRange.builder()
                                                                          .startDate(null)
                                                                          .endDate(null)
                                                                          .build())
                                        .expectedException(new MandatoryParamMissingException("startDate"))
                                        .build(),

                ConsumptionRangeScenario.builder()
                                        .description("cas où [startDate] est null && [endDate] n'est pas null")
                                        .consumptionRange(ConsumptionRange.builder()
                                                                          .startDate(null)
                                                                          .endDate(END_DATE)
                                                                          .build())
                                        .expectedException(new MandatoryParamMissingException("startDate"))
                                        .build(),
                ConsumptionRangeScenario.builder()
                                        .description("cas où [startDate] n'est pas null && [endDate] est null")
                                        .consumptionRange(ConsumptionRange.builder()
                                                                          .startDate(START_DATE)
                                                                          .endDate(null)
                                                                          .build())
                                        .expectedException(new MandatoryParamMissingException("endDate"))
                                        .build(),
                ConsumptionRangeScenario.builder()
                                        .description("cas où [startDate] n'est pas null && [endDate] n'est pas null && startDate est après endDate")
                                        .consumptionRange(ConsumptionRange.builder()
                                                                          .startDate(END_DATE)
                                                                          .endDate(START_DATE)
                                                                          .build())
                                        .expectedException(new InvalidDateRangeException(END_DATE, START_DATE))
                                        .build()

        );
    }

}
