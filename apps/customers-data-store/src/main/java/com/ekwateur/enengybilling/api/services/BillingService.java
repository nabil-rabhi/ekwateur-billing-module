package com.ekwateur.enengybilling.api.services;

import static com.ekwateur.enengybilling.model.energy.EnergyType.ELECTRICITY;
import static com.ekwateur.enengybilling.model.energy.EnergyType.GAS;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ekwateur.enengybilling.model.bill.Amount;
import com.ekwateur.enengybilling.model.bill.Bill;
import com.ekwateur.enengybilling.model.bill.Consumption;
import com.ekwateur.enengybilling.model.customer.Customer;

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
