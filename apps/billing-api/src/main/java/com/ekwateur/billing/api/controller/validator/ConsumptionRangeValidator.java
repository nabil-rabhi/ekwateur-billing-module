package com.ekwateur.billing.api.controller.validator;

import java.time.LocalDate;

import com.ekwateur.billing.api.controller.params.ConsumptionRange;
import com.ekwateur.billing.api.errors.InvalidDateRangeException;
import com.ekwateur.billing.api.errors.MandatoryParamMissingException;

public class ConsumptionRangeValidator {

    public void validate(ConsumptionRange consumptionRange) throws InvalidDateRangeException, MandatoryParamMissingException {

        LocalDate startDate = consumptionRange.startDate();
        LocalDate endDate = consumptionRange.endDate();

        if (startDate == null) {
            throw new MandatoryParamMissingException("startDate");
        }

        if (endDate == null) {
            throw new MandatoryParamMissingException("endDate");
        }

        if (startDate.isAfter(endDate)) {
            throw new InvalidDateRangeException(startDate, endDate);
        }
    }

}
