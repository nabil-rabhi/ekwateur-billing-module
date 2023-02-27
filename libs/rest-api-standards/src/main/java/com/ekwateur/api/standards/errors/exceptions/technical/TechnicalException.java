package com.ekwateur.api.standards.errors.exceptions.technical;

import org.springframework.http.HttpStatus;

public abstract class TechnicalException extends RuntimeException {

    private static final long serialVersionUID = -3983940107725199007L;

    public abstract TechnicalError getError();

    protected TechnicalException() {

        super();
    }

    public HttpStatus getResponseStatus() {

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
