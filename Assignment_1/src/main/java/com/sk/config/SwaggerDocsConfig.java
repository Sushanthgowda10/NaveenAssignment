package com.sk.config;

import java.nio.file.Paths;
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

@Configuration
@EnableSwagger2
public class SwaggerDocsConfig {

	
	public Docket createDocker() {
		return new Docket(DocumentationType.SWAGGER_2) // UISCREEN TYPE
				.select() // to Specify RestController
				.apis(RequestHandlerSelectors.basePackage("com.nt.controller")).paths(PathSelectors.regex("/tourist.*"))
				.build().useDefaultResponseMessages(true).apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		Contact contact = new Contact("sushanth", "http://www.HCL.com/tourist", "sushanthgowda97@gmail.com");
		return new ApiInfo("Tourist API", "Gives Info Tourist Activites", "3.4.RELEASE", "http://www.hcl.com/licence",
				contact, "GNU Public", "htttp://apache.org/licence/gnu", Collections.emptyList());

	}

}
