package com.ekwateur.energybilling.test.assertions;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Assertions {

    public static JsonAssert assertThat(String actual) {

        return new JsonAssert(actual);
    }

}