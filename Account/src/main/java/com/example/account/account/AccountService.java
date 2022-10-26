package com.example.account.account;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    AccountEntity getById(Long id);

    List<AccountEntity> showAllAccountsOfCustomer(String userName);

    AccountEntity openNewAccount(OpenNewAccountDto openNewAccount);

    AccountEntity findAccountByAccountNumber(String accountNumber);

    List<AccountEntity> accountBalance(Double balance);
}
