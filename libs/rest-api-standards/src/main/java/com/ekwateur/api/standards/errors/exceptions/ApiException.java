package com.ekwateur.api.standards.errors.exceptions;

import org.springframework.http.HttpStatus;

import com.ekwateur.api.standards.errors.exceptions.error.ApiError;

public interface ApiException {

    ApiError getError();

    HttpStatus getResponseStatus();

}
