package com.ekwateur.energybilling.model.customer;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
              property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = IndividualCustomer.class, name = "IndividualCustomer"),
        @JsonSubTypes.Type(value = ProCustomer.class, name = "ProCustomer")
})
public sealed class Customer permits ProCustomer, IndividualCustomer {

    @Pattern(regexp = "^EKW(\\d{8})$")
    protected String reference;
    protected CustomerType type;

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
