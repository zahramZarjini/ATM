package com.example.customer.customer;

import com.example.customer.login.CustomerLoginRequest;
import com.example.customer.login.CustomerResetPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping("/get/{id}")
    public ResponseEntity<CustomerModel> getById(@PathVariable Long id) {
        CustomerEntity customer = customerService.getById(id);
        CustomerModel customerModel = customerMapper.toCustomerDto(customer);
        return ResponseEntity.ok(customerModel);
    }

    @PostMapping("authorize-to-login")
    public String login(@RequestBody CustomerLoginRequest request) {
        return customerService.login(request);
    }

    @PostMapping("find-by-user-name")
    public ResponseEntity<CustomerModel> findByUserName(String userName) {
        CustomerEntity customer = customerService.findByUserName(userName);
        CustomerModel customerModel = customerMapper.toCustomerDto(customer);
        return ResponseEntity.ok(customerModel);
    }

    @PostMapping("request-reset-password")
    public Integer requestPasswordReset(String username) {
        return customerService.requestPasswordReset(username);
    }

    @PostMapping("reseting-password")
    public void resetPassword(@RequestBody CustomerResetPassword customerResetPassword) throws Exception {
        customerService.resetPassword(customerResetPassword);
    }

    @GetMapping("/is-the-customer-exists")
    public boolean doesCustomerExist(@RequestParam("customerId") Long customerId) {
        return customerService.getById(customerId) != null;
    }

}
