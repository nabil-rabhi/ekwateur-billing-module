package com.ekwateur.api.standards.errors.exceptions.business;

import java.util.Collection;

import org.springframework.http.HttpStatus;

public abstract class BusinessException extends Exception {

    private static final long serialVersionUID = -5069808693311840644L;

    public abstract Collection<BusinessError> getErrors();

    public abstract HttpStatus getResponseStatus();

}
