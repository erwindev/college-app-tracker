package com.erwindev.apptracker.security

import com.erwindev.apptracker.domain.Student
import com.erwindev.apptracker.service.StudentService
import com.erwindev.apptracker.util.TokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Component

/**
 * Created by erwinalberto on 6/23/17.
 */
@Component
class JwtAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    TokenUtil tokenUtil

    @Autowired
    StudentService studentService

    @Override
    Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            String token = authentication.name
            Student student = studentService.getStudent(tokenUtil.decodeStudentJwt(token))
            JwtAuthenticatedProfile authProfile = new JwtAuthenticatedProfile(student)
            return authProfile
        }
        catch (Exception e){
            throw new AuthenticationCredentialsNotFoundException("Failed to verify token", e)
        }
    }

    @Override
    boolean supports(Class<?> authentication) {
        return JwtAuthToken.class.equals(authentication);
    }
}
