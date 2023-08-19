package com.ekwateur.enengybilling.business.services.model.energy.enums;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ekwateur.enengybilling.business.services.model.customer.Customer;
import com.ekwateur.enengybilling.business.services.model.energy.functions.AmountFunction;
import com.ekwateur.enengybilling.business.services.model.energy.functions.EnergyAmountFunctions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EnergyType {

    ELECTRICITY(EnergyAmountFunctions.ELECTRICITY_AMOUNT_FUNCTION),
    GAS(EnergyAmountFunctions.GAS_AMOUNT_FUNCTION);

    private final AmountFunction<Customer, LocalDate> amountFunction;

    public BigDecimal getBillingAmount(Customer customer,
                                       LocalDate startDate,
                                       LocalDate endDate) {

        return amountFunction.apply(customer, startDate, endDate);
    }

}
