package com.example.security.domain.service.userdetails;

import com.example.security.domain.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class SsoSampleUserDetailsService
        implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private static final Logger logger = LoggerFactory
            .getLogger(SsoSampleUserDetailsService.class);

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) {
        Account account = new Account();

        account.setUsername((String) token.getPrincipal());
        account.setPassword((String) token.getCredentials());

        logger.debug("account is loaded: {}", account);

        return new SampleUserDetails(account);
    }
}