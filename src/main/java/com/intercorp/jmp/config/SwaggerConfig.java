// Desafio Intercorp - 2020 
package com.intercorp.jmp.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Name: SwaggerConfig
 * @author JESUS MATOS
 * @date 2020-12-21
 * @version 1.0.0
 * 
 * **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/***
	 * Get API Docket
	 * @return Docket
	 * 
	 * */
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.intercorp.jmp.rest"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}
	
	/**
	 * Get api info
	 * @return Api Info
	 * 
	 * **/
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Backend Customer Intercorp API",
				"Microservicio de Clientes, Intercorp 2020",
				"1.0",
				"https://intercorp-retail.com/",
				new Contact("JMP", "https://jmpitconsulting.com", "jmatos@jmpitconsulting.com"),
				"LICENSE",
				"LICENSE URL",
				Collections.emptyList());
	}
}