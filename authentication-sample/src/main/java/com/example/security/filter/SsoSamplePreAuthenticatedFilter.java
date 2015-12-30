package com.example.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * Use {@link RequestHeaderAuthenticationFilter} alternatively.
 * Created by ikeya on 15/06/20.
 */
@Deprecated
public class SsoSamplePreAuthenticatedFilter extends AbstractPreAuthenticatedProcessingFilter {

    private static final Logger logger = LoggerFactory
            .getLogger(SsoSamplePreAuthenticatedFilter.class);

    public static final String HEADER_NAME = "username";

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        String username = request.getHeader(HEADER_NAME);
        logger.debug("header detected: {}: {}", HEADER_NAME, username);
        return username;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "";
    }

}
