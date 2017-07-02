package com.erwindev.apptracker.security

import com.erwindev.apptracker.domain.Student
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

/**
 * Created by erwinalberto on 7/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class JwtAuthenticatedProfileTest {

    @Test
    void testJwtAuthToken(){
        Student student = new Student()
        student.email = "jalberto1@email.com"

        JwtAuthenticatedProfile jwtAuthenticatedProfile = new JwtAuthenticatedProfile(student)

        assertEquals(jwtAuthenticatedProfile.name, student.email)
        assertEquals(jwtAuthenticatedProfile.principal, student)
        assertEquals(jwtAuthenticatedProfile.details, null)
        assertEquals(jwtAuthenticatedProfile.credentials, null)
        assertEquals(jwtAuthenticatedProfile.authorities, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
    }
}
