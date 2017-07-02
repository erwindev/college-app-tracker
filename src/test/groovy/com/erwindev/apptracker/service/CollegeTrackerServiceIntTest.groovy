package com.erwindev.apptracker.service

import org.springframework.test.context.TestPropertySource

import static org.junit.Assert.*;

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by erwinalberto on 6/21/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class CollegeTrackerServiceIntTest {

    @Autowired
    CollegeTrackerService collegeTrackerService

    @Test
    public void testGetListOfColleges() throws Exception{
        assertEquals(collegeTrackerService.getAllColleges().size(), 3)
    }
}
