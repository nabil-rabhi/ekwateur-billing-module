package com.ekwateur.enengybilling.business.services.model.customer;

import java.math.BigDecimal;

public record Revenue(BigDecimal value) {

    public boolean isGreaterThan(Long value) {

        return this.value.compareTo(BigDecimal.valueOf(value)) >= 0;
    }

}
