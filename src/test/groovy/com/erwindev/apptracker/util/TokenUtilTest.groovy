package com.erwindev.apptracker.util

import org.junit.Test

import static org.junit.Assert.*;

import com.erwindev.apptracker.domain.Student
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by erwinalberto on 7/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class TokenUtilTest {

    @Autowired
    TokenUtil tokenUtil

    @Test
    void testJwtGeneration(){
        Student student = new Student()
        student.firstName = "Julian"
        student.lastName = "Alberto"
        student.email = "jalberto1@email.com"
        student.id = UUID.randomUUID()

        String jwt = tokenUtil.generateStudentJwt(student)
        String id = tokenUtil.decodeStudentJwt(jwt)

        assertEquals(student.id.toString(), id)
    }
}
