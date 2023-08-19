package com.ekwateur.energybilling.errors;

import static com.ekwateur.api.standards.errors.ErrorCodes.MISSING_PARAMETER;

import org.springframework.http.HttpStatus;

import com.ekwateur.api.standards.errors.exceptions.business.BusinessException;
import com.ekwateur.api.standards.errors.exceptions.error.ApiError;

public class MandatoryParamMissingException extends BusinessException {

    public MandatoryParamMissingException(String paramName) {

        super("the mandatory parameter [%s] is eiher missing or null".formatted(paramName));
    }

    @Override
    public ApiError getError() {

        return ApiError.builder()
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
