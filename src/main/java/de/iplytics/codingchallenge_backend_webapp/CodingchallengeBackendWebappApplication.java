package de.iplytics.codingchallenge_backend_webapp;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CodingchallengeBackendWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingchallengeBackendWebappApplication.class, args);
	}
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("de.iplytics.codingchallenge_backend_webapp.api"))
				.build()
				.apiInfo(generateApiInfo());
	}
	
	private ApiInfo generateApiInfo() {
		return new ApiInfo(
			      "IPlytics Backend Coding Challenge", 
			      "CRUD Auto-Generated Restful Web Service APIs Documentation", 
			      "1.0", 
			      "https://www.iplytics.com/wp-content/uploads/2018/05/IPlytics_Privacy-Policy_2018-ENG.pdf", 
			      new Contact("IPlytics", "https://www.iplytics.com/", "info@iplytics.com"), 
			      "", // License of API
			      "", // API license URL
			      Collections.emptyList());
	}

}
