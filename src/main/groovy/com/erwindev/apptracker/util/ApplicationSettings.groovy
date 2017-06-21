package com.erwindev.apptracker.util

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

/**
 * Created by erwinalberto on 6/21/17.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="erwindev")
class ApplicationSettings {
    String apiVersion
}
