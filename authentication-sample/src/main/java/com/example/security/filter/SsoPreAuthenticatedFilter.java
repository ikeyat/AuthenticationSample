package com.example.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ikeya on 15/06/20.
 */
public class SsoPreAuthenticatedFilter extends RequestHeaderAuthenticationFilter {

    private static final Logger logger = LoggerFactory
            .getLogger(SsoPreAuthenticatedFilter.class);

    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) {
        super.successfulAuthentication(request, response, authResult);
        try {
            authenticationSuccessHandler.onAuthenticationSuccess(request, response, authResult);
        } catch (IOException | ServletException e) {
            // Wrap exception because of interface limitations
            throw new AuthenticationServiceException("Failed to call authentication success handler.", e);
        }
    }

    public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }
}
