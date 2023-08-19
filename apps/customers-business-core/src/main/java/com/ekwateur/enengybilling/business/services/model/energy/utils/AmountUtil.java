package com.ekwateur.enengybilling.business.services.model.energy.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AmountUtil {

    public static BigDecimal round(double value) {

        BigDecimal amount = BigDecimal.valueOf(value);
        return amount.setScale(2, RoundingMode.HALF_EVEN);
    }

}
