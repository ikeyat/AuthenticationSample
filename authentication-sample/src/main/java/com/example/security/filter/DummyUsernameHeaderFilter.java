package com.example.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ikeya on 15/06/20.
 */
public class DummyUsernameHeaderFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory
            .getLogger(DummyUsernameHeaderFilter.class);

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest wrappedRequest = new HttpServletRequestWrapper(httpServletRequest) {
            @Override
            public String getHeader(String name) {
                String ret = super.getHeader(name);
                if (SsoSamplePreAuthenticatedFilter.HEADER_NAME.equals(name)
                        && StringUtils.isEmpty(ret)) {
                    logger.debug("dummy header is returned: {}: {}", name, username);
                    return username;
                }
                return ret;
            }
        };
        logger.debug("header is hacked.");
        filterChain.doFilter(wrappedRequest, httpServletResponse);
    }
}