package com.ekwateur.api.standards.errors.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ekwateur.api.standards.errors.exceptions.ApiException;
import com.ekwateur.api.standards.errors.exceptions.business.BusinessException;
import com.ekwateur.api.standards.errors.exceptions.error.ApiError;
import com.ekwateur.api.standards.errors.exceptions.technical.TechnicalException;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class StandardExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<Object> handleTechnicalException(TechnicalException exception) {

        log.error("A technical error occurred" + exception.getMessage(), exception);
        return buildResponseEntity(exception);

    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException exception) {

        log.error("A business error occurred:\n" + exception.getMessage(), exception);
        return buildResponseEntity(exception);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleThrowable(Throwable exception) {

        log.error("An unhandled exception occurred:\n" + exception.getMessage(), exception);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("An unhandled exception occurred " + exception.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> handleValidationException(ValidationException exception) {

        log.error("A validation exception occurred:\n" + exception.getMessage(), exception);

        String description = "";

        if (exception instanceof ConstraintViolationException ex) {
            description = ex.getConstraintViolations()
                            .stream()
                            .findFirst()
                            .map(constraintViolation -> "[%s] %s".formatted(constraintViolation.getInvalidValue(), constraintViolation.getMessage()))
                            .orElse("");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(ApiError.builder()
                                           .code("VALIDATION_ERROR")
                                           .label("Incorrect Parameter value")
                                           .description(description)
                                           .build());

    }

    private ResponseEntity<Object> buildResponseEntity(ApiException exception) {

        return ResponseEntity.status(exception.getResponseStatus())
                             .body(exception.getError());
    }

}



