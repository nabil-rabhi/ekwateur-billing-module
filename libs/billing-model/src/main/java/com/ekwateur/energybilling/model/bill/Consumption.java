package com.ekwateur.energybilling.model.bill;

import com.ekwateur.api.standards.resources.SnakeCaseable;

import lombok.Builder;

@Builder
public record Consumption(

        long gas,
        long electricity
)
        implements SnakeCaseable {
}
