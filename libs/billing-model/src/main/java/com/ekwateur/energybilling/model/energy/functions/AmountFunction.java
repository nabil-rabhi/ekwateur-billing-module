package com.ekwateur.energybilling.model.energy.functions;

import java.math.BigDecimal;

@FunctionalInterface
public interface AmountFunction<Customer, LocalDate> {

    BigDecimal apply(Customer customer,
                     LocalDate startDate,
                     LocalDate endDate);

}