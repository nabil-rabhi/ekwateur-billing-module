package com.ekwateur.enengybilling.business.services.model.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class IndividualCustomer extends Customer {

    private Civility civility;
    private String firstName;
    private String lastName;

}
