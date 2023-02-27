package com.ekwateur.billing.model.customer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Civility {
    MISTER("Mr"),
    MISS("Mrs");

    @Getter
    private final String label;
}
