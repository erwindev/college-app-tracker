package com.erwindev.apptracker.exception

import org.junit.Test

import static org.junit.Assert.*;

import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

/**
 * Created by erwinalberto on 7/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class ApplicationExceptionTest {

    @Test
    void testApplicationException(){
        def message = "This is an error"
        def originalErrorMessage = "This original cause"
        ApplicationException e = new ApplicationException(message)
        assertEquals(e.message, message)

        e = new ApplicationException(message, new Throwable(originalErrorMessage))
        assertEquals(e.cause.message, originalErrorMessage)
        assertEquals(e.message, message)

        e = new ApplicationException(new Throwable(originalErrorMessage))
        assertEquals(e.cause.message, originalErrorMessage)
    }
}
