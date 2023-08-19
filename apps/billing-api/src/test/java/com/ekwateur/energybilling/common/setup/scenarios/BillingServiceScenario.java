package com.ekwateur.energybilling.common.setup.scenarios;

import com.ekwateur.energybilling.common.scenarios.specific.BasicBillingScenario;
import com.ekwateur.energybilling.model.bill.Bill;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class BillingServiceScenario extends BasicBillingScenario {

    private final Bill expectedBill;

}
