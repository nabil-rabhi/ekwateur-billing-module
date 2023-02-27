package com.ekwateur.billing.api.setup.scenarios;

import com.ekwateur.billing.model.bill.Bill;
import com.ekwateur.billing.test.scenarios.specific.BasicBillingScenario;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class BillingScenario extends BasicBillingScenario {

    private final Bill expectedBill;

}
