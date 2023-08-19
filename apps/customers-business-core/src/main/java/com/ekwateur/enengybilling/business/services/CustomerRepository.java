package com.ekwateur.enengybilling.business.services;

import com.ekwateur.energybilling.model.customer.Customer;

public interface CustomerRepository {


    Customer get(String customerReference);

}
