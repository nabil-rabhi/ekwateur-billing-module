package com.ekwateur.energybilling.common.assertions;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Assertions {

    public static JsonAssert assertThat(String actual) {

        return new JsonAssert(actual);
    }

}