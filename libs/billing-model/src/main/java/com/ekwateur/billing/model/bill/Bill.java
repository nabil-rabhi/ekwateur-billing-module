package com.ekwateur.billing.model.bill;

import lombok.Builder;

@Builder
public record Bill(
        String customerReference,
        Amount amount,
        Consumption consumption
) {
}
