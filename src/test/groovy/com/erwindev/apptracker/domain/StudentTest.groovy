package com.erwindev.apptracker.domain

import groovy.sql.GroovyRowResult
import org.springframework.test.context.TestPropertySource

import static org.junit.Assert.*;

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by erwinalberto on 7/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class StudentTest {
    @Test
    void testConvertStudentToMap(){
        Student student = new Student()
        student.firstName = "Julian"
        student.lastName = "Alberto"
        student.email = "jalberto@email.com"
        student.id = UUID.randomUUID()
        student.password = "mypassword"

        Map studentMap = student.asMap()

        assertEquals(studentMap.containsKey("address"), false)
        assertEquals(studentMap.containsKey("first_name"), true)
        assertEquals(studentMap.get("first_name"), "Julian")
        assertNotEquals(studentMap.get("first_name"), "Julianx")
    }

    @Test
    void testStudentInstaceCreation(){
        Student student = new Student()
        student.firstName = "Julian"
        student.lastName = "Alberto"
        student.email = "jalberto@email.com"
        student.id = UUID.randomUUID()
        student.password = "mypassword"
        student.created = new Date()
        student.lastModified = new Date()
        student.lastLogin = new Date()

        Map studentMap = student.asMap()

        GroovyRowResult row = new GroovyRowResult(studentMap)
        Student studentFromRow = Student.newInstance(row)

        assertEquals(studentFromRow.id, student.id)

    }
}
