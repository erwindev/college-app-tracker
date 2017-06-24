package com.erwindev.apptracker.security

import com.erwindev.apptracker.domain.Student
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

/**
 * Created by erwinalberto on 6/23/17.
 */
class JwtAuthenticatedProfile implements Authentication{

    private final Student student

    JwtAuthenticatedProfile(Student student){
        this.student = student
    }

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
    }

    @Override
    Object getCredentials() {
        return null
    }

    @Override
    Object getDetails() {
        return null
    }

    @Override
    Object getPrincipal() {
        return null
    }

    @Override
    boolean isAuthenticated() {
        return false
    }

    @Override
    void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    String getName() {
        return student.email
    }
}
