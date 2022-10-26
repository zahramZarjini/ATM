package com.example.customer.customer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerModel {

    @ApiModelProperty(required = true)
    private String username;

    @ApiModelProperty(required = true)
    private String password;

    @ApiModelProperty(required = true)
    private String address;

    @ApiModelProperty(required = true)
    private String phoneNumber;
}
