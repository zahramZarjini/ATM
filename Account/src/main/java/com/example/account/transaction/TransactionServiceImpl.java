package com.example.account.transaction;

import com.example.account.account.AccountDao;
import com.example.account.account.AccountEntity;
import com.example.account.types.CustomerType;
import com.example.account.types.PayaNormalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private TransactionDao transactionDao;

    @Override
    public TransactionEntity getById(Long id) {
        return transactionDao.findById(id);
    }

    @Override
    public void cardByCard(String firstAccountId, String secondAccountId, Double amount, PayaNormalType payaNormalType, CustomerType customerType) throws Exception {
        if (customerType.equals(CustomerType.NATURAL)) {
            AccountEntity firstAccount = accountDao.findAccountByAccountNumber(firstAccountId);
            AccountEntity secondAccount = accountDao.findAccountByAccountNumber(secondAccountId);
            if (firstAccount == null || secondAccount == null)
                throw new IOException("Not found an account!!!");

            if (!isAuthorizedForCardByCard(firstAccountId, amount, payaNormalType, customerType))
                throw new Exception("Error in transactions!!!");

            TransactionEntity transaction = new TransactionEntity();
            transaction.setTransactionDateTime(LocalDateTime.now());
            transaction.setAmount(amount);
            transaction.setFirstAccountNumber(firstAccount);
            transaction.setSecondAccountNumber(secondAccount);
            firstAccount.setBalance(firstAccount.getBalance() - amount);
            secondAccount.setBalance(secondAccount.getBalance() + amount);

            transactionDao.save(transaction);
        }
    }

    @Override
    public List<TransactionModel> returnLastTenTransactions(String accountNumber) {
        return (List<TransactionModel>) transactionDao.findLastTenTransactions(accountNumber);
    }

    public boolean isAuthorizedForCardByCard(String firstAccountId, Double amount, PayaNormalType payaNormalType, CustomerType customerType) {
        List<AccountEntity> firstAccount = accountDao.showAllAccountsOfCustomer(firstAccountId);
        if (customerType.equals(CustomerType.NATURAL)) {
            Double sum = 0D;
            for (TransactionEntity transaction : transactionDao.getAllOfTodaysTransactions(firstAccountId)) {
                sum = sum + transaction.getAmount();
                if (customerType.equals(CustomerType.NATURAL)) {
                    if (sum > 50.000000) return false;
                } else if (customerType.equals(CustomerType.LEGAL)) {
                    if (sum > 200.000000) return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isAuthorizedForPaya(String firstAccountId, Double amount, PayaNormalType payaNormalType, CustomerType customerType) throws Exception {

        accountDao.findAccountByAccountNumber(firstAccountId);
        if (payaNormalType.equals(PayaNormalType.PAYA)) {
            Double sum = 0D;
            for (TransactionEntity transaction : transactionDao.getAllOfTodaysTransactions(firstAccountId)) {
                sum = sum + transaction.getAmount();
                if (customerType.equals(CustomerType.NATURAL)) {
                    if (sum > 10.000000) return false;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer ratingForEachTransactions(PayaNormalType payaNormalType, CustomerType customerType, Double amount) {
        final Double rateOfPayaForNatural = 2500D;
        final Double rateOfNormalForNatural = 2000D;
        final Double rateOfPayaForLegal = 1500D;
        final Double rateOfNormalForLegal = 1000D;


        int ratingCount =0;
        double count = 0;


        if (customerType.equals(CustomerType.NATURAL)) {
            if (payaNormalType.equals(PayaNormalType.PAYA)) {
                count = amount / rateOfPayaForNatural;
                ratingCount = (int) (count * 5);
            } else if (payaNormalType.equals(PayaNormalType.NORMAL)) {
                count = amount / rateOfNormalForNatural;
                ratingCount = (int) (count * 4);
            }


        else if (customerType.equals(CustomerType.LEGAL)) {
                if (payaNormalType.equals(PayaNormalType.PAYA)) {
                    count = amount / rateOfPayaForLegal;
                    ratingCount = (int) (count * 3);
                } else if (payaNormalType.equals(PayaNormalType.NORMAL)) {
                    count = amount / rateOfNormalForLegal;
                    ratingCount = (int) (count * 2);
                }
            }
        }
        return ratingCount;
    }
}