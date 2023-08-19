package com.ekwateur.energybilling.common.setup.methodsources;

import static com.ekwateur.energybilling.common.fixtures.EnergyTypeFixture.END_DATE;
import static com.ekwateur.energybilling.common.fixtures.EnergyTypeFixture.START_DATE;

import java.util.List;

import com.ekwateur.energybilling.common.setup.scenarios.ConsumptionRangeScenario;
import com.ekwateur.energybilling.controller.params.ConsumptionRange;
import com.ekwateur.energybilling.errors.InvalidDateRangeException;
import com.ekwateur.energybilling.errors.MandatoryParamMissingException;

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
