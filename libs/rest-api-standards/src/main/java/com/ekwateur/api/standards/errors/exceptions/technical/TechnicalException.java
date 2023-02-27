package com.ekwateur.api.standards.errors.exceptions.technical;

import com.ekwateur.api.standards.errors.exceptions.ApiException;

public abstract class TechnicalException extends RuntimeException implements ApiException {

    private static final long serialVersionUID = -3983940107725199007L;

    protected TechnicalException(String message) {

        super(message);
    }

}
