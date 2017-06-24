package com.erwindev.apptracker.service

import com.erwindev.apptracker.dao.StudentDao
import com.erwindev.apptracker.domain.Student
import com.erwindev.apptracker.exception.ApplicationException
import com.erwindev.apptracker.util.ApplicationSettings
import groovy.time.TimeCategory
import groovy.util.logging.Slf4j
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by erwinalberto on 6/21/17.
 */
@Service
@Slf4j
class StudentService{

    @Autowired
    StudentDao studentDao

    @Autowired
    ApplicationSettings applicationSettings

    Student authenticate(String email, String password) throws ApplicationException{
        def student = studentDao.findStudentByEmailPassword(email, password)
        if (!student){
            throw new ApplicationException('Incorrect Email or Password')
        }
        studentDao.updateLastLogin(student.id)
        return student
    }

    Student getStudent(String id) throws ApplicationException{
        try {
            def student = studentDao.findStudent(id)
            if (!student){
                throw new ApplicationException('Student Not Found')
            }
            return student
        }
        catch(JwtException e){
            throw new ApplicationException(e)
        }
    }
}
