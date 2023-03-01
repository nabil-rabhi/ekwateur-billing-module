package com.ekwateur.energybilling.api.controller;

import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.END_DATE;
import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.INDIVIDUAL_CUSTOMER;
import static com.ekwateur.energybilling.test.fixtures.EnergyTypeFixture.START_DATE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockserver.springtest.MockServerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.ekwateur.energybilling.EnergyBillingApplication;
import com.ekwateur.energybilling.test.assertions.Assertions;

@SpringBootTest(classes = EnergyBillingApplication.class,
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@MockServerTest({"mock.server.url=http://localhost:${mockServerPort}"})
class BillingControllerIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate;

    private static final String URL_TEMPLATE = "/bills/{customer-reference}?startDate={startDate}&endDate={endDate  }";

    @Test
    void this_should_work() {

        //given
        String effectiveUrl = UriComponentsBuilder.fromPath(URL_TEMPLATE)
                                                  .buildAndExpand(INDIVIDUAL_CUSTOMER.getReference(),
                                                                  START_DATE,
                                                                  END_DATE)
                                                  .toUriString();
        String expectedJsonFile = "";
        //when
        ResponseEntity<String> actualResponse = restTemplate.getForEntity(effectiveUrl,
                                                                          String.class);
        //then
        assertThat(actualResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(actualResponse.getBody()).isEqualTo(expectedJsonFile);
    }

}