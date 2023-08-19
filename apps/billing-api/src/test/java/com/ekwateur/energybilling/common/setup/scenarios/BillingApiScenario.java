package com.ekwateur.energybilling.common.setup.scenarios;

import com.ekwateur.energybilling.common.scenarios.generic.ApiScenario;
import com.ekwateur.energybilling.controller.params.ConsumptionRange;
import com.ekwateur.energybilling.model.bill.Bill;
import com.ekwateur.energybilling.model.customer.Customer;

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
