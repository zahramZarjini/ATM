package com.example.account.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;

    @GetMapping("/find-account-by-id")
    public ResponseEntity<AccountModel> getById(@RequestParam Long id) {
        AccountEntity accountEntity = accountService.getById(id);
        AccountModel account = accountMapper.toAccountDto(accountEntity);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/open-new-account")
    public ResponseEntity<AccountModel> openNewAccount(@RequestBody OpenNewAccountDto open) {
        AccountEntity accountEntity = accountService.openNewAccount(open);
        AccountModel accountModel = accountMapper.toAccountDto(accountEntity);
        return ResponseEntity.ok(accountModel);
    }
    @PostMapping("account-balance")
    public ResponseEntity<AccountModel> accountBalance(Double balance){
        AccountEntity accountEntity = (AccountEntity) accountService.accountBalance(balance);
        AccountModel accountModel = accountMapper.toAccountDto(accountEntity);
        return ResponseEntity.ok(accountModel);
    }

    @PostMapping("/show-all-accounts")
    public List<AccountEntity> showAllAccountsOfCustomer(@RequestBody String userName) {
        return accountService.showAllAccountsOfCustomer(userName);
    }

    @PostMapping("/find-all-accounts")
    public ResponseEntity<AccountModel> findAccountByAccountNumber(@RequestBody String accountNumber) {
        AccountEntity accountEntity = accountService.findAccountByAccountNumber(accountNumber);
        AccountModel accountModel = accountMapper.toAccountDto(accountEntity);
        return ResponseEntity.ok(accountModel);
    }
}