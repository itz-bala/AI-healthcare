package main.MAPPER;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
	
	
	
	
	@Bean
	public ModelMapper modelmapper() {
		
		ModelMapper mapper=new ModelMapper();
		
		mapper.getConfiguration().setAmbiguityIgnored(true);
		
		return mapper;
	}

}
