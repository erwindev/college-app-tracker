package com.erwindev.apptracker.dao

import org.junit.Before
import org.springframework.test.context.TestPropertySource

import static org.junit.Assert.*;

import com.erwindev.apptracker.domain.Student
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by erwinalberto on 7/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class StudentDaoTest {
    @Autowired
    StudentDao studentDao

    @Test
    void testCreateStudent(){
        Student student = new Student()
        student.firstName = "Julian"
        student.lastName = "Alberto"
        student.email = "jalberto1@email.com"
        student.id = UUID.randomUUID()
        student.password = StudentDao.passwordHasher('password')

        studentDao.create(student)

        Student studentFromDb = studentDao.findStudent(student.id.toString())

        assertEquals(studentFromDb.id, student.id)
        assertEquals(studentFromDb.email, student.email)
    }

    @Test
    void testFindStudentByEmail(){
        Student student = new Student()
        student.firstName = "Julian"
        student.lastName = "Alberto"
        student.email = "jalberto2@email.com"
        student.id = UUID.randomUUID()
        student.password = StudentDao.passwordHasher('password')

        studentDao.create(student)
        Student studentFromDb = studentDao.findStudentByEmail("jalberto2@email.com")

        assertEquals(studentFromDb.id, student.id)
    }

    @Test
    void testFindStudentByEmailPassword(){
        Student student = new Student()
        student.firstName = "Julian"
        student.lastName = "Alberto"
        student.email = "jalberto3@email.com"
        student.id = UUID.randomUUID()
        student.password = StudentDao.passwordHasher('password')

        studentDao.create(student)
        Student studentFromDb = studentDao.findStudentByEmailPassword("jalberto3@email.com", "password")

        assertEquals(studentFromDb.id, student.id)

    }

    @Test
    void testUpdateLastLogin(){
        Student student = new Student()
        student.firstName = "Julian"
        student.lastName = "Alberto"
        student.email = "jalberto4@email.com"
        student.id = UUID.randomUUID()
        student.password = StudentDao.passwordHasher('password')

        studentDao.create(student)
        Student studentFromDb = studentDao.findStudent(student.id.toString())
        def firstLastLoginDate = studentFromDb.lastLogin
        studentDao.updateLastLogin(studentFromDb.id)
        studentFromDb = studentDao.findStudent(student.id.toString())
        studentDao.findStudent(studentFromDb.id.toString())
        def secondLastLoginDate = studentFromDb.lastLogin

        print(firstLastLoginDate)
        print(secondLastLoginDate)

        assertNotEquals(firstLastLoginDate, secondLastLoginDate)

    }
}
