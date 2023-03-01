package com.ekwateur.enengybilling.api.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekwateur.enengybilling.api.services.CustomerService;
import com.ekwateur.energybilling.model.customer.Customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/customers", produces = APPLICATION_JSON_VALUE)
@Tag(name = "customers",
     description = "contains operations on customers")
public class CustomerController {

    @NotNull
    private final CustomerService customerService;

    @GetMapping("/{customer-reference}")
    @Operation(description = "returns a customer by his/her/its reference",
               summary = "returns a customer by his/her/its reference",
               tags = {"customers"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "If the customer was successfully returned"),
            @ApiResponse(responseCode = "500", description = "Internal Server error"),
    })
    Customer getCustomer(@PathVariable("customer-reference") String customerReference) {

        return customerService.getCustomer(customerReference);

    }

}
