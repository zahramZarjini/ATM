package com.example.account.transaction;

import com.example.account.account.AccountEntity;
import com.example.account.types.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDateTime;

    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "amount")
    private Double amount;

    @ManyToOne
    private AccountEntity firstAccountNumber;

    @ManyToOne
    private AccountEntity secondAccountNumber;
}
