package com.ekwateur.enengybilling.business.services.model.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ProCustomer extends Customer {

    private String siret;
    private String companyName;
    private Revenue revenue;

}
