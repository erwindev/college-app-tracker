package com.erwindev.apptracker.dao

import com.erwindev.apptracker.domain.Student
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component

import javax.sql.DataSource

/**
 * Created by erwinalberto on 6/21/17.
 */
@Component
@Slf4j
class StudentDao{
    @Autowired
    DataSource dataSource

    @Autowired
    AddressDao addressDao

    Student findStudentByEmailPassword(String email, String password){
        Sql sql = new Sql(dataSource)
        def params = [email: email]
        Student student = Student.newInstance(sql.firstRow(params, FIND_STUDENT_BY_EMAIL))
        if(student) {
            if (BCrypt.checkpw(password, student.password))
                student.address = addressDao.getAddress(student.id, 1002)
            else
                student = null
        }
        return student
    }

    Student findStudentByEmail(String email){
        Sql sql = new Sql(dataSource)
        def params = [email: email]
        Student student = Student.newInstance(sql.firstRow(params, FIND_STUDENT_BY_EMAIL))
        return student
    }


    Student findStudent(String id){
        Sql sql = new Sql(dataSource)
        def params = [id: id]
        Student student = Student.newInstance(sql.firstRow(params, FIND_STUDENT_BY_ID))
        if(student) {
            student.address = addressDao.getAddress(student.id, 1002)
        }
        return student
    }

    void updateLastLogin(id){
        Sql sql = new Sql(dataSource)
        def params = [id: id]
        sql.execute(params, UPDATE_STUDENT_LAST_LOGIN)
    }

    private final static String passwordHasher(String password){
        BCrypt.hashpw(password, BCrypt.gensalt())
    }

    private final String FIND_STUDENT_BY_EMAIL =
            'select * from tracker.student where email = :email'

    private final String FIND_STUDENT_BY_ID =
            'select * from tracker.student where id = cast(:id as uuid)'

    private final String UPDATE_STUDENT_LAST_LOGIN =
            'update tracker.student set last_login = now() where id = :id'

    static void main(String[] args){
        print(StudentDao.passwordHasher('passwordswc'))
    }

}
