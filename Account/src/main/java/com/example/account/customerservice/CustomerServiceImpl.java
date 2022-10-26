package com.example.account.customerservice;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class CustomerServiceImpl implements CustomerService {

    RestTemplate restTemplate;

    @Override
    public boolean doesCustomerExist(Long customerId) {
        return restTemplate.getForEntity
                ("http://Customer/exists?customerId="
                        + customerId, Boolean.class).getBody();
    }
}
