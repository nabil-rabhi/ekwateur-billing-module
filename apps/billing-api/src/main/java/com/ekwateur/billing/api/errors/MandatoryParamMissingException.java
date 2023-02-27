package com.ekwateur.billing.api.errors;

import static com.ekwateur.billing.api.errors.ErrorCodes.MISSING_PARAMETER;

import org.springframework.http.HttpStatus;

import com.ekwateur.api.standards.errors.exceptions.business.BusinessException;
import com.ekwateur.api.standards.errors.exceptions.error.Error;

public class MandatoryParamMissingException extends BusinessException {

    public MandatoryParamMissingException(String paramName) {

        super("the mandatory parameter [%s] is eiher missing or null".formatted(paramName));
    }

    @Override
    public Error getError() {

        return Error.builder()
                    .code(MISSING_PARAMETER.getCode())
                    .label(MISSING_PARAMETER.getLabel())
                    .description(getMessage())
                    .build();
    }

    @Override
    public HttpStatus getResponseStatus() {

        return HttpStatus.BAD_REQUEST;
    }

}
