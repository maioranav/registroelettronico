package org.vm93.registroelettronico.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

		@Bean
	  public OpenAPI myOpenAPI() {

		    Contact contact = new Contact();
		    contact.setEmail("info@vincenzomaiorana.it");
		    contact.setName("Vincenzo Maiorana");
		    contact.setUrl("https://www.vincenzomaiorana.it");

		    Info info = new Info()
		        .title("Registro Elettronico API")
		        .version("1.0")
		        .contact(contact)
		        .description("Registro Elettronico Capstone Epicode API.");

		    return new OpenAPI().info(info);
		  }

}