package com.erwindev.apptracker.security

import static org.junit.Assert.*;

import com.erwindev.apptracker.dao.StudentDao
import com.erwindev.apptracker.domain.Student
import com.erwindev.apptracker.service.StudentService
import com.erwindev.apptracker.util.TokenUtil
import org.junit.Test
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
class JwtAuthenticationProviderTest {

    @Autowired
    JwtAuthenticationProvider provider

    @Autowired
    StudentService studentService

    @Autowired
    TokenUtil tokenUtil

    @Test
    void testAuthentication(){
        Student student = new Student()
        student.firstName = "Julian"
        student.lastName = "Alberto"
        student.email = "jalberto1@email.com"
        student.password = StudentDao.passwordHasher("password")
        student.id = UUID.randomUUID()

        studentService.createStudent(student)
        Student studentFromDb = studentService.getStudent(student.id.toString())

        String token = tokenUtil.generateStudentJwt(studentFromDb)
        JwtAuthToken jwt = new JwtAuthToken(token)

        JwtAuthenticatedProfile profile = provider.authenticate(jwt)

        assertEquals(profile.name, student.email)
    }

}
