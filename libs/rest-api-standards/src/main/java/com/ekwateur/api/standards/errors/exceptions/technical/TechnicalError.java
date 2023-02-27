package com.ekwateur.api.standards.errors.exceptions.technical;

import java.io.Serializable;

import lombok.Builder;

@Builder
public record TechnicalError(
        String code,
        String label) implements Serializable {


}
