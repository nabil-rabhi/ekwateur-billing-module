package com.ekwateur.billing.model.customer;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public final class ProCustomer extends Customer {

    private final String siret;
    private final String companyName;
    private final Revenue revenue;

}
