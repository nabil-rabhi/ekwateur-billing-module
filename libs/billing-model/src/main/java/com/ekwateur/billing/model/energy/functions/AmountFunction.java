package com.ekwateur.billing.model.energy.functions;

import java.math.BigDecimal;

@FunctionalInterface
public interface AmountFunction<Customer, LocalDate> {

    BigDecimal apply(Customer customer,
                     LocalDate startDate,
                     LocalDate endDate);

}