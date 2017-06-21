package com.erwindev.apptracker.service

import com.erwindev.apptracker.dao.CollegeAppTrackerDao
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by erwinalberto on 6/21/17.
 */
@Service
@Slf4j
class CollegeTrackerService {

    @Autowired
    CollegeAppTrackerDao collegeAppTrackerDao

    List getAllColleges(){
        def colleges = collegeAppTrackerDao.getAllColleges()
        return colleges
    }
}
