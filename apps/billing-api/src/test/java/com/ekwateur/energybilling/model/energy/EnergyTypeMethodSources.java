package com.ekwateur.energybilling.model.energy;

import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.END_DATE;
import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.START_DATE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ekwateur.energybilling.model.energy.enums.EnergyType;
import com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture;
import com.ekwateur.energybilling.test.scenarios.specific.EnergyTypeScenario;

import lombok.experimental.UtilityClass;

@SuppressWarnings("unused")
@UtilityClass
public class EnergyTypeMethodSources {

    public static List<EnergyTypeScenario> amountPerEnergyTypeScenarios() {

        List<EnergyTypeScenario> amountPerEnergyTypeScenarios = new ArrayList<>(electricityScenarios());
        amountPerEnergyTypeScenarios.addAll(gasScenarios());

        return amountPerEnergyTypeScenarios;
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
                                  .customer(EnergyTypeFixture.PRO_CUSTOMER_WITH_REVENUE_LOWER_THAN_ONE_MILLION)
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
                                  .customer(EnergyTypeFixture.PRO_CUSTOMER_WITH_REVENUE_LOWER_THAN_ONE_MILLION)
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
