package com.erwindev.apptracker.service

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

import static org.assertj.core.api.Assertions.assertThat

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by erwinalberto on 6/21/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class CollegeTrackerServiceIntTest {

    @TestConfiguration
    static class CollegeTrackerServiceTestContextConfiguration {

        @Bean
        public CollegeTrackerService collegeTrackerService() {
            return new CollegeTrackerService()
        }
    }

    @Autowired
    CollegeTrackerService collegeTrackerService

    @Test
    public void shouldGetListOfColleges() throws Exception{
        assertThat(collegeTrackerService.getAllColleges().size(), is(3))
    }
}
