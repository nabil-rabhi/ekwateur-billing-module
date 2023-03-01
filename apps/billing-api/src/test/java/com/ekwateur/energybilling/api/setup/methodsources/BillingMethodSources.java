package com.ekwateur.energybilling.api.setup.methodsources;

import static com.ekwateur.energybilling.model.customer.CustomerType.INDIVUDUAL;
import static com.ekwateur.energybilling.model.customer.CustomerType.PROFESSIONAl;
import static com.ekwateur.energybilling.model.energy.utils.AmountUtil.round;
import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.END_DATE;
import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.INDIVIDUAL_CUSTOMER;
import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION;
import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.PRO_CUSTOMER_WITH_REVENUE_LOWER_THAN_ONE_MILLION;
import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.START_DATE;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.ekwateur.energybilling.api.controller.params.ConsumptionRange;
import com.ekwateur.energybilling.api.setup.scenarios.BillingApiScenario;
import com.ekwateur.energybilling.api.setup.scenarios.BillingServiceScenario;
import com.ekwateur.energybilling.model.bill.Amount;
import com.ekwateur.energybilling.model.bill.Bill;
import com.ekwateur.energybilling.model.bill.Consumption;

import lombok.experimental.UtilityClass;

@SuppressWarnings("unused")
@UtilityClass
public class BillingMethodSources {

    private final Bill INDIVIDUAL_BILL = Bill.builder()
                                             .customerReference(INDIVIDUAL_CUSTOMER.getReference())
                                             .customerType(INDIVUDUAL)
                                             .amount(Amount.builder()
                                                           .electricityAmount(round(3.27))
                                                           .gasAmount(round(3.10))
                                                           .build())
                                             .consumption(Consumption.builder()
                                                                     .electricity(27)
                                                                     .gas(27)
                                                                     .build())
                                             .build();

    private final Bill BILL_FOR_PRO_WITH_REVENUE_LOWER_THAN_ONE_MILLION = Bill.builder()
                                                                              .customerReference(PRO_CUSTOMER_WITH_REVENUE_LOWER_THAN_ONE_MILLION.getReference())
                                                                              .customerType(PROFESSIONAl)
                                                                              .amount(Amount.builder()
                                                                                            .electricityAmount(round(3.19))
                                                                                            .gasAmount(round(3.05))
                                                                                            .build())
                                                                              .consumption(Consumption.builder()
                                                                                                      .electricity(27)
                                                                                                      .gas(27)
                                                                                                      .build())
                                                                              .build();

    private final Bill BILL_FOR_PRO_WITH_REVENUE_HIGHER_THAN_ONE_MILLION = Bill.builder()
                                                                               .customerReference(PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION.getReference())
                                                                               .customerType(PROFESSIONAl)
                                                                               .amount(Amount.builder()
                                                                                             .electricityAmount(round(3.08))
                                                                                             .gasAmount(round(3.00))
                                                                                             .build())
                                                                               .consumption(Consumption.builder()
                                                                                                       .electricity(27)
                                                                                                       .gas(27)
                                                                                                       .build())
                                                                               .build();

    public static List<BillingServiceScenario> billingServiceScenarios() {

        return List.of(
                BillingServiceScenario.builder()
                                      .description("cas d'un client particulier")
                                      .customer(INDIVIDUAL_CUSTOMER)
                                      .startDate(START_DATE)
                                      .endDate(END_DATE)
                                      .expectedBill(INDIVIDUAL_BILL)
                                      .build(),
                BillingServiceScenario.builder()
                                      .description("cas d'un client pro ayant un chiffre d'affaire <1_000_000")
                                      .customer(PRO_CUSTOMER_WITH_REVENUE_LOWER_THAN_ONE_MILLION)
                                      .startDate(START_DATE)
                                      .endDate(END_DATE)
                                      .expectedBill(BILL_FOR_PRO_WITH_REVENUE_LOWER_THAN_ONE_MILLION)
                                      .build(),
                BillingServiceScenario.builder()
                                      .description("cas d'un client pro ayant un chiffre d'affaire >=1_000_000")
                                      .customer(PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION)
                                      .startDate(START_DATE)
                                      .endDate(END_DATE)
                                      .expectedBill(BILL_FOR_PRO_WITH_REVENUE_HIGHER_THAN_ONE_MILLION)
                                      .build()
        );
    }

    public static List<BillingApiScenario> billingApiNominalScenarios() {

        ConsumptionRange consumptionRange = ConsumptionRange.builder()
                                                            .startDate(START_DATE)
                                                            .endDate(END_DATE)
                                                            .build();
        return List.of(
                BillingApiScenario.builder()
                                  .description("cas d'un client particulier")
                                  .customerReference(INDIVIDUAL_CUSTOMER.getReference())
                                  .customer(INDIVIDUAL_CUSTOMER)
                                  .consumptionRange(consumptionRange)
                                  .expectedHttpStatus(HttpStatus.OK)
                                  .expetedBill(INDIVIDUAL_BILL)
                                  .expectedJsonResponseFilePath("billing_api/ok_scenarios/billing_api_individual_response.json")
                                  .build(),
                BillingApiScenario.builder()
                                  .description("cas d'un client pro ayant un chiffre d'affaire <1_000_000")
                                  .customerReference(PRO_CUSTOMER_WITH_REVENUE_LOWER_THAN_ONE_MILLION.getReference())
                                  .customer(PRO_CUSTOMER_WITH_REVENUE_LOWER_THAN_ONE_MILLION)
                                  .consumptionRange(consumptionRange)
                                  .expectedHttpStatus(HttpStatus.OK)
                                  .expetedBill(BILL_FOR_PRO_WITH_REVENUE_LOWER_THAN_ONE_MILLION)
                                  .expectedJsonResponseFilePath("billing_api/ok_scenarios/billing_api_pro_with_revenue_lower_than_one_million_response.json")
                                  .build(),
                BillingApiScenario.builder()
                                  .description("cas d'un client pro ayant un chiffre d'affaire >=1_000_000")
                                  .customerReference(PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION.getReference())
                                  .customer(PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION)
                                  .consumptionRange(consumptionRange)
                                  .expectedHttpStatus(HttpStatus.OK)
                                  .expetedBill(BILL_FOR_PRO_WITH_REVENUE_HIGHER_THAN_ONE_MILLION)
                                  .expectedJsonResponseFilePath("billing_api/ok_scenarios/billing_api_pro_with_revenue_higher_than_one_million_response.json")
                                  .build()
        );
    }

    public static List<BillingApiScenario> billingApiErrorScenarios() {

        return List.of(
                BillingApiScenario.builder()
                                  .description("cas d'une référence non conforme à la règle de nommage")
                                  .customerReference("some incorrect customer reference ")
                                  .customer(null)
                                  .consumptionRange(ConsumptionRange.builder()
                                                                    .startDate(START_DATE)
                                                                    .endDate(END_DATE)
                                                                    .build())
                                  .expectedHttpStatus(HttpStatus.BAD_REQUEST)
                                  .expectedJsonResponseFilePath("billing_api/ko_scenarios/billing_api_incorrect_reference_response.json")
                                  .build(),
                BillingApiScenario.builder()
                                  .description("cas où [startDate] est null && [endDate] est null")
                                  .consumptionRange(ConsumptionRange.builder()
                                                                    .startDate(null)
                                                                    .endDate(null)
                                                                    .build())
                                  .customerReference(PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION.getReference())
                                  .customer(null)
                                  .expectedHttpStatus(HttpStatus.BAD_REQUEST)
                                  .expectedJsonResponseFilePath("billing_api/ko_scenarios/billing_api_both_dates_are_null_response.json")
                                  .build(),
                BillingApiScenario.builder()
                                  .description("cas où [startDate] est null && [endDate] n'est pas null")
                                  .consumptionRange(ConsumptionRange.builder()
                                                                    .startDate(null)
                                                                    .endDate(END_DATE)
                                                                    .build())
                                  .customerReference(PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION.getReference())
                                  .customer(null)
                                  .expectedHttpStatus(HttpStatus.BAD_REQUEST)
                                  .expectedJsonResponseFilePath("billing_api/ko_scenarios/billing_api_start_date_is_null_response.json")
                                  .build(),
                BillingApiScenario.builder()
                                  .description("cas où [startDate] n'est pas null && [endDate] est null")
                                  .consumptionRange(ConsumptionRange.builder()
                                                                    .startDate(START_DATE)
                                                                    .endDate(null)
                                                                    .build())
                                  .customerReference(PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION.getReference())
                                  .customer(null)
                                  .expectedHttpStatus(HttpStatus.BAD_REQUEST)
                                  .expectedJsonResponseFilePath("billing_api/ko_scenarios/billing_api_end_date_is_null_response.json")
                                  .build(),
                BillingApiScenario.builder()
                                  .description("cas où [startDate] n'est pas null && [endDate] n'est pas null && startDate est après endDate")
                                  .consumptionRange(ConsumptionRange.builder()
                                                                    .startDate(END_DATE)
                                                                    .endDate(START_DATE)
                                                                    .build())
                                  .customerReference(PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION.getReference())
                                  .customer(null)
                                  .expectedHttpStatus(HttpStatus.BAD_REQUEST)
                                  .expectedJsonResponseFilePath("billing_api/ko_scenarios/billing_api_date_range_incorrect_response.json")
                                  .build()

        );
    }

}
