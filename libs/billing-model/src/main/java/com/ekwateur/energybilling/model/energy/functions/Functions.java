package com.ekwateur.energybilling.model.energy.functions;

import static com.ekwateur.energybilling.model.energy.utils.AmountUtil.round;

import java.time.LocalDate;

import com.ekwateur.energybilling.model.customer.Customer;
import com.ekwateur.energybilling.model.customer.IndividualCustomer;
import com.ekwateur.energybilling.model.customer.ProCustomer;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Functions {

    private static final double ELECTRICITY_KWH_UNIT_PRICE_FOR_INDIVIDUALS = 0.121;
    private static final double ELECTRICITY_KWH_UNIT_PRICE_FOR_PRO_WITH_REVENUE_GREATER_THAN_ONE_MILLION = 0.114;
    private static final double ELECTRICITY_KWH_UNIT_PRICE_FOR_PRO_WITH_REVENUE_LOWER_THAN_ONE_MILLION = 0.118;

    private static final double GAS_KWH_UNIT_PRICE_FOR_INDIVIDUALS = 0.115;
    private static final double GAS_KWH_UNIT_PRICE_FOR_PRO_WITH_REVENUE_GREATER_THAN_ONE_MILLION = 0.111;
    private static final double GAS_KWH_UNIT_PRICE_FOR_PRO_WITH_REVENUE_LOWER_THAN_ONE_MILLION = 0.113;

    private static final Long ONE_MILLION = 1_000_000L;

    public static final AmountFunction<Customer, LocalDate> ELECTRICITY_AMOUNT = (var customer, var startDate, var endDate) ->

            switch (customer) {

                case IndividualCustomer individualCustomer -> round(individualCustomer.getElectricityConsumption(startDate, endDate) * ELECTRICITY_KWH_UNIT_PRICE_FOR_INDIVIDUALS);
                case ProCustomer proCustomer -> {
                    Long electricityConsumption = proCustomer.getElectricityConsumption(startDate, endDate);
                    yield proCustomer.getRevenue().isGreaterThan(ONE_MILLION) ?
                            round(electricityConsumption * ELECTRICITY_KWH_UNIT_PRICE_FOR_PRO_WITH_REVENUE_GREATER_THAN_ONE_MILLION) :
                            round(electricityConsumption * ELECTRICITY_KWH_UNIT_PRICE_FOR_PRO_WITH_REVENUE_LOWER_THAN_ONE_MILLION);
                }
                case null -> throw new IllegalArgumentException("Customer should be not null");
                default -> throw new IllegalArgumentException("customer type is not supported yet");
            };

    public static final AmountFunction<Customer, LocalDate> GAS_AMOUNT = (var customer, var startDate, var endDate) ->

            switch (customer) {

                case IndividualCustomer individualCustomer -> round(individualCustomer.getGasConsumption(startDate, endDate) * GAS_KWH_UNIT_PRICE_FOR_INDIVIDUALS);
                case ProCustomer proCustomer -> {
                    Long gasConsumption = proCustomer.getGasConsumption(startDate, endDate);
                    yield proCustomer.getRevenue().isGreaterThan(ONE_MILLION) ?
                            round(gasConsumption * GAS_KWH_UNIT_PRICE_FOR_PRO_WITH_REVENUE_GREATER_THAN_ONE_MILLION) :
                            round(gasConsumption * GAS_KWH_UNIT_PRICE_FOR_PRO_WITH_REVENUE_LOWER_THAN_ONE_MILLION);
                }
                case null -> throw new IllegalArgumentException("Customer should be not null");
                default -> throw new IllegalArgumentException("customer type is not supported yet");
            };

}
