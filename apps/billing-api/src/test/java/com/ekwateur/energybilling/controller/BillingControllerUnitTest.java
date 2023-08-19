package com.ekwateur.energybilling.controller;

import static com.ekwateur.energybilling.common.fixtures.EnergyTypeFixture.END_DATE;
import static com.ekwateur.energybilling.common.fixtures.EnergyTypeFixture.INDIVIDUAL_CUSTOMER;
import static com.ekwateur.energybilling.common.fixtures.EnergyTypeFixture.START_DATE;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import com.ekwateur.api.standards.configuration.RestApiStandardConfiguration;
import com.ekwateur.energybilling.common.annotation.ScenarioParameterizedTest;
import com.ekwateur.energybilling.common.setup.scenarios.BillingApiScenario;
import com.ekwateur.energybilling.controller.validator.ConsumptionRangeValidator;
import com.ekwateur.energybilling.model.customer.Customer;
import com.ekwateur.energybilling.services.BillingService;
import com.ekwateur.energybilling.services.CustomerService;

import lombok.SneakyThrows;

@WebMvcTest(BillingController.class)
@Import({
        RestApiStandardConfiguration.class,
        ConsumptionRangeValidator.class
})
class BillingControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BillingService billingService;

    @MockBean
    CustomerService customerService;

    private static final String URL_TEMPLATE = "/bills/{customer-reference}?startDate={startDate}&endDate={endDate}";

    @SneakyThrows
    @ScenarioParameterizedTest
    @MethodSource("com.ekwateur.energybilling.common.setup.methodsources.BillingMethodSources#billingApiNominalScenarios")
    void getBill_should_return_expected_result_according_to_the_nominal_scenario(BillingApiScenario scenario) {

        //given
        String customerReference = scenario.getCustomerReference();

        Customer customer = scenario.getCustomer();
        Optional<Customer> optionalCustomer = Optional.of(customer);

        when(customerService.getCustomer(customerReference)).thenReturn(optionalCustomer);

        String effectiveUrl = UriComponentsBuilder.fromPath(URL_TEMPLATE)
                                                  .buildAndExpand(customerReference,
                                                                  scenario.getConsumptionRange().startDate(),
                                                                  scenario.getConsumptionRange().endDate())
                                                  .toUriString();
        //when -then
        mockMvc.perform(get(effectiveUrl))
               .andDo(print())
               .andExpect(status().is(scenario.getExpectedHttpStatus().value()))
               .andExpect(content().encoding(UTF_8))
               .andReturn();

        verify(customerService).getCustomer(customerReference);
        verify(billingService).getBill(customer, scenario.getConsumptionRange().startDate(), scenario.getConsumptionRange().endDate());

    }

    @SneakyThrows
    @ScenarioParameterizedTest
    @MethodSource("com.ekwateur.energybilling.common.setup.methodsources.BillingMethodSources#billingApiErrorScenarios")
    void getBill_should_return_expected_error_result_according_to_the_error_scenario(BillingApiScenario scenario) {

        //given
        String effectiveUrl = UriComponentsBuilder.fromPath(URL_TEMPLATE)
                                                  .buildAndExpand(scenario.getCustomerReference(),
                                                                  scenario.getConsumptionRange().startDate(),
                                                                  scenario.getConsumptionRange().endDate())
                                                  .toUriString();
        //when -then
        mockMvc.perform(get(effectiveUrl))
               .andDo(print())
               .andExpect(status().is(scenario.getExpectedHttpStatus().value()))
               .andExpect(content().encoding(UTF_8))
               .andReturn();

        verifyNoInteractions(customerService);
        verifyNoInteractions(billingService);
    }

    @SneakyThrows
    @Test
    void getBill_should_return_HttpStatus_NOT_FOUND_when_customerReference_is_not_recognized() {

        //given
        String customerReference = INDIVIDUAL_CUSTOMER.getReference();

        when(customerService.getCustomer(customerReference)).thenReturn(Optional.empty());

        String effectiveUrl = UriComponentsBuilder.fromPath(URL_TEMPLATE)
                                                  .buildAndExpand(customerReference,
                                                                  START_DATE,
                                                                  END_DATE)
                                                  .toUriString();
        //when -then
        mockMvc.perform(get(effectiveUrl))
               .andDo(print())
               .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
               .andExpect(content().encoding(UTF_8))
               .andReturn();

        verifyNoInteractions(billingService);
    }

}