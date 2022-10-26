package com.example.account.transaction;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class TransactionModel {

    @ApiModelProperty(required = true)
    private String transactionType;

    @ApiModelProperty(required = true)
    private String transactionDateTime;

    @ApiModelProperty(required = true)
    private List<TransactionEntity> transactions;

    @ApiModelProperty(required = true)
    private Double amount;
}
