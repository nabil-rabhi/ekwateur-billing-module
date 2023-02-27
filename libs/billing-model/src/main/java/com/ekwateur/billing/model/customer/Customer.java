package com.ekwateur.billing.model.customer;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public sealed class Customer permits ProCustomer, IndividualCustomer {

    @NotBlank
    @Pattern(regexp = "^EKW(\\d{8})$")
    protected final String reference;

    private final Map<LocalDate, Long> electricityConsumptions = new ConcurrentHashMap<>();
    private final Map<LocalDate, Long> gasConsumptions = new ConcurrentHashMap<>();

    public Long getElectricityConsumption(LocalDate beginDate,
                                          LocalDate endDate) {

        return getConsumption(electricityConsumptions, beginDate, endDate);
    }

    public Long getGasConsumption(LocalDate beginDate,
                                  LocalDate endDate) {

        return getConsumption(gasConsumptions, beginDate, endDate);
    }

    public void addElectricityConsumption(LocalDate localDate,
                                          Long dailyConsumption) {

        electricityConsumptions.putIfAbsent(localDate, dailyConsumption);
    }

    public void addGasConsumption(LocalDate localDate,
                                  Long dailyConsumption) {

        gasConsumptions.putIfAbsent(localDate, dailyConsumption);
    }

    private long getConsumption(Map<LocalDate, Long> consumptions,
                                LocalDate beginDate,
                                LocalDate endDate) {

        List<Map.Entry<LocalDate, Long>> entries = consumptions.entrySet()
                                                               .stream()
                                                               .filter(dateBetween(beginDate, endDate))
                                                               .toList();

        return entries.stream()
                      .map(Map.Entry::getValue)
                      .count();
    }

    private Predicate<Map.Entry<LocalDate, Long>> dateBetween(LocalDate startDate,
                                                              LocalDate endDate) {

        return entry -> {
            LocalDate localDate = entry.getKey();
            return !(localDate.isBefore(startDate) || localDate.isAfter(endDate));
        };
    }

}
