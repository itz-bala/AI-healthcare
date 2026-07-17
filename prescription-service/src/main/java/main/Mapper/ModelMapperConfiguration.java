package main.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import main.Dto.PrescriptionRequestDto;
import main.Entity.Prescription;

@Configuration
public class ModelMapperConfiguration {
	
//	@Bean
//	public ModelMapper  modelmapper() {
//		
//		return new ModelMapper();
//	}
	
	
	
	
	

	
	@Bean
	public ModelMapper modelMapper() {

	    ModelMapper mapper = new ModelMapper();

	    mapper.getConfiguration()
	          .setAmbiguityIgnored(true);

	    return mapper;

}
}
