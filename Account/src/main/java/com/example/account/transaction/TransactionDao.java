package com.example.account.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public TransactionEntity findById(Long id) {
        return (TransactionEntity) hibernateTemplate.find
                (String.format("from TransactionEntity p where p.id = %d ", id));

    }

    public TransactionEntity save(TransactionEntity transactionEntity) {
        return (TransactionEntity) hibernateTemplate.save(transactionEntity);
    }

    public TransactionEntity findLastTenTransactions(String accountNumber) {
        return (TransactionEntity) hibernateTemplate.find
                (String.format("SELECT * FROM transactions v WHERE v.account_number=:accountNumber ORDER BY v.id desc LIMIT 10", accountNumber));
    }

    public List<TransactionEntity> getAllOfTodaysTransactions(String accountNumber) {
        return (List<TransactionEntity>) hibernateTemplate.find("FROM TransactionEntity t  WHERE t.transactionDateTime = current_date And t.firstAccountNumber.accountNumber = ?");
    }

}

