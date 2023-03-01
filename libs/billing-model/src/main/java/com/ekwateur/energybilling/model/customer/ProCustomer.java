package com.ekwateur.energybilling.model.customer;

import com.ekwateur.api.standards.resources.SnakeCaseable;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public final class ProCustomer extends Customer implements SnakeCaseable {

    private final String siret;
    private final String companyName;
    private final Revenue revenue;

}
