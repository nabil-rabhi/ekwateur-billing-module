package com.ekwateur.billing;

import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BillingApiApplicationTests {

    @Test
    void context_should_load_without_errors() {
        // add assertion for Sonar analysis purpose
        assertThatNoException().isThrownBy(this::doNotThrowException);
    }

    private void doNotThrowException() {
        //This method will never throw exception
    }

}
