package com.erwindev.apptracker.config

import com.erwindev.apptracker.security.JwtAuthenticationEntryPoint
import com.erwindev.apptracker.security.JwtAuthenticationProvider
import com.erwindev.apptracker.security.JwtFilter
import com.erwindev.apptracker.util.TokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

/**
 * Created by erwinalberto on 6/21/17.
 */
@EnableWebSecurity
@Configuration
@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    TokenUtil tokenUtil

    @Autowired
    JwtFilter jwtFilter

    @Autowired
    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint

    @Autowired
    JwtAuthenticationProvider jwtAuthenticationProvider

    String[] swaggerPatterns =[
        "/v2/api-docs",
        "/configuration/ui",
        "/swagger-resources",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        "/swagger-resources/configuration/ui",
        "/swagge‌​r-ui.html"
    ]

    String[] collegeTrackerAppAllowedPatterns = [
        "/api/apptracker/v1/login",
        "/health",
        "/info"
    ]

    String[] collegeTrackerAppAuthenticatedPatterns = [
            "/api/apptracker/v1/whoami",
            "/api/apptracker/v1/colleges",
    ]

    String[] allAllowed = [swaggerPatterns, collegeTrackerAppAllowedPatterns]

    @Override
    void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers(allAllowed).permitAll()
                .antMatchers(collegeTrackerAppAuthenticatedPatterns)
                .hasAuthority("ROLE_USER")
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)

        http.cors().configurationSource(corsConfigurationSource())
        http.csrf().disable()
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration()
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource()

        configuration.setAllowCredentials(true)
        configuration.addAllowedOrigin("*")
        configuration.addAllowedHeader("*")
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTION"))

        source.registerCorsConfiguration("/**", configuration)
        return source
    }

    @Override
    void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(swaggerPatterns)
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth)  throws Exception {
        auth.authenticationProvider(jwtAuthenticationProvider)
    }


}
