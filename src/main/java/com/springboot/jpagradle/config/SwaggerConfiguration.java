package com.springboot.jpagradle.config;


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
public class SwaggerConfiguration {
    //swagger 설정 코드
    @Bean
    public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .useDefaultResponseMessages(false)
                    .apiInfo(getApiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.springboot.jpagradle")) //스캔할 패키지 범위 설정
                    .paths(PathSelectors.any())
                    .build();
        }

    //return될 api정보를 담은 것
    private ApiInfo getApiInfo() {
            return new ApiInfoBuilder()
                    .title("String Boot Open API Test with Swagger")
                    .description("설명 부분")
                    .version("1.0.0")
                    .build();
        }
    }
