package com.ekwateur.billing.model.bill;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record Amount(
        BigDecimal electricityAmount,
        BigDecimal gasAmount
) {

    public BigDecimal totalAmount() {

        return electricityAmount.add(gasAmount);
    }

}
