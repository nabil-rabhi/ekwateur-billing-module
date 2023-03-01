package com.ekwateur.energybilling.model.customer;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Civility {

    MISTER("Mr"),
    MISS("Mrs");

    @JsonValue
    @Getter
    private final String label;
}
