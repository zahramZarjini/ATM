package com.example.customer.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerResetPassword {

    private String username;
    private String newPassword;
    private Integer resetCode;

}
