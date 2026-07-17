package main.OPENAPICONFIG;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	
	
	
	@Bean
	public OpenAPI openapi() {
		
		
		Contact contact=new Contact();
		
		   //contact.setEmail("riyariya43957@gmail.com");
		   contact.setName("Bala");
		 //  contact.setUrl("");
		   
		   
		   
		   Info info=new Info();
		   
		   
		   info.setDescription("THIS IS API-GATEWAY WE CAN HANDLE EVERYTHING FROM HERE");
		   info.setTitle("API-GATEWAY");
		   info.setContact(contact);
		   
		
		   
		   OpenAPI openapi=new OpenAPI();
		   
		   openapi.setInfo(info);
		
		
		return openapi;
		
	}

}
