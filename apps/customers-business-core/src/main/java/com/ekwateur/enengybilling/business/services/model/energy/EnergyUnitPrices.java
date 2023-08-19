package com.ekwateur.enengybilling.business.services.model.energy;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EnergyUnitPrices {

    public static final Long ONE_MILLION = 1_000_000L;

    public static final double ELECTRICITY_PRICE_FOR_INDIVIDUALS = 0.121;
    public static final double ELECTRICITY_PRICE_FOR_PRO_WITH_REVENUE_GREATER_THAN_ONE_MILLION = 0.114;
    public static final double ELECTRICITY_PRICE_FOR_PRO_WITH_REVENUE_LOWER_THAN_ONE_MILLION = 0.118;

    public static final double GAS_PRICE_FOR_INDIVIDUALS = 0.115;
    public static final double GAS_PRICE_FOR_PRO_WITH_REVENUE_GREATER_THAN_ONE_MILLION = 0.111;
    public static final double GAS_PRICE_FOR_PRO_WITH_REVENUE_LOWER_THAN_ONE_MILLION = 0.113;

}
