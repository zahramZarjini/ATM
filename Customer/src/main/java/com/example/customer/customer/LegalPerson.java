package com.example.customer.customer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class LegalPerson extends CustomerEntity {

    @Column(name = "postal_code")
    private Long postalCode;
}
