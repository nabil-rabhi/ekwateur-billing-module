package com.ekwateur.api.standards.errors.exceptions.business;

import com.ekwateur.api.standards.errors.exceptions.ApiException;

public abstract class BusinessException extends Exception implements ApiException {

    private static final long serialVersionUID = -5069808693311840644L;

    protected BusinessException(String message) {

        super(message);
    }

}
