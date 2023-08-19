package com.ekwateur.energybilling.dto;

import com.ekwateur.api.standards.resources.SnakeCaseable;
import com.ekwateur.energybilling.model.customer.CustomerType;

import lombok.Builder;

@Builder
public record Bill(

        String customerReference,
        CustomerType customerType,
        Amount amount,
        Consumption consumption
)
        implements SnakeCaseable {
}
