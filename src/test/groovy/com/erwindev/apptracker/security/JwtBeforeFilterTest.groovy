package com.erwindev.apptracker.security

import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder

import static org.junit.Assert.*;

import com.erwindev.apptracker.domain.Student
import com.erwindev.apptracker.util.TokenUtil
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockFilterChain
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by erwinalberto on 7/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class JwtBeforeFilterTest {

    @Autowired
    TokenUtil tokenUtil

    @Autowired
    JwtBeforeFilter filter

    @Test
    void testJwtBeforeFilter(){

        Student student = new Student()
        student.firstName = "Julian"
        student.lastName = "Alberto"
        student.email = "jalberto1@email.com"
        student.id = UUID.randomUUID()

        String jwt = tokenUtil.generateStudentJwt(student)

        MockFilterChain mockChain = new MockFilterChain()
        MockHttpServletRequest mockRequest = new MockHttpServletRequest()
        mockRequest.addHeader("Authorization", "Bearer " + jwt)
        MockHttpServletResponse mockResponse = new MockHttpServletResponse()
        filter.doFilterInternal(mockRequest, mockResponse, mockChain)

        assertEquals(mockResponse.status, HttpStatus.OK.value())
        assertEquals(SecurityContextHolder.getContext().authentication.name, jwt)
    }
}
