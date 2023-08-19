package com.ekwateur.energybilling.common.fixtures;

import static com.ekwateur.energybilling.model.customer.CustomerType.INDIVIDUAL;
import static com.ekwateur.energybilling.model.customer.CustomerType.PROFESSIONAl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.ekwateur.energybilling.model.customer.Civility;
import com.ekwateur.energybilling.model.customer.IndividualCustomer;
import com.ekwateur.energybilling.model.customer.ProCustomer;
import com.ekwateur.energybilling.model.customer.Revenue;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EnergyTypeFixture {

    public static final LocalDate START_DATE = LocalDate.parse("2023-02-01", DateTimeFormatter.ISO_LOCAL_DATE);
    public static final LocalDate END_DATE = LocalDate.parse("2023-02-28", DateTimeFormatter.ISO_LOCAL_DATE);

    private static final String REFERENCE_PREFIX = "EKW";

    public static final IndividualCustomer INDIVIDUAL_CUSTOMER = IndividualCustomer.builder()
                                                                                   .reference(REFERENCE_PREFIX + "00000001")
                                                                                   .type(INDIVIDUAL)
                                                                                   .civility(Civility.MISS)
                                                                                   .firstName("Individual First Name")
                                                                                   .lastName("Individual Last Name")
                                                                                   .build();

    public static final ProCustomer PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION = ProCustomer.builder()
                                                                                                   .reference(REFERENCE_PREFIX + "00000002")
                                                                                                   .type(PROFESSIONAl)
                                                                                                   .revenue(new Revenue(BigDecimal.valueOf(10_000_000)))
                                                                                                   .siret("siret 1")
                                                                                                   .companyName("Company Name 1")
                                                                                                   .build();

    public static final ProCustomer PRO_CUSTOMER_WITH_REVENUE_LOWER_THAN_ONE_MILLION = ProCustomer.builder()
                                                                                                  .reference(REFERENCE_PREFIX + "00000003")
                                                                                                  .type(PROFESSIONAl)
                                                                                                  .revenue(new Revenue(BigDecimal.valueOf(100_000)))
                                                                                                  .siret("siret 2")
                                                                                                  .companyName("Company Name 2")
                                                                                                  .build();

    static {
        LocalDate startDate = LocalDate.parse("2023-02-01", DateTimeFormatter.ISO_LOCAL_DATE);
        Map<LocalDate, Long> someEnergyConsumptions = new HashMap<>();

        someEnergyConsumptions.put(startDate.plus(Period.ofDays(1)), 2L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(2)), 1L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(3)), 3L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(4)), 2L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(5)), 4L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(6)), 0L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(7)), 1L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(8)), 3L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(9)), 0L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(10)), 1L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(11)), 5L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(12)), 2L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(13)), 1L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(14)), 0L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(15)), 0L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(16)), 0L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(17)), 0L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(18)), 0L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(19)), 5L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(20)), 2L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(21)), 2L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(22)), 3L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(23)), 1L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(24)), 1L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(25)), 1L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(26)), 5L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(27)), 5L);
        someEnergyConsumptions.put(startDate.plus(Period.ofDays(28)), 5L);

        someEnergyConsumptions.forEach(INDIVIDUAL_CUSTOMER::addElectricityConsumption);
        someEnergyConsumptions.forEach(INDIVIDUAL_CUSTOMER::addGasConsumption);

        someEnergyConsumptions.forEach(PRO_CUSTOMER_WITH_REVENUE_LOWER_THAN_ONE_MILLION::addElectricityConsumption);
        someEnergyConsumptions.forEach(PRO_CUSTOMER_WITH_REVENUE_LOWER_THAN_ONE_MILLION::addGasConsumption);

        someEnergyConsumptions.forEach(PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION::addElectricityConsumption);
        someEnergyConsumptions.forEach(PRO_CUSTOMER_WITH_REVENUE_HIGHER_THAN_ONE_MILLION::addGasConsumption);
    }
}
