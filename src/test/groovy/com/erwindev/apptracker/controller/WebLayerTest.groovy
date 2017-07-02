package com.erwindev.apptracker.controller

import com.erwindev.apptracker.domain.Student
import com.erwindev.apptracker.security.JwtAuthenticatedProfile
import groovy.json.JsonOutput
import org.springframework.http.MediaType
import org.springframework.security.core.context.SecurityContextHolder

import static org.junit.Assert.*;

import com.erwindev.apptracker.service.CollegeTrackerService
import com.erwindev.apptracker.service.StudentService
import com.erwindev.apptracker.util.ApplicationSettings
import com.erwindev.apptracker.util.TokenUtil
import groovy.json.JsonSlurper
import org.junit.Before
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.web.FilterChainProxy
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

/**
 * Created by erwinalberto on 7/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@TestPropertySource(locations="classpath:application-test.properties")
class WebLayerTest {
    MockMvc mockMvc

    @Autowired
    private ApplicationSettings applicationSettings

    @Autowired
    private CollegeTrackerService collegeTrackerService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    FilterChainProxy springSecurityFilterChain;

    @Autowired
    private WebApplicationContext context;

    @Before
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
        Student student = new Student()
        student.firstName = "Julian"
        student.lastName = "Alberto"
        student.email = "jalberto1@email.com"
        student.id = UUID.randomUUID()

        JwtAuthenticatedProfile jwtAuthProfile = new JwtAuthenticatedProfile(student)
        SecurityContextHolder.getContext().setAuthentication(jwtAuthProfile);
    }

    @Test
    void testGetColleges(){
        MvcResult mvcResult = this.mockMvc.perform(get("/api/apptracker/v1/colleges"))
                .andExpect(status().isOk()).andReturn()
        String content = mvcResult.response.contentAsString

        def jsonSlurper = new JsonSlurper()
        def object = jsonSlurper.parseText(content)

        assertEquals(object.colleges.size(), 3)
    }

    @Test
    void testReturnWhoAmI(){
        MvcResult mvcResult = this.mockMvc.perform(get("/api/apptracker/v1/whoami")).andExpect(status().isOk()).andReturn()
        String content = mvcResult.response.contentAsString

        def jsonSlurper = new JsonSlurper()
        def object = jsonSlurper.parseText(content)
        assertEquals(object.whoami, "Application Tracker")
    }

    @Test
    void testHealthIndicator(){
        MvcResult mvcResult = this.mockMvc.perform(get("/health")).andExpect(status().isOk()).andReturn()
        String content = mvcResult.response.contentAsString

        def jsonSlurper = new JsonSlurper()
        def object = jsonSlurper.parseText(content)
        assertEquals(object.status, "UP")
    }

    @Test
    void testLogin(){
        def email = 'ealberto@me.com'
        def json = JsonOutput.toJson([email: email, password: 'passwordswc'])

        MvcResult mvcResult =
                this.mockMvc.perform(
                post("/api/apptracker/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk()).andReturn()
        String content = mvcResult.response.contentAsString

        def jsonSlurper = new JsonSlurper()
        def object = jsonSlurper.parseText(content)
        assertEquals(object.student.email, email)
    }
}
