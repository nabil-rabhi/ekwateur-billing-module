package com.ekwateur.api.standards.errors.exceptions.business;

import java.io.Serializable;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record BusinessError(
        @JsonProperty("code")
        String functionalBusinessCode,
        String shortLabel,
        String longLabel,
        String uriDescription,
        Collection<String> invalidProperties
) implements Serializable {


}
