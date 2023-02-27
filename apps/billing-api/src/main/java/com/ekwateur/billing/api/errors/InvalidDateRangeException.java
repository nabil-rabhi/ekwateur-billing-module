package com.ekwateur.billing.api.errors;

import static com.ekwateur.billing.api.errors.ErrorCodes.BAD_PARAMETERS_COMBINATION;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;

import com.ekwateur.api.standards.errors.exceptions.business.BusinessException;
import com.ekwateur.api.standards.errors.exceptions.error.Error;

public class InvalidDateRangeException extends BusinessException {

    public InvalidDateRangeException(LocalDate startDate,
                                     LocalDate endDate) {

        super("the date range is incorrect, start date [%s] is after end date [%s]".formatted(startDate, endDate));
    }

    @Override
    public Error getError() {

        return Error.builder()
                    .code(BAD_PARAMETERS_COMBINATION.getCode())
                    .label(BAD_PARAMETERS_COMBINATION.getLabel())
                    .description(getMessage())
                    .build();
    }

    @Override
    public HttpStatus getResponseStatus() {

        return HttpStatus.BAD_REQUEST;
    }

}
