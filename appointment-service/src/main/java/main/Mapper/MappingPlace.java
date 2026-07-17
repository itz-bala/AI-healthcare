package main.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import main.Dto.AppointmentRequestDto;
import main.Dto.AppointmentResponseDto;
import main.Entity.Appointment;

@Component
public class MappingPlace {
	
	
	
	@Autowired
	private ModelMapper modelmapper;

	
	//dto to entity convert
	
	public  Appointment  toEntity(AppointmentRequestDto dto) {
		
		return modelmapper.map(dto, Appointment.class);
		
		
	}
	
	
	//entity to dto convert
	
	
	public AppointmentResponseDto toDto(Appointment appointment) {
		
		return modelmapper.map(appointment,AppointmentResponseDto.class);
	}
	
	
	
}
