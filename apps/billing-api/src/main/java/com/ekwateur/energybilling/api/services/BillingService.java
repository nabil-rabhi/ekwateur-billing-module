package com.ekwateur.energybilling.api.services;

import static com.ekwateur.energybilling.model.energy.enums.EnergyType.ELECTRICITY;
import static com.ekwateur.energybilling.model.energy.enums.EnergyType.GAS;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ekwateur.energybilling.model.bill.Amount;
import com.ekwateur.energybilling.model.bill.Bill;
import com.ekwateur.energybilling.model.bill.Consumption;
import com.ekwateur.energybilling.model.customer.Customer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BillingService {

    public Bill getBill(Customer customer,
                        LocalDate startDate,
                        LocalDate endDate) {

        return Bill.builder()
                   .customerReference(customer.getReference())
                   .amount(amount(customer, startDate, endDate))
                   .consumption(consumption(customer, startDate, endDate))
                   .build();
    }

    private static Amount amount(Customer customer,
                                 LocalDate startDate,
                                 LocalDate endDate) {

        BigDecimal electricityAmount = ELECTRICITY.getBillingAmount(customer, startDate, endDate);
        BigDecimal gasAmount = GAS.getBillingAmount(customer, startDate, endDate);

        return Amount.builder()
                     .electricityAmount(electricityAmount)
                     .gasAmount(gasAmount)
                     .build();
    }

    private Consumption consumption(Customer customer,
                                    LocalDate startDate,
                                    LocalDate endDate) {

        return Consumption.builder()
                          .electricity(customer.getElectricityConsumption(startDate, endDate))
                          .gas(customer.getGasConsumption(startDate, endDate))
                          .build();
    }

}
