package com.ekwateur.energybilling.model.energy.enums;

import static com.ekwateur.energybilling.model.energy.functions.Functions.ELECTRICITY_AMOUNT;
import static com.ekwateur.energybilling.model.energy.functions.Functions.GAS_AMOUNT;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ekwateur.energybilling.model.customer.Customer;
import com.ekwateur.energybilling.model.energy.functions.AmountFunction;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EnergyType {

    ELECTRICITY(ELECTRICITY_AMOUNT),
    GAS(GAS_AMOUNT);

    private final AmountFunction<Customer, LocalDate> amountFunction;

    public BigDecimal getBillingAmount(Customer customer,
                                       LocalDate startDate,
                                       LocalDate endDate) {

        return amountFunction.apply(customer, startDate, endDate);
    }

}
