package com.example.customer.customer;

import com.example.customer.jwt.JwtService;
import com.example.customer.login.CustomerLoginRequest;
import com.example.customer.login.CustomerResetPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

@Component
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private JwtService jwtService;


    @Override
    public CustomerEntity getById(Long id) {
        return customerDao.getById(id);
    }

    @Override
    public String login(CustomerLoginRequest customerEntity) {

        CustomerEntity customer = customerDao.findByUserName(customerEntity.getUsername());

        if (customer == null) throw new RuntimeException("User does not exist.");

        if (!customerEntity.getPassword().equals(customer.getPassword()))

            throw new RuntimeException("Password is not correct.");

        return jwtService.createToken(customer.getName());
    }

    @Override
    public CustomerEntity findByUserName(String username) {

        return customerDao.findByUserName(username);
    }

    @Override
    public Integer requestPasswordReset(String username) {

        CustomerEntity customer = customerDao.findByUserName(username);

        customer.setPasswordResetCode(generateRandomCode());

        customerDao.save(customer);

        return customer.getPasswordResetCode();
    }

    @Override
    public void resetPassword(CustomerResetPassword customerResetPassword) throws Exception {

        CustomerEntity customer = customerDao.findByUserName(customerResetPassword.getUsername());

        if (!customer.getPasswordResetCode().equals(customerResetPassword.getResetCode())) throw new Exception("Incorrect password");

        customer.setPassword(customerResetPassword.getNewPassword());

        customerDao.save(customer);
    }

    private Integer generateRandomCode() {
        return 5050;
    }

}
