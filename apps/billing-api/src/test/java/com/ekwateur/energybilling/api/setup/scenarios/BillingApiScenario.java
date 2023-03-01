package com.ekwateur.energybilling.api.setup.scenarios;

import com.ekwateur.energybilling.api.controller.params.ConsumptionRange;
import com.ekwateur.energybilling.model.bill.Bill;
import com.ekwateur.energybilling.model.customer.Customer;
import com.ekwateur.energybilling.test.scenarios.generic.ApiScenario;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class BillingApiScenario extends ApiScenario {

    private final String customerReference;
    private final ConsumptionRange consumptionRange;
    private final Customer customer;
    private final Bill expetedBill;

}
