package com.example.security.domain.service.userdetails;

import com.example.security.domain.model.Account;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SampleUserDetailsService implements UserDetailsService { // (1)

    private static final String CORRECT_USERNAME = "demo";
    private static final String CORRECT_PASSWORD = "demo";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: Sample dummy implementation
        if (CORRECT_USERNAME.equals(username)) {
            Account account = new Account();

            account.setUsername(CORRECT_USERNAME);
            account.setPassword(CORRECT_PASSWORD);

            return new SampleUserDetails(account);
        } else {
            throw new UsernameNotFoundException("user not found"); // (5)
        }
    }

}