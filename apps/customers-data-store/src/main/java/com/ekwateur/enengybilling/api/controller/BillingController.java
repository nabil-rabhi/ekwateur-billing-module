package com.ekwateur.enengybilling.api.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekwateur.enengybilling.api.errors.CustomerNotFoundException;
import com.ekwateur.enengybilling.api.services.BillingService;
import com.ekwateur.enengybilling.api.services.CustomerService;
import com.ekwateur.enengybilling.model.bill.Bill;
import com.ekwateur.enengybilling.model.customer.Customer;

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
@RequestMapping("/billing")
@Tag(name = "billing",
     description = "returns the energy bill of a customer for a period of time")
public class BillingController {

    @NotNull
    private final BillingService billingService;
    private final CustomerService customerService;

    @GetMapping("/{customer-reference}")
    @Operation(description = "returns the energy bill of a customer between two dates",
               summary = "returns the energy bill of a customer between two dates",
               tags = {"billing"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "If the bill was successfully returned"),
            @ApiResponse(responseCode = "404", description = "if the customer reference was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error"),
    })
    Bill getBill(@PathVariable("customer-reference")
                 String customerReference,
                 DateRange dateRange) throws CustomerNotFoundException {

        Optional<Customer> customer = customerService.getCustomer(customerReference);

        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("no customer with reference [%s] was found".formatted(customerReference));
        }

        return billingService.getBill(customer.get(), dateRange.startDate(), dateRange.endDate());
    }

}
