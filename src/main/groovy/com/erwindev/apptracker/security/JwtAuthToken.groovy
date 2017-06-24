package com.erwindev.apptracker.security

import org.springframework.security.access.method.P
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

/**
 * Created by erwinalberto on 6/23/17.
 */
class JwtAuthToken implements Authentication{

    final String token

    JwtAuthToken(String token){
        this.token = token
    }

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        return null
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
        return null
    }
}
