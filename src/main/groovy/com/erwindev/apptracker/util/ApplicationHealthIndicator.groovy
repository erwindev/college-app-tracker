package com.erwindev.apptracker.util

import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component

/**
 * Created by erwinalberto on 6/21/17.
 */
@Component
class ApplicationHealthIndicator implements HealthIndicator{

    @Override
    public Health health(){
        int errorCode = check()
        if (errorCode != 0){
            return Health.down().withDetail("Error Code", errorCode).build()
        }
        return Health.up().build()
    }

    private int check(){
        //Todo: Implement status checks here
        return 0
    }
}
