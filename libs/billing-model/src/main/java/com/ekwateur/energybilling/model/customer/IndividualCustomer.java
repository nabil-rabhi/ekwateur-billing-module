package com.ekwateur.energybilling.model.customer;

import com.ekwateur.api.standards.resources.SnakeCaseable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class IndividualCustomer extends Customer implements SnakeCaseable {

    private Civility civility;
    private String firstName;
    private String lastName;

}
