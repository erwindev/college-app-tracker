package com.erwindev.apptracker.security

import com.erwindev.apptracker.util.TokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

import javax.servlet.*
import javax.servlet.http.HttpServletRequest

/**
 * Created by erwinalberto on 6/23/17.
 */
@Component
class JwtFilter implements Filter {

    @Autowired
    TokenUtil tokenUtil

    @Override
    void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = tokenUtil.getToken((HttpServletRequest) request)
        if (token) {
            JwtAuthToken jwtToken = new JwtAuthToken(token)
            SecurityContextHolder.getContext().setAuthentication(jwtToken)
        }
        chain.doFilter(request, response)
    }

    @Override
    void destroy() {

    }
}
