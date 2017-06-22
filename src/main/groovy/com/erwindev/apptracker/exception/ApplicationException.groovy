package com.erwindev.apptracker.exception

/**
 * Created by erwinalberto on 6/21/17.
 */
class ApplicationException extends RuntimeException{
    ApplicationException(){
        super()
    }

    ApplicationException(String message) {
        super(message)
    }

    ApplicationException(String message, Throwable cause) {
        super(message, cause)
    }

    ApplicationException(Throwable cause) {
        super(cause)
    }
}
