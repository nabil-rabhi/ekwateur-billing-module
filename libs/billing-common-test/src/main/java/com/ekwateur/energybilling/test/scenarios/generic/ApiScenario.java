package com.ekwateur.energybilling.test.scenarios.generic;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class ApiScenario extends Scenario {

    private final String expectedJsonResponseFile;
    private final HttpStatus expectedHttpStatus;

}
