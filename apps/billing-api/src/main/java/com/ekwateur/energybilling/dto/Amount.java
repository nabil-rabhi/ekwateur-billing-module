package com.ekwateur.energybilling.dto;

import java.math.BigDecimal;

import com.ekwateur.api.standards.resources.SnakeCaseable;

import lombok.Builder;

@Builder
public record Amount(

        BigDecimal electricityAmount,
        BigDecimal gasAmount
)
        implements SnakeCaseable {


}
