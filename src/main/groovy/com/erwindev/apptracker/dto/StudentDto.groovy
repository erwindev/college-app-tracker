package com.erwindev.apptracker.dto

import com.erwindev.apptracker.domain.Address
import com.erwindev.apptracker.domain.Student

/**
 * Created by erwinalberto on 6/24/17.
 */
class StudentDto {
    String firstName
    String lastName
    String email
    Address address

    static StudentDto create(Student student){
        new StudentDto(firstName: student.firstName,
                lastName: student.lastName,
                email: student.email,
                address: student.address)
    }
}
