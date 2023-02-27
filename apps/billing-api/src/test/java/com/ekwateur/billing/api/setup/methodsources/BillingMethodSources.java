package com.ekwateur.billing.api.setup.methodsources;

import static com.ekwateur.billing.model.energy.AmountUtil.round;
import static com.ekwateur.billing.test.fixtures.EnergyTypeFixture.END_DATE;
import static com.ekwateur.billing.test.fixtures.EnergyTypeFixture.INDIVIDUAL_CUSTOMER;
import static com.ekwateur.billing.test.fixtures.EnergyTypeFixture.PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION;
import static com.ekwateur.billing.test.fixtures.EnergyTypeFixture.PRO_CUSTOMER_WITH_REVENUE_LESSER_THAN_ONE_MILLION;
import static com.ekwateur.billing.test.fixtures.EnergyTypeFixture.START_DATE;

import java.util.List;

import com.ekwateur.billing.api.setup.scenarios.BillingScenario;
import com.ekwateur.billing.model.bill.Amount;
import com.ekwateur.billing.model.bill.Bill;
import com.ekwateur.billing.model.bill.Consumption;

import lombok.experimental.UtilityClass;

@SuppressWarnings("unused")
@UtilityClass
public class BillingMethodSources {

    public static List<BillingScenario> billingScenarios() {

        Bill individualBill = Bill.builder()
                                  .customerReference(INDIVIDUAL_CUSTOMER.getReference())
                                  .amount(Amount.builder()
                                                .electricityAmount(round(3.27))
                                                .gasAmount(round(3.10))
                                                .build())
                                  .consumption(Consumption.builder()
                                                          .electricity(27)
                                                          .gas(27)
                                                          .build())
                                  .build();

        Bill billForProWithRevenueLessThanOneMillion = Bill.builder()
                                                           .customerReference(PRO_CUSTOMER_WITH_REVENUE_LESSER_THAN_ONE_MILLION.getReference())
                                                           .amount(Amount.builder()
                                                                         .electricityAmount(round(3.19))
                                                                         .gasAmount(round(3.05))
                                                                         .build())
                                                           .consumption(Consumption.builder()
                                                                                   .electricity(27)
                                                                                   .gas(27)
                                                                                   .build())
                                                           .build();

        Bill billForProWithRevenueHigherThanOneMillion = Bill.builder()
                                                             .customerReference(PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION.getReference())
                                                             .amount(Amount.builder()
                                                                           .electricityAmount(round(3.08))
                                                                           .gasAmount(round(3.00))
                                                                           .build())
                                                             .consumption(Consumption.builder()
                                                                                     .electricity(27)
                                                                                     .gas(27)
                                                                                     .build())
                                                             .build();
        return List.of(
                BillingScenario.builder()
                               .description("cas d'un client particulier")
                               .customer(INDIVIDUAL_CUSTOMER)
                               .startDate(START_DATE)
                               .endDate(END_DATE)
                               .expectedBill(individualBill)
                               .build(),
                BillingScenario.builder()
                               .description("cas d'un client pro ayant un chiffre d'affaire <1_000_000")
                               .customer(PRO_CUSTOMER_WITH_REVENUE_LESSER_THAN_ONE_MILLION)
                               .startDate(START_DATE)
                               .endDate(END_DATE)
                               .expectedBill(billForProWithRevenueLessThanOneMillion)
                               .build(),
                BillingScenario.builder()
                               .description("Electricité - cas d'un client pro ayant un chiffre d'affaire >=1_000_000")
                               .customer(PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION)
                               .startDate(START_DATE)
                               .endDate(END_DATE)
                               .expectedBill(billForProWithRevenueHigherThanOneMillion)
                               .build()
        );
    }

}
