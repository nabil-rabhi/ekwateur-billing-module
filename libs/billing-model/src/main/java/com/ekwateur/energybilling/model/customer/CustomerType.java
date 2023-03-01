package com.ekwateur.energybilling.model.customer;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public enum CustomerType {
    INDIVUDUAL("Particulier"),
    PROFESSIONAl("Professionnel");

    @Getter
    @JsonValue
    private final String type;
}
