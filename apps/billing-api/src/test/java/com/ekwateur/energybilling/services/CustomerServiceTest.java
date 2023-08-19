package com.ekwateur.energybilling.services;

import static com.ekwateur.energybilling.common.fixtures.EnergyTypeFixture.INDIVIDUAL_CUSTOMER;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.MediaType;
import org.mockserver.springtest.MockServerTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.ekwateur.energybilling.model.customer.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@MockServerTest("server.url=http://localhost:${mockServerPort}")
@ExtendWith(SpringExtension.class)
class CustomerServiceTest {

    @Value("${server.url}")
    private String serverUrl;

    private MockServerClient mockServerClient;

    @Test
    void unitTestCase() {

        //given
        String customerReference = INDIVIDUAL_CUSTOMER.getReference();
        String urlTemplate = "/customers/" + customerReference;

        mockServerClient.when(request().withMethod("GET")
                                       .withPath(urlTemplate))
                        .respond(response().withStatusCode(200)
                                           .withContentType(MediaType.APPLICATION_JSON)
                                           .withBody("")
                        );

        WebTestClient webTestClient = WebTestClient.bindToServer()
                                                   .baseUrl(serverUrl)
                                                   .build();
        //when-then
        webTestClient.get()
                     .uri(urlTemplate)
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody(Customer.class);
    }

}
