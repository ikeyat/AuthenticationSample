package com.example.security.domain.service.userdetails;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.example.security.domain.model.Account;

public class SampleUserDetails extends User {
    private static final long serialVersionUID = 1L;

    private final Account account;

    public SampleUserDetails(Account account) {
        super(account.getUsername(), account.getPassword(), AuthorityUtils
                .createAuthorityList("ROLE_USER"));
        this.account = account;
    }

    public Account getAccount() { // (5)
        return account;
    }

}