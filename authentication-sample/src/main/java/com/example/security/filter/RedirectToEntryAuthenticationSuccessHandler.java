package com.example.security.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ikeya on 16/01/04.
 */
public class RedirectToEntryAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(RedirectToEntryAuthenticationSuccessHandler.class);
    private String topUri;
    private String entryUrl;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private static final UrlPathHelper URL_PATH_HELPER = new UrlPathHelper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String requestUri = URL_PATH_HELPER.getOriginatingRequestUri(request);
        String topUriWithContextPath = URL_PATH_HELPER.getContextPath(request) + topUri;

        if (topUriWithContextPath.equals(requestUri)) {
            // Do nothing for requests to top page
            return;
        }

        // Otherwise redirect to another entry page.
        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + entryUrl);
            return;
        }

        logger.debug("A request " + requestUri + " without session is detected. Redirect to " + entryUrl);
        redirectStrategy.sendRedirect(request, response, entryUrl);
    }

    public void setTopUri(String topUri) {
        this.topUri = topUri;
    }

    public void setEntryUrl(String entryUrl) {
        this.entryUrl = entryUrl;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
}
