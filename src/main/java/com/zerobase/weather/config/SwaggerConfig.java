package com.zerobase.weather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        String basePackage = "com.zerobase.weather";
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
//                .paths(PathSelectors.ant("/read/**")) // "/read" 로 시작하는 것만 확인
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        String title = "날씨 일기 프로젝트";
        String description = "날씨 일기를 CRUD 할 수 있는 백엔드 API";
        String version = "2.0";
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(version)
                .build();
    }

}