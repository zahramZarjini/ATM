package com.example.customer.customer;

import com.example.customer.login.CustomerLoginRequest;
import com.example.customer.login.CustomerResetPassword;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    CustomerEntity getById(Long id);

    String login(CustomerLoginRequest userEntity);

    CustomerEntity findByUserName(String username);

    Integer requestPasswordReset(String username);

    void resetPassword(CustomerResetPassword customerResetPassword) throws Exception;

}
