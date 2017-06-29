package com.erwindev.apptracker.domain

import groovy.sql.GroovyRowResult

/**
 * Created by erwinalberto on 6/21/17.
 */
class Address implements Serializable{
    String street1
    String street2
    String city
    String state
    String country
    String zipcode

    static newInstance(final GroovyRowResult row) {
        if (row)
            return new Address(street1: row.street1,
                    street2: row.street2,
                    city: row.city,
                    state: row.state,
                    zipcode: row.zipcode,
                    country: row.country)
        return null
    }
}
