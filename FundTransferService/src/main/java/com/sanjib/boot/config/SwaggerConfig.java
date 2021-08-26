package com.sanjib.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket docket() {
		Docket docket=new Docket(DocumentationType.SWAGGER_2);
		return docket
			   .select()
			   .apis(RequestHandlerSelectors.basePackage("com.sanjib.boot.controller"))
			   .paths(PathSelectors.any())
			   .build()
			   .apiInfo(apiInfo());
	}//docket(-------)
	
	
	public ApiInfo apiInfo() {
		Contact contact=new Contact("sanjib","DreamCoder","DreamCoder");
		ApiInfo apiInfo=new ApiInfo("SpringBoot and Microservices",
				"For SB&MService UseCases",
				"2.x", 
				"DreamCoder.com",
				contact,
				"Appache license",
				"Appache.org"	);
		
		return apiInfo;
	}//appInfo()

}//class