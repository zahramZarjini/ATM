package com.example.account.transaction;

import com.example.account.types.CustomerType;
import com.example.account.types.PayaNormalType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    TransactionEntity getById(Long id);

    void cardByCard(String firstAccountId, String secondAccountId, Double amount, PayaNormalType payaOrSimple, CustomerType customerType) throws Exception;

    List<TransactionModel> returnLastTenTransactions(String accountNumber);

    boolean isAuthorizedForPaya(String firstAccountId, Double amount, PayaNormalType payaNormalType, CustomerType customerType) throws Exception;

    Integer ratingForEachTransactions(PayaNormalType payaNormalType, CustomerType customerType, Double rate);

}
