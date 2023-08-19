package com.ekwateur.energybilling.common.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.junit.jupiter.params.ParameterizedTest;


@ParameterizedTest(name = "{index} - {0}")
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ScenarioParameterizedTest {
}
