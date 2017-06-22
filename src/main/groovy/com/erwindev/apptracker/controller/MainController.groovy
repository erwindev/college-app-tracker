package com.erwindev.apptracker.controller

import com.erwindev.apptracker.exception.ApplicationException
import com.erwindev.apptracker.service.CollegeTrackerService
import com.erwindev.apptracker.service.StudentService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
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

    @ApiOperation(value = "Returns 'Application Tracker'",response = String.class)
    @RequestMapping(method=RequestMethod.GET,value="/whoami", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> test() {
        return new ResponseEntity<String>('{"whoami":"Application Tracker"}', OK)
    }

    @ApiOperation(value = "Returns List of Colleges",response = Iterable.class)
    @RequestMapping(method=RequestMethod.GET,value="/colleges", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getAllColleges() {
        final def colleges = collegeTrackerService.getAllColleges()
        new ResponseEntity(colleges, OK)
    }

    @ApiOperation(value = "Authenticates a Student",response = String.class)
    @RequestMapping(method=RequestMethod.POST,value="/login", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody LoginRequestData loginRequestData) {
        try {
            final def studentInfo = studentService.authenticate(loginRequestData.email, loginRequestData.password)
            new ResponseEntity(new LoginResponseData(token: studentService.generateStudentJwt(studentInfo)), OK)
        }
        catch(ApplicationException e){
            new ResponseEntity(NOT_FOUND)
        }

    }
}

class LoginRequestData{
    String email
    String password
}

class LoginResponseData{
    String token
}
