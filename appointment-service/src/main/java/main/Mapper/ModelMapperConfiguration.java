package main.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import main.Dto.AppointmentResponseDto;
import main.Entity.Appointment;

@Configuration
public class ModelMapperConfiguration {
	
	
	@Bean
	public ModelMapper   modelmapper() {
		
		ModelMapper mapper=new ModelMapper();
		
		mapper.getConfiguration().setAmbiguityIgnored(true);
		
		
		// Explicit mapping  ......enum purpose
        mapper.typeMap(Appointment.class, AppointmentResponseDto.class)
              .addMappings(m -> m.map(
                      Appointment::getAppointmentStatus,
                      AppointmentResponseDto::setAppointmentStatus));
		
		return mapper;
	}

}