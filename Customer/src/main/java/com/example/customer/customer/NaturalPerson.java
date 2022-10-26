package com.example.customer.customer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class NaturalPerson extends CustomerEntity {

    @Column(name = "first_name")
    private String firstNameOfPerson;

    @Column(name = "national_code")
    private String nationalCode;
}
