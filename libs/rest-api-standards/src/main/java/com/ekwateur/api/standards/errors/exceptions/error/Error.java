package com.ekwateur.api.standards.errors.exceptions.error;

import java.io.Serializable;

import lombok.Builder;

@Builder
public record Error(

        String code,
        String label,
        String description
)
        implements Serializable {

}
