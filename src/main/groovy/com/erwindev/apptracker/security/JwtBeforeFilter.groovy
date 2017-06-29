package com.erwindev.apptracker.security

import com.erwindev.apptracker.util.TokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by erwinalberto on 6/23/17.
 */
@Component
class JwtBeforeFilter extends OncePerRequestFilter {

    @Autowired
    TokenUtil tokenUtil

    @Override
    void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = tokenUtil.getToken( request)
        if (token) {
            JwtAuthToken jwtToken = new JwtAuthToken(token)
            SecurityContextHolder.getContext().setAuthentication(jwtToken)
        }
        filterChain.doFilter(request, response)
    }
}
