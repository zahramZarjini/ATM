package com.example.customer.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;


    public CustomerEntity getById(Long id) {
        List<?> result = hibernateTemplate.find(String.format("from CustomerEntity c where c.id = %d ", id));
        return (CustomerEntity) result.get(0);
    }

    public CustomerEntity save(CustomerEntity user) {
        return (CustomerEntity) hibernateTemplate.save(user);
    }

    public CustomerEntity findByUserName(String username) {
        List<?> result = hibernateTemplate.find(String.format("from customers c where c.username = %s ", username));
        return (CustomerEntity) result.get(0);
    }

}
