package com.ekwateur.energybilling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.ekwateur.api.standards.errors.handler.StandardExceptionHandler;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Ekwateur Billing API",
                                version = "1.0",
                                description = "Ekwateur Billing API"))
@Import(StandardExceptionHandler.class)
public class EnergyBillingApplication {

    public static void main(String[] args) {

        SpringApplication.run(EnergyBillingApplication.class, args);
    }

}
