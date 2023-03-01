package com.ekwateur.energybilling.model.customer;

import java.math.BigDecimal;

import com.ekwateur.api.standards.resources.SnakeCaseable;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Revenue(@JsonProperty("value") BigDecimal value) implements SnakeCaseable {

    public boolean isGreaterThan(Long value) {

        return this.value.compareTo(BigDecimal.valueOf(value)) >= 0;
    }

}
