package com.ekwateur.energybilling.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.MethodSource;

import com.ekwateur.energybilling.common.annotation.ScenarioParameterizedTest;
import com.ekwateur.energybilling.common.setup.scenarios.BillingServiceScenario;
import com.ekwateur.energybilling.model.bill.Bill;

class BillingServiceTest {

    BillingService billingService;

    @BeforeEach
    void setUp() {

        billingService = new BillingService();
    }

    @ScenarioParameterizedTest
    @MethodSource("com.ekwateur.energybilling.common.setup.methodsources.BillingMethodSources#billingServiceScenarios")
    void getBill_should_return_expected_result(BillingServiceScenario scenario) {

        //when
        Bill actual = billingService.getBill(scenario.getCustomer(), scenario.getStartDate(), scenario.getEndDate());

        //then
        assertThat(actual).isEqualTo(scenario.getExpectedBill());
    }

}