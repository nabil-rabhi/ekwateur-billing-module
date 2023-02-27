package com.ekwateur.billing.api.controller;

import java.util.Optional;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekwateur.billing.api.controller.params.ConsumptionRange;
import com.ekwateur.billing.api.controller.validator.ConsumptionRangeValidator;
import com.ekwateur.billing.api.errors.CustomerNotFoundException;
import com.ekwateur.billing.api.errors.InvalidDateRangeException;
import com.ekwateur.billing.api.errors.MandatoryParamMissingException;
import com.ekwateur.billing.api.services.BillingService;
import com.ekwateur.billing.api.services.CustomerService;
import com.ekwateur.billing.model.bill.Bill;
import com.ekwateur.billing.model.customer.Customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/billing")
@Tag(name = "billing",
     description = "returns the energy bill of a customer for a period of time")
public class BillingController {

    @NotNull
    private final BillingService billingService;

    @NotNull
    private final CustomerService customerService;

    @NotNull
    private final ConsumptionRangeValidator consumptionRangeValidator;

    @GetMapping("/{customer-reference}")
    @Operation(description = "returns the energy bill of a customer between two dates",
               summary = "returns the energy bill of a customer between two dates",
               tags = {"billing"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "If the bill was successfully returned"),
            @ApiResponse(responseCode = "400", description = "if at least one parameter of the request is incorrect"),
            @ApiResponse(responseCode = "404", description = "if the customer reference was not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server error"),
    })
    Bill getBill(@Pattern(regexp = "^EKW(\\d{8})$")
                 @PathVariable("customer-reference")
                 String customerReference,
                 ConsumptionRange consumptionRange) throws CustomerNotFoundException, InvalidDateRangeException, MandatoryParamMissingException {

        consumptionRangeValidator.validate(consumptionRange);

        Optional<Customer> customer = customerService.getCustomer(customerReference);

        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(customerReference);
        }

        return billingService.getBill(customer.get(), consumptionRange.startDate(), consumptionRange.endDate());
    }

}
