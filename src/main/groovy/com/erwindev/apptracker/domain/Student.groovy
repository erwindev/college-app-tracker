package com.erwindev.apptracker.domain

import groovy.sql.GroovyRowResult

/**
 * Created by erwinalberto on 6/21/17.
 */
class Student extends AppTrackerBaseDomain{
    UUID id
    String firstName
    String lastName
    String email
    String password
    Date created
    Date lastLogin
    Date lastModified
    Address address

    static newInstance(final GroovyRowResult row) {
        if (row)
            return new Student(id: row.id,
                    firstName: row.first_name,
                    lastName: row.last_name,
                    email: row.email,
                    password: row.password,
                    created: row.created,
                    lastLogin: row.last_login,
                    lastModified: row.last_modified)

        return null
    }
}
