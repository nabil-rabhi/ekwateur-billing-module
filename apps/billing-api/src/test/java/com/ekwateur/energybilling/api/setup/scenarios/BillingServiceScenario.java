package com.ekwateur.energybilling.api.setup.scenarios;

import com.ekwateur.energybilling.model.bill.Bill;
import com.ekwateur.energybilling.test.scenarios.specific.BasicBillingScenario;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class BillingServiceScenario extends BasicBillingScenario {

    private final Bill expectedBill;

}
