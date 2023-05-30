package br.com.cotiinformatica.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	@Bean // Anotação que diz que a configuração está sendo feita para O SWAGGER.
	public OpenAPI customOpenApi() {
		
		//Nova funcionalidade 30/05
		Contact contact = new Contact();
		contact.url("http://www.cotiinformatica.com.br");
		contact.email("contato@cotiinformatica.com.br");
		
		return new OpenAPI().components
				(new Components()).info(new Info()
						.title("API de Usuários - COTI Informática")
						.description("Sistema SpringBoot com SpringData para controle de Usuários")
						.contact(contact)
						.version("v1"));
	}
}
