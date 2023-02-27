package com.ekwateur.api.standards.errors.exceptions;

import org.springframework.http.HttpStatus;

import com.ekwateur.api.standards.errors.exceptions.error.Error;

public interface ApiException {

    Error getError();

    HttpStatus getResponseStatus();

}
