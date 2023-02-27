package com.ekwateur.billing.model.methodsources;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.ekwateur.billing.model.energy.EnergyType;
import com.ekwateur.billing.model.energy.fixtures.EnergyTypeFixture;
import com.ekwateur.billing.model.energy.scenario.EnergyTypeScenario;

import lombok.experimental.UtilityClass;

@SuppressWarnings("unused")
@UtilityClass
public class EnergyTypeMethodSources {

    private static final LocalDate START_DATE = LocalDate.parse("2023-02-01", DateTimeFormatter.ISO_LOCAL_DATE);
    private static final LocalDate END_DATE = LocalDate.parse("2023-02-28", DateTimeFormatter.ISO_LOCAL_DATE);

    public static List<EnergyTypeScenario> energyTypeScenarios() {

        List<EnergyTypeScenario> energyTypeAmountScenarios = new ArrayList<>(electricityScenarios());
        energyTypeAmountScenarios.addAll(gasScenarios());
        return energyTypeAmountScenarios;
    }

    private static List<EnergyTypeScenario> electricityScenarios() {

        return List.of(
                EnergyTypeScenario.builder()
                                  .description("Elecrticité - cas d'un client particulier")
                                  .customer(EnergyTypeFixture.INDIVIDUAL_CUSTOMER)
                                  .energyType(EnergyType.ELECTRICITY)
                                  .startDate(START_DATE)
                                  .endDate(END_DATE)
                                  .expectedAmount(BigDecimal.valueOf(3.27))
                                  .build(),
                EnergyTypeScenario.builder()
                                  .description("Elecrticité - cas d'un client pro ayant un chiffre d'affaire <1_000_000")
                                  .customer(EnergyTypeFixture.PRO_CUSTOMER_WITH_REVENUE_LESSER_THAN_ONE_MILLION)
                                  .energyType(EnergyType.ELECTRICITY)
                                  .startDate(START_DATE)
                                  .endDate(END_DATE)
                                  .expectedAmount(BigDecimal.valueOf(3.19))
                                  .build(),
                EnergyTypeScenario.builder()
                                  .description("Electricité - cas d'un client pro ayant un chiffre d'affaire >=1_000_000")
                                  .customer(EnergyTypeFixture.PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION)
                                  .energyType(EnergyType.ELECTRICITY)
                                  .startDate(START_DATE)
                                  .endDate(END_DATE)
                                  .expectedAmount(BigDecimal.valueOf(3.08))
                                  .build()
        );
    }

    private static List<EnergyTypeScenario> gasScenarios() {

        return List.of(
                EnergyTypeScenario.builder()
                                  .description("Gaz - cas d'un client particulier")
                                  .customer(EnergyTypeFixture.INDIVIDUAL_CUSTOMER)
                                  .energyType(EnergyType.GAS)
                                  .startDate(START_DATE)
                                  .endDate(END_DATE)
                                  .expectedAmount(BigDecimal.valueOf(3.10))
                                  .build(),

                EnergyTypeScenario.builder()
                                  .description("Gaz - cas d'un client pro ayant un chiffre d'affaire <1_000_000")
                                  .customer(EnergyTypeFixture.PRO_CUSTOMER_WITH_REVENUE_LESSER_THAN_ONE_MILLION)
                                  .energyType(EnergyType.GAS)
                                  .startDate(START_DATE)
                                  .endDate(END_DATE)
                                  .expectedAmount(BigDecimal.valueOf(3.05))
                                  .build(),

                EnergyTypeScenario.builder()
                                  .description("Gaz - cas d'un client pro ayant un chiffre d'affaire >=1_000_000")
                                  .customer(EnergyTypeFixture.PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION)
                                  .energyType(EnergyType.GAS)
                                  .startDate(START_DATE)
                                  .endDate(END_DATE)
                                  .expectedAmount(BigDecimal.valueOf(3.00))
                                  .build()
        );
    }

}
