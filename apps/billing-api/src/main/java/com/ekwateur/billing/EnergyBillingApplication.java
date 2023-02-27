package com.ekwateur.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.ekwateur.api.standards.errors.handler.StandardExceptionHandler;

@SpringBootApplication
@Import(StandardExceptionHandler.class)
public class EnergyBillingApplication {

    public static void main(String[] args) {

        SpringApplication.run(EnergyBillingApplication.class, args);
    }

}
