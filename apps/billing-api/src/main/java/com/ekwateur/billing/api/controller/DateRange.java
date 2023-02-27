package com.ekwateur.billing.api.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public record DateRange(

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate startDate,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate endDate
) {

}
