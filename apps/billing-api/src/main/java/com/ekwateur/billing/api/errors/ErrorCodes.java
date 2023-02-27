package com.ekwateur.billing.api.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCodes {

    BAD_PARAMETERS_COMBINATION("EKW_400", "Bad Parameters combination"),
    MISSING_PARAMETER("EKW_400", "Missing parameter"),
    RESOURCE_NOT_FOUND("EKW_404", "EKW Resource not found");

    private final String code;
    private final String label;
}
