package com.ekwateur.enengybilling.business.services.model.customer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public enum CustomerType {
    INDIVIDUAL("Particulier"),
    PROFESSIONAl("Professionnel");

    @Getter
    private final String type;
}
