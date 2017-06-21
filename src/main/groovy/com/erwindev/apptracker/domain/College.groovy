package com.erwindev.apptracker.domain

import groovy.sql.GroovyRowResult

/**
 * Created by erwinalberto on 6/21/17.
 */
class College {
    UUID id
    String name
    Date created
    Date lastModified
    Address address

    public static newInstance(final GroovyRowResult row) {
        new College(id: row.id,
                name: row.name,
                created: row.created,
                lastModified: row.last_modified)
    }
}
