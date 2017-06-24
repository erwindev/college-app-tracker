package com.erwindev.apptracker.security

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN

/**
 * Created by erwinalberto on 6/23/17.
 */
@Component
class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

    @Override
    void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(SC_FORBIDDEN)
        response.setContentType(MediaType.APPLICATION_JSON_VALUE)

        String message
        if(authException.getCause() != null) {
            message = authException.getCause().getMessage()
        } else {
            message = authException.getMessage()
        }
        byte[] body = new ObjectMapper()
                .writeValueAsBytes(Collections.singletonMap("error", message))
        response.getOutputStream().write(body)
    }
}
