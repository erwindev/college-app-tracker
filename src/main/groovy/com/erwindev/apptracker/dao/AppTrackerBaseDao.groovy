package com.erwindev.apptracker.dao

import com.erwindev.apptracker.domain.AppTrackerBaseDomain
import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired

import javax.sql.DataSource

/**
 * Created by erwinalberto on 7/1/17.
 */
abstract class AppTrackerBaseDao {
    @Autowired
    DataSource dataSource

    void create(AppTrackerBaseDomain object){

        Sql sql = new Sql(dataSource)
        Map paramMap = object.asMap()

        sql.executeInsert("""
             INSERT INTO ${getTableName()}
                    (${paramMap.keySet().join(',')})
                    VALUES
                    (${paramMap.keySet().collect { key -> ":" + key }.join(',')})
        """, paramMap)
    }

    abstract String getTableName();


}
