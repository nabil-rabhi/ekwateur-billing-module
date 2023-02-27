package com.ekwateur.billing.model.customer;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public final class IndividualCustomer extends Customer {

    private final Civility civility;
    private final String firstName;
    private final String lastName;

}
