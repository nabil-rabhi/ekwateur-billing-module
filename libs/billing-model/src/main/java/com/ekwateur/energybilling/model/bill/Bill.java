package com.ekwateur.energybilling.model.bill;

import com.ekwateur.api.standards.resources.SnakeCaseable;

import lombok.Builder;

@Builder
public record Bill(

        String customerReference,
        Amount amount,
        Consumption consumption
)
        implements SnakeCaseable {
}
