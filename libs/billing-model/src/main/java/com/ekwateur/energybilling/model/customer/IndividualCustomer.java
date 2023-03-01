package com.ekwateur.energybilling.model.customer;

import com.ekwateur.api.standards.resources.SnakeCaseable;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public final class IndividualCustomer extends Customer implements SnakeCaseable {

    private final Civility civility;
    private final String firstName;
    private final String lastName;

}
