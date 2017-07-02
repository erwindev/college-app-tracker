package com.erwindev.apptracker.controller

import com.erwindev.apptracker.domain.Student
import com.erwindev.apptracker.dto.StudentDto
import com.erwindev.apptracker.exception.ApplicationException
import com.erwindev.apptracker.security.JwtAuthenticatedProfile
import com.erwindev.apptracker.service.CollegeTrackerService
import com.erwindev.apptracker.service.StudentService
import com.erwindev.apptracker.util.TokenUtil
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.RequestBody

import static org.springframework.http.HttpStatus.*

import com.erwindev.apptracker.util.ApplicationSettings
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

import groovy.util.logging.Slf4j

/**
 * Created by erwinalberto on 6/21/17.
 */
@RestController
@RequestMapping(value="/api/apptracker/v1", produces=MediaType.APPLICATION_JSON_VALUE)
@Api(value = "apptracker", description = "App Tracker API")
@Slf4j
class MainController {

    @Autowired
    ApplicationSettings applicationSettings

    @Autowired
    CollegeTrackerService collegeTrackerService

    @Autowired
    StudentService studentService

    @Autowired
    TokenUtil tokenUtil

    @ApiOperation(value = "Returns 'Application Tracker'",response = String.class)
    @RequestMapping(method=RequestMethod.GET,value="/whoami", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<String> whoami() {
        return new ResponseEntity<String>('{"whoami":"Application Tracker"}', OK)
    }

    @ApiOperation(value = "Returns List of Colleges",response = Iterable.class)
    @RequestMapping(method=RequestMethod.GET,value="/colleges", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<String> getAllColleges() {
        final def colleges = collegeTrackerService.getAllColleges()
        new ResponseEntity(new CollegeListResponseData(colleges: colleges, token: refreshToken()), OK)
    }

    @ApiOperation(value = "Authenticates a Student",response = String.class)
    @RequestMapping(method=RequestMethod.POST,value="/login", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<String> login(@RequestBody LoginRequestData loginRequestData) {
        try {
            final def studentInfo = studentService.authenticate(loginRequestData.email, loginRequestData.password)
            def authToken = tokenUtil.generateStudentJwt(studentInfo)
            new ResponseEntity(new LoginResponseData(status: 'SUCCESSFUL',
                    token: authToken, student: StudentDto.create(studentInfo)), OK)
        }
        catch(ApplicationException e){
            new ResponseEntity(new ResultResponseData(status: 'ERROR', message: e.getMessage()), OK)
        }
    }

    private String refreshToken(){
        JwtAuthenticatedProfile authProfile = (JwtAuthenticatedProfile)SecurityContextHolder.getContext().getAuthentication()
        return tokenUtil.generateStudentJwt(authProfile.principal)
    }
}

/*
Request Data
 */
class LoginRequestData{
    String email
    String password
}


/*
Response Data
 */
class TokenResponseData {
    String token
}

class ResultResponseData extends TokenResponseData{
    String status
    String message
}

class LoginResponseData extends ResultResponseData{
    StudentDto student
}

class CollegeListResponseData extends ResultResponseData{
    List colleges
}

