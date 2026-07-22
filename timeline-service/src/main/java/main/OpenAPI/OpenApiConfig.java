package main.OpenAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	
	
	@Bean
	public OpenAPI openapi() {
		
	
         Contact  contact=new Contact();
         contact.setName("Bala");
         contact.setEmail("bala@gmail.com");
         
         
         Info info=new Info();
         info.setTitle("Timeline-Service");
         info.setDescription("this is timeline service");
         
         OpenAPI openapi=new OpenAPI();
         
         openapi.setInfo(info);
         
         
         return openapi;
		
		
	}

}
