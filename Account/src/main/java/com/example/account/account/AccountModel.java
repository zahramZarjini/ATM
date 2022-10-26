package com.example.account.account;

import com.example.account.transaction.TransactionEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class AccountModel {
    @ApiModelProperty(required = true)
    private String accountNumber;

    @ApiModelProperty(required = true)
    private String balance;

    @ApiModelProperty(required = true)
    private List<TransactionEntity> transactions;
}
