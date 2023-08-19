package com.ekwateur.enengybilling.business.services.model.energy.functions;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ekwateur.enengybilling.business.services.model.customer.Customer;
import com.ekwateur.enengybilling.business.services.model.customer.IndividualCustomer;
import com.ekwateur.enengybilling.business.services.model.customer.ProCustomer;
import com.ekwateur.enengybilling.business.services.model.energy.EnergyUnitPrices;
import com.ekwateur.enengybilling.business.services.model.energy.utils.AmountUtil;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EnergyAmountFunctions {

    public static final AmountFunction<Customer, LocalDate> ELECTRICITY_AMOUNT_FUNCTION = (var customer, var startDate, var endDate) ->

            switch (customer) {

                case IndividualCustomer individualCustomer -> electricityPrice(individualCustomer, startDate, endDate, EnergyUnitPrices.ELECTRICITY_PRICE_FOR_INDIVIDUALS);
                case ProCustomer proCustomer when proCustomer.getRevenue().isGreaterThan(EnergyUnitPrices.ONE_MILLION) ->
                        electricityPrice(proCustomer, startDate, endDate, EnergyUnitPrices.ELECTRICITY_PRICE_FOR_PRO_WITH_REVENUE_GREATER_THAN_ONE_MILLION);
                case ProCustomer proCustomer -> electricityPrice(proCustomer, startDate, endDate, EnergyUnitPrices.ELECTRICITY_PRICE_FOR_PRO_WITH_REVENUE_LOWER_THAN_ONE_MILLION);

                case null -> throw new IllegalArgumentException("Customer should be not null");
            };

    public static final AmountFunction<Customer, LocalDate> GAS_AMOUNT_FUNCTION = (var customer, var startDate, var endDate) ->

            switch (customer) {

                case IndividualCustomer individualCustomer -> gasPrice(individualCustomer, startDate, endDate, EnergyUnitPrices.GAS_PRICE_FOR_INDIVIDUALS);
                case ProCustomer proCustomer when proCustomer.getRevenue().isGreaterThan(EnergyUnitPrices.ONE_MILLION) ->
                        gasPrice(proCustomer, startDate, endDate, EnergyUnitPrices.GAS_PRICE_FOR_PRO_WITH_REVENUE_GREATER_THAN_ONE_MILLION);
                case ProCustomer proCustomer -> gasPrice(proCustomer, startDate, endDate, EnergyUnitPrices.GAS_PRICE_FOR_PRO_WITH_REVENUE_LOWER_THAN_ONE_MILLION);

                case null -> throw new IllegalArgumentException("Customer should be not null");
            };

    private static BigDecimal electricityPrice(Customer customer,
                                               LocalDate startDate,
                                               LocalDate endDate,
                                               double electricityPrice) {

        return AmountUtil.round(customer.getElectricityConsumption(startDate, endDate) * electricityPrice);
    }

    private static BigDecimal gasPrice(Customer customer,
                                       LocalDate startDate,
                                       LocalDate endDate,
                                       double gasPrice) {

        return AmountUtil.round(customer.getGasConsumption(startDate, endDate) * gasPrice);
    }

}
