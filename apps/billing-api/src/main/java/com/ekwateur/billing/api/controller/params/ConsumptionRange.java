package com.ekwateur.billing.api.controller.params;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public record ConsumptionRange(

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate startDate,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate endDate
) {

}
