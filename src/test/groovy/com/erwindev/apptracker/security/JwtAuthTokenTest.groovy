package com.erwindev.apptracker.security

import org.junit.Test

import static org.junit.Assert.*;

import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by erwinalberto on 7/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class JwtAuthTokenTest {

    @Test
    void testJwtAuthToken(){
        JwtAuthToken jwtAuthToken = new JwtAuthToken("token")

        assertEquals(jwtAuthToken.name, "token")
        assertEquals(jwtAuthToken.principal, null)
        assertEquals(jwtAuthToken.details, null)
        assertEquals(jwtAuthToken.credentials, null)
        assertEquals(jwtAuthToken.authorities, null)
    }
}
