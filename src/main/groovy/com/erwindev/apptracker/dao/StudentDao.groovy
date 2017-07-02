package com.erwindev.apptracker.dao

import com.erwindev.apptracker.domain.Student
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component

/**
 * Created by erwinalberto on 6/21/17.
 */
@Component
@Slf4j
class StudentDao extends AppTrackerBaseDao{

    @Autowired
    AddressDao addressDao

    Student findStudentByEmailPassword(String email, String password){
        Sql sql = new Sql(dataSource)
        def params = [email: email]
        Student student = Student.newInstance(sql.firstRow(params, FIND_STUDENT_BY_EMAIL))
        if(student) {
            if (!BCrypt.checkpw(password, student.password))
                return null
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
        return student
    }

    void updateLastLogin(id){
        Sql sql = new Sql(dataSource)
        def params = [id: id]
        sql.execute(params, UPDATE_STUDENT_LAST_LOGIN)
    }

    String getTableName(){
        "student"
    }

    private final static String passwordHasher(String password){
        BCrypt.hashpw(password, BCrypt.gensalt())
    }

    private final String FIND_STUDENT_BY_EMAIL =
            """select * from ${getTableName()} where email = :email"""

    private final String FIND_STUDENT_BY_ID =
            """select * from ${getTableName()} where id = cast(:id as uuid)"""

    private final String UPDATE_STUDENT_LAST_LOGIN =
            """update ${getTableName()} set last_login = now() where id = :id"""

    static void main(String[] args){
        print(StudentDao.passwordHasher('passwordswc'))
    }

}
