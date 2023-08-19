package com.ekwateur.energybilling.common.scenarios.generic;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ApiScenario extends Scenario {

    private final String expectedJsonResponseFilePath;
    private final HttpStatus expectedHttpStatus;

}
