package com.example.account.account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public AccountEntity getById(Long id) {
        return hibernateTemplate.get(AccountEntity.class, id);
    }

    public AccountEntity save(AccountEntity accountEntity) {
        hibernateTemplate.save(accountEntity);
        return accountEntity;
    }
    public List<AccountEntity> accountBalance(Double accountId) {
        return (List<AccountEntity>) hibernateTemplate.find
                ("from AccountEntity a where a.id = :accountId", accountId);

    }
    public List<AccountEntity> showAllAccountsOfCustomer(String userName) {
        return (List<AccountEntity>) hibernateTemplate.find(String.format("FROM accounts a WHERE a.userName='%s' ", userName));
    }

    public AccountEntity findAccountByAccountNumber(String accountNumber) {
        return (AccountEntity) hibernateTemplate.find(String.format("FROM accounts a WHERE a.userName='%s' ", accountNumber));
    }
}