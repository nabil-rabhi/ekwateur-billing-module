package com.ekwateur.billing.model.bill;

import lombok.Builder;

@Builder
public record Consumption(
        long gas,
        long electricity
) {
}
