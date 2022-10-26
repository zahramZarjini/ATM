package com.example.account.transaction;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;

@Configuration
@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionModel toTransactionDto
            (TransactionEntity transactionEntity);
}
