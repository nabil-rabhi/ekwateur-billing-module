package com.ekwateur.energybilling.api.controller;

import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.END_DATE;
import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION;
import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.START_DATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ekwateur.energybilling.api.controller.params.ConsumptionRange;
import com.ekwateur.energybilling.api.services.BillingService;
import com.ekwateur.energybilling.api.services.CustomerService;
import com.ekwateur.energybilling.api.setup.scenarios.BillingApiScenario;
import com.ekwateur.energybilling.model.customer.Customer;
import com.ekwateur.energybilling.test.annotation.ScenarioParameterizedTest;
import com.ekwateur.energybilling.test.assertions.Assertions;

import lombok.SneakyThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BillingControllerIntegrationTest {

    public static final String URL_TEMPLATE = "/bills/{customerReference}?startDate={startDate}&endDate={endDate}";
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private BillingService billingService;

    @MockBean
    private CustomerService customerService;

    @SneakyThrows
    @ScenarioParameterizedTest
    @MethodSource("com.ekwateur.energybilling.api.setup.methodsources.BillingMethodSources#billingApiNominalScenarios")
    void getBill_should_return_expected_result_according_to_the_nominal_scenario(BillingApiScenario scenario) {

        //given
        Customer customer = scenario.getCustomer();
        String customerReference = scenario.getCustomerReference();

        LocalDate startDate = scenario.getConsumptionRange().startDate();
        LocalDate endDate = scenario.getConsumptionRange().endDate();

        when(customerService.getCustomer(customerReference)).thenReturn(Optional.of(customer));

        when(billingService.getBill(eq(customer),
                                    any(LocalDate.class),
                                    any(LocalDate.class)))
                .thenReturn(scenario.getExpetedBill());
        //when
        ResponseEntity<String> actualResponse = restTemplate.getForEntity(URL_TEMPLATE,
                                                                          String.class,
                                                                          customerReference,
                                                                          startDate,
                                                                          endDate);
        //then
        assertThat(actualResponse.getStatusCode()).isEqualTo(scenario.getExpectedHttpStatus());
        Assertions.assertThat(actualResponse.getBody()).isEqualTo(scenario.getExpectedJsonResponseFilePath());

        verify(customerService).getCustomer(customerReference);
        verify(billingService).getBill(customer, startDate, endDate);
    }

    @SneakyThrows
    @ScenarioParameterizedTest
    @MethodSource("com.ekwateur.energybilling.api.setup.methodsources.BillingMethodSources#billingApiErrorScenarios")
    void getBill_should_return_expected_error_result_according_to_the_error_scenario(BillingApiScenario scenario) {

        //when
        ResponseEntity<String> actualResponse = restTemplate.getForEntity(URL_TEMPLATE,
                                                                          String.class,
                                                                          scenario.getCustomerReference(),
                                                                          scenario.getConsumptionRange().startDate(),
                                                                          scenario.getConsumptionRange().endDate());
        //then
        assertThat(actualResponse.getStatusCode()).isEqualTo(scenario.getExpectedHttpStatus());
        Assertions.assertThat(actualResponse.getBody()).isEqualTo(scenario.getExpectedJsonResponseFilePath());

        verifyNoInteractions(customerService);
        verifyNoInteractions(billingService);
    }

    @SneakyThrows
    @Test
    void getBill_should_return_HttpStatus_NOT_FOUND_when_customerReference_is_not_recognized() {

        //given
        BillingApiScenario scenario = BillingApiScenario.builder()
                                                        .description("cas où les dates sont valides mais le customerReference n'est pas reconnu")
                                                        .consumptionRange(ConsumptionRange.builder()
                                                                                          .startDate(START_DATE)
                                                                                          .endDate(END_DATE)
                                                                                          .build())
                                                        .customerReference(PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION.getReference())
                                                        .customer(null)
                                                        .expectedHttpStatus(HttpStatus.NOT_FOUND)
                                                        .expectedJsonResponseFilePath("billing_api/ko_scenarios/billing_api_customer_not_found_response.json")
                                                        .build();

        String customerReference = scenario.getCustomerReference();

        when(customerService.getCustomer(customerReference)).thenReturn(Optional.empty());

        //when
        ResponseEntity<String> actualResponse = restTemplate.getForEntity(URL_TEMPLATE,
                                                                          String.class,
                                                                          customerReference,
                                                                          START_DATE,
                                                                          END_DATE);
        //then
        assertThat(actualResponse.getStatusCode()).isEqualTo(scenario.getExpectedHttpStatus());
        Assertions.assertThat(actualResponse.getBody()).isEqualTo(scenario.getExpectedJsonResponseFilePath());

        verify(customerService).getCustomer(customerReference);
        verifyNoInteractions(billingService);
    }

}
