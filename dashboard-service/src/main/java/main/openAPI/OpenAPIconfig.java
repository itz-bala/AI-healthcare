package main.openAPI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIconfig {
	
	
	@Bean
	public OpenAPI openapi() {
		
		
		Contact contact=new Contact();
		contact.setName("bala");
		//contact.setUrl(" ");
		//contact.setEmail("riyariya43957@gmail.com");
		
		Info info=new Info();
		info.description("this dashboard we can see hospital activies");
		info.setTitle("dashboard-service");
		info.contact(contact);
		
		
		OpenAPI oi=new OpenAPI();
		oi.info(info);
		
		
		return oi;
		
	}

}
