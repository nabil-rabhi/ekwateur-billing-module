package com.ekwateur.enengybilling.services;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ekwateur.enengybilling.business.services.CustomerRepository;
import com.ekwateur.enengybilling.business.services.CustomerService;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {

        customerService = new CustomerService(customerRepository);
    }

    @Test
    void getCustomer_should_call_customerRepository() {

        //given
        String customerReference = "customerReference";

        //when
        customerService.getCustomer(customerReference);

        //then
        verify(customerRepository).get(customerReference);
    }

}