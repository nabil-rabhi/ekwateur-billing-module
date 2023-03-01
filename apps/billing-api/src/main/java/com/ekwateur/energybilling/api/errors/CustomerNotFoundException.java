package com.ekwateur.energybilling.api.errors;

import static com.ekwateur.api.standards.errors.ErrorCodes.RESOURCE_NOT_FOUND;

import org.springframework.http.HttpStatus;

import com.ekwateur.api.standards.errors.exceptions.business.BusinessException;
import com.ekwateur.api.standards.errors.exceptions.error.Error;

public class CustomerNotFoundException extends BusinessException {

    private static final long serialVersionUID = -8969290164261706204L;

    public CustomerNotFoundException(Object customerReference) {

        super("No customer with the reference [%s] was found in our system".formatted(customerReference));
    }

    @Override
    public Error getError() {

        return Error.builder()
                    .code(RESOURCE_NOT_FOUND.getCode())
                    .label(RESOURCE_NOT_FOUND.getLabel())
                    .description(getMessage())
                    .build();

    }

    @Override
    public HttpStatus getResponseStatus() {

        return HttpStatus.NOT_FOUND;
    }

}
