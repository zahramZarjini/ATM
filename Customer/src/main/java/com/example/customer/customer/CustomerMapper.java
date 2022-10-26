package com.example.customer.customer;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerModel toCustomerDto(CustomerEntity user);

}
