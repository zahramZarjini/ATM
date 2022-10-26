package com.example.account.account;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;
@Configuration
@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountModel toAccountDto(AccountEntity account);
}
