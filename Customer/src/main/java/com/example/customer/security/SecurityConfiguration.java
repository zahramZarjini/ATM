package com.example.customer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String DEFUALT_PASSWORD = new BCryptPasswordEncoder().encode("1401");

    @Autowired
    public void configurationGlobal(AuthenticationManagerBuilder authentication) throws Exception {
        authentication.inMemoryAuthentication().withUser("customer").password(DEFUALT_PASSWORD).roles("CUSTOMER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll();
    }
}

