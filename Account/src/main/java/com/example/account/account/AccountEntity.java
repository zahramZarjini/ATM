package com.example.account.account;

import com.example.account.transaction.TransactionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private Double balance;

    @OneToMany
    private List<TransactionEntity> transactions;

    @Column(name = "customer_id")
    private Long customerId;
}
