package com.ekwateur.billing.api.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.MethodSource;

import com.ekwateur.billing.api.setup.scenarios.BillingScenario;
import com.ekwateur.billing.model.bill.Bill;
import com.ekwateur.billing.test.annotation.ScenarioParameterizedTest;

class BillingServiceTest {

    BillingService billingService;

    @BeforeEach
    void setUp() {

        billingService = new BillingService();
    }

    @ScenarioParameterizedTest
    @MethodSource("com.ekwateur.billing.api.scenarios.methodsources.BillingMethodSources#billingScenarios")
    void getBill_should_return_expected_result(BillingScenario scenario) {

        //when
        Bill actual = billingService.getBill(scenario.getCustomer(), scenario.getStartDate(), scenario.getEndDate());

        //then
        assertThat(actual).isEqualTo(scenario.getExpectedBill());
    }

}