package com.ekwateur.energybilling.model.customer;

import java.math.BigDecimal;

import com.ekwateur.api.standards.resources.SnakeCaseable;

public record Revenue(BigDecimal value) implements SnakeCaseable {

    public boolean isGreaterThan(Long value) {

        return this.value.compareTo(BigDecimal.valueOf(value)) >= 0;
    }

}
