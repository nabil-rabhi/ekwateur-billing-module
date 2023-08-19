package com.ekwateur.enengybilling.business.controller;

import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.INDIVIDUAL_CUSTOMER;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.ekwateur.api.standards.configuration.RestApiStandardConfiguration;
import com.ekwateur.enengybilling.business.services.CustomerService;

import lombok.SneakyThrows;

@WebMvcTest(CustomerController.class)
@Import(RestApiStandardConfiguration.class)
class CustomerControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    private static final String URL_TEMPLATE = "/customers/{customer-reference}";

    @SneakyThrows
    @Test
    void getCustomer_should_return_expected_result() {

        //given
        String customerReference = INDIVIDUAL_CUSTOMER.getReference();

        when(customerService.getCustomer(customerReference)).thenReturn(INDIVIDUAL_CUSTOMER);

        //when -then
        mockMvc.perform(get(URL_TEMPLATE, customerReference))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().encoding(UTF_8))
               .andReturn();

        verify(customerService).getCustomer(customerReference);

    }

}