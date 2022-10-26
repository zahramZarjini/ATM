package com.example.account.account;
import com.example.account.customerservice.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private CustomerService customerService;

    @Override
    public AccountEntity getById(Long id) {
        return accountDao.getById(id);
    }

    @Override
    public List<AccountEntity> showAllAccountsOfCustomer(String userName) {
        return accountDao.showAllAccountsOfCustomer(String.valueOf(userName));
    }

    @Override
    public AccountEntity openNewAccount(OpenNewAccountDto openNewAccount) {
        boolean exists = customerService.doesCustomerExist(openNewAccount.customerId);
        if(!exists) throw new RuntimeException();
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(openNewAccount.customerId);
        accountEntity.setBalance(openNewAccount.balance);
        accountEntity.setCustomerId(openNewAccount.customerId);
        return accountDao.save(accountEntity);
    }

    @Override
    public List<AccountEntity> accountBalance(Double balance) {
        return accountDao.accountBalance(balance);
    }

    @Override
    public AccountEntity findAccountByAccountNumber(String accountNumber) {
        return accountDao.findAccountByAccountNumber(accountNumber);
    }
}
