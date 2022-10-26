package com.example.account.customerservice;

import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    boolean doesCustomerExist (Long customerId);

}
