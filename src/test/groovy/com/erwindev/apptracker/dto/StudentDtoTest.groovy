package com.erwindev.apptracker.dto

import static org.junit.Assert.*;

import com.erwindev.apptracker.domain.Student
import org.junit.Test
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
class StudentDtoTest {

    @Test
    void testStudentDtoCreate(){
        Student student = new Student()
        student.firstName = "Julian"
        student.lastName = "Alberto"
        student.email = "jalberto1@email.com"
        student.id = UUID.randomUUID()

        StudentDto dto = StudentDto.create(student)

        assertEquals(student.firstName, dto.firstName)
        assertEquals(student.lastName, dto.lastName)
    }
}
