package com.ekwateur.energybilling.dto;

import com.ekwateur.api.standards.resources.SnakeCaseable;

import lombok.Builder;

@Builder
public record Consumption(

        long gas,
        long electricity
)
        implements SnakeCaseable {
}
