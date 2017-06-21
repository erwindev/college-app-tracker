package com.erwindev.apptracker.dao

import com.erwindev.apptracker.domain.College
import groovy.sql.Sql
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.sql.DataSource

/**
 * Created by erwinalberto on 6/21/17.
 */
@Component
@Slf4j
class CollegeAppTrackerDao {
    @Autowired
    DataSource dataSource

    List<College> getAllColleges() {
        Sql sql = new Sql(dataSource)
        sql.rows(GET_ALL_COLLEGES).collect{ r -> College.newInstance(r) }
    }

    private final String GET_ALL_COLLEGES = 'select * from college;'
}
