package com.erwindev.apptracker.config

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * Created by erwinalberto on 6/21/17.
 */
@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Value('${info.app.name}') String appName
    @Value('${info.app.description}') String appDescription
    @Value('${info.app.contact}') String appContact

    @Bean
    Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.erwindev.apptracker.controller"))
                .paths(regex("/api/apptracker/v1.*"))
                .build().apiInfo(apiInfo())
    }

    private def apiInfo() {
        new ApiInfo(appName,
                appDescription,
                "API TOS",
                "Terms of service",
                appContact,
                "Licence of API",
                "API license URL"
        )
    }
}
