package com.erwindev.apptracker.service

import com.erwindev.apptracker.dao.StudentDao
import com.erwindev.apptracker.domain.Student

import static org.junit.Assert.*;

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
class StudentServiceTest {
    @Autowired
    StudentService studentService

    @Test
    void testCreateStudent(){

        Student student = new Student()
        student.firstName = "Julian"
        student.lastName = "Alberto"
        student.email = "jalberto1@email.com"
        student.id = UUID.randomUUID()
        student.password = StudentDao.passwordHasher('password')

        studentService.createStudent(student)
        Student studentFromDb = studentService.getStudent(student.id.toString())

        assertEquals(student.id, studentFromDb.id)
    }

    @Test
    void testAuthenticate(){

        Student student = new Student()
        student.firstName = "Julian"
        student.lastName = "Alberto"
        student.email = "jalberto6@email.com"
        student.id = UUID.randomUUID()
        student.password = StudentDao.passwordHasher('password')

        studentService.createStudent(student)
        Student studentFromDb = studentService.authenticate(student.email, "password")

        assertEquals(student.id, studentFromDb.id)

    }
}
