package com.erwindev.apptracker

import com.erwindev.apptracker.controller.MainController
import com.erwindev.apptracker.dao.CollegeAppTrackerDao
import com.erwindev.apptracker.service.CollegeTrackerService

import static org.assertj.core.api.Assertions.assertThat

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
class SmokeTest {
    @Autowired
    MainController mainController

    @Autowired
    CollegeTrackerService collegeTrackerService

    @Autowired
    CollegeAppTrackerDao collegeAppTrackerDao

    @Test
    public void contexLoads() throws Exception {
        assertThat(mainController).isNotNull()
        assertThat(collegeTrackerService).isNotNull()
        assertThat(collegeAppTrackerDao).isNotNull()
    }
}
