package com.example.account.transaction;

import com.example.account.types.CustomerType;
import com.example.account.types.PayaNormalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionMapper transactionMapper;


    @GetMapping("/new-user")
    public ResponseEntity<TransactionModel> getById(@RequestParam Long id) {
        TransactionEntity transactionEntity = transactionService.getById(id);
        TransactionModel transaction = transactionMapper.toTransactionDto(transactionEntity);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping("/card-by-Card")
    public ResponseEntity<Void> cardByCard(String firstAccountId, String secondAccountId, Double amount, PayaNormalType payaNormalType, CustomerType customerType ) throws Exception {
        transactionService.cardByCard(firstAccountId, secondAccountId, amount, payaNormalType, customerType);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("last-ten-transactions")
    public List<TransactionModel> returnLastTenTransactions(String accountNumber){
        List<TransactionModel> transactions = transactionService.returnLastTenTransactions(accountNumber);
        return transactions;
    }

    @PostMapping("paya-limited-for-legal-customers")
    public boolean isAuthorizedForPaya(String firstAccountId, Double amount, PayaNormalType payaNormalType, CustomerType customerType) throws Exception {
        return transactionService.isAuthorizedForPaya(firstAccountId, amount, payaNormalType, customerType);

    }
    @PostMapping("rate-transactions")
    public Integer ratingForEachTransactions(PayaNormalType payaNormalType, CustomerType customerType, Double amount) {
        return transactionService.ratingForEachTransactions(payaNormalType, customerType, amount);
    }
}