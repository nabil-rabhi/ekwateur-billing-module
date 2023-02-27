package com.ekwateur.api.standards.errors.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ekwateur.api.standards.errors.exceptions.business.BusinessException;
import com.ekwateur.api.standards.errors.exceptions.technical.TechnicalException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class StandardExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<Object> handleTechnicalException(final TechnicalException exception) {

        log.error("A technical error occurred" + exception.getMessage(), exception);
        return ResponseEntity.status(exception.getResponseStatus())
                             .body(exception.getError());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(final BusinessException exception) {

        log.error("A business error occurred" + exception.getMessage(), exception);
        return buildResponseEntity(exception);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleThrowable(final Throwable exception) {

        log.error("An unhandled exception occurred " + exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("An unhandled exception occurred " + exception.getMessage());
    }

    private ResponseEntity<Object> buildResponseEntity(BusinessException exception) {

        return ResponseEntity.status(exception.getResponseStatus())
                             .body(exception.getErrors());
    }

}



